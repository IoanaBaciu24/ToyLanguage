package Controller;
import Container.MyStack;
import Exceptions.PancakeException;
import Module.Statement.IStmt;
import Module.*;
import Module.Value.RefValue;
import Module.Value.Value;
import Repo.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private Repository repo;

    public Controller(Repository r)
    {
        repo = r;
    }

    private Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap){

        return heap.entrySet().stream().filter(e->symTableAddr.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
                .collect(Collectors.toList());
    }

    private List<Integer> getAddrFromHeap(Collection<Value> heapValues)
    {
        return heapValues.stream().filter(v-> v instanceof RefValue).map(v->((RefValue)v).getAddr()).collect(Collectors.toList());
    }

    private void executeGarbageCollector()
    {
        PrgState current = repo.getCurrentPrg();
        List<Integer> add = getAddrFromSymTable(current.getSymTable().values());
        List<Integer> add2 = getAddrFromHeap(current.getHeap().values());
        add.addAll(add2);
        current.getHeap().setContent((HashMap<Integer, Value>) unsafeGarbageCollector(add, current.getHeap().getContent()));
    }

    private PrgState oneStep(PrgState state) throws PancakeException
    {
        MyStack<IStmt> stk = state.getStack();
        if(stk.isEmpty())
            throw new PancakeException("Program state stack is empty...");
        IStmt currentStatement = stk.pop();
        return currentStatement.execute(state);
    }

    public void allSteps() throws PancakeException{
        PrgState program = repo.getCurrentPrg();
        repo.logPrgStateExec();
        while(!program.getStack().isEmpty()) {
            this.oneStep(program);
            repo.logPrgStateExec();
            System.out.println(program.toString());
            executeGarbageCollector();
            repo.logPrgStateExec();
        }
    }
}
