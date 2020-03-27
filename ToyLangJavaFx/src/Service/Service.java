package Service;

import ExceptionThing.SomeException;
import Model.ProgState;
import Model.Value.RefValue;
import Model.Value.value;
import RepoStuff.RepoInter;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Service {

    RepoInter repo;
    ExecutorService executor = Executors.newFixedThreadPool(2);

    public Service(RepoInter r)
    {
        repo = r;
    }

//    public ProgState oneStep(ProgState state) throws SomeException
//    {
//        //Stack<IStmt> stk=state.getExeStack();
//        StackInter<IStmt> stk = state.getExeStack();
//        if(stk.isEmpty()){
//            throw  new SomeException("not much in stack");}
//        IStmt crtStmt = stk.pop();
//        //System.out.println(crtStmt.toString());
//        return crtStmt.execute(state);
//    }

    public void allSteps() throws SomeException, IOException, InterruptedException {
        this.executor= Executors.newFixedThreadPool(2);
        List<ProgState> prgList = removeCompletedPrg(repo.getPrgList());

        while(prgList.size() > 0){
            execGarbageCollector();
            oneStepForAllPrg(prgList);
            //remove the completed programs
            prgList= removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        repo.setPrgList(prgList);

    }


    Map<Integer, value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer,value>
            heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));}



    List<Integer> getAddrFromSymTable(Collection<value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v;return v1.getAddr();})
                .collect(Collectors.toList());
    }

    private List<Integer> getAddrFromHeap(Collection<value> heapValues)
    {
        return heapValues.stream().filter(v->v instanceof RefValue).map(v->((RefValue)v).getAddr()).collect(Collectors.toList());
    }
    private void execGarbageCollector() throws SomeException
    {
//        ProgState current = repo.getCrtPrg();
//        List<Integer> add = getAddrFromSymTable(current.getSymTable().values());
//        List<Integer> addd = getAddrFromHeap(current.getHeap().values());
//
//        add.addAll(addd);
//        current.getHeap().setContent(unsafeGarbageCollector(add,current.getHeap().getContent()));

        //todo am modificat aici garbc sa mearga cu concurenta
        List<ProgState>prgList = repo.getPrgList();
        List<List<Integer>> symtbladdrs =prgList.stream().map(p->p.getSymTable()).map(p->getAddrFromSymTable(p.values())).collect(Collectors.toList());
        List<Integer> addrs = new ArrayList<Integer>();
        symtbladdrs.forEach(addrs::addAll);
        List<Integer> fromheap =getAddrFromHeap(prgList.get(0).getHeap().values());
        addrs.addAll(fromheap);
        Map<Integer,value> garbCollector = unsafeGarbageCollector(addrs,prgList.get(0).getHeap().getContent());
        //ConcurrentMap<Integer,value> m = (ConcurrentMap<Integer, value>) garbCollector;
        prgList.forEach(p->p.getHeap().setContent(garbCollector));


    }


    public List<ProgState> removeCompletedPrg(List<ProgState> inPrgList)
    {
        return inPrgList.stream().filter(p->p.isNotCompleted()).collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<ProgState> prgList) throws  SomeException, IOException,InterruptedException {
        for (ProgState prg : prgList) {
            repo.logPrgStateExec(prg);
            System.out.println(prg);
        }

        List<Callable<ProgState>> callList = prgList.stream().map((ProgState p)->(Callable<ProgState>)(p::oneStep)).collect(Collectors.toList());
        List<ProgState> newPrgList = executor.invokeAll(callList).stream().map(future->{try{
            return future.get();
        }
        catch ( InterruptedException | ExecutionException e)
        {
            //throw new SomeException(e.getMessage());
            System.out.println(e);
        }return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());

        /*List<ProgState> newPrgList = executor.invokeAll(callList).stream().map(future->{
            try{
                return future.get();
            }
            catch (SomeException | InterruptedException | ExecutionException exc){
                throw new SomeException(exc.getMessage());
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());*/

        prgList.addAll(newPrgList);

        for (ProgState prg : prgList) {
            System.out.println(prg);
            repo.logPrgStateExec(prg);
        }
        repo.setPrgList(prgList);

    }

    public List<ProgState> getProgStates()
    {
        return repo.getPrgList();
    }


    public void thatOneStepForGUI(List<ProgState> prgList) throws SomeException, IOException, InterruptedException
    {
        removeCompletedPrg(prgList);
        oneStepForAllPrg(prgList);
        execGarbageCollector();
    }

}

