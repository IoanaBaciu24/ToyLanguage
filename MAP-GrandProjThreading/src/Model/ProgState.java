package Model;
import ExceptionThing.SomeException;
import Model.Containers.*;
import Model.Stmt.IStmt;
import Model.Value.StringValue;
import Model.Value.value;


import java.io.BufferedReader;
import java.util.*;

public class ProgState {

    //Stack<IStmt> exeStack = new Stack<IStmt>();
    StackInter<IStmt> exeStack ;
    //HashMap<String, value> symTable = new HashMap<String, value>();
    MapInter<String, value> symTable;
    QueueInter<value> out ;
    MapInter<StringValue, BufferedReader> FileTable ;
    HeapInter heap;
    IStmt originalProgram;
    public static Integer lastThr = 0;
    public Integer id;

    public synchronized Integer createId()
    {
        lastThr++;
        return lastThr;
    }

    public ProgState(StackInter<IStmt> stk, MapInter<String,value> symtbl,QueueInter<value> ot, IStmt prg){
        exeStack=stk;
        symTable=symtbl;
        out = ot;
        //aici imi trebuie deepcopy, dar nu stiu exact ce sa fac cu deepcopy ul ala
        originalProgram=prg.deepcopy();//recreate the entire original prg
        heap = new MonHeap();
        FileTable = new MonMap<StringValue, BufferedReader>();
        stk.push(prg);
        id = createId();
    }

    public ProgState(StackInter<IStmt> stk, MapInter<String,value> symtbl,QueueInter<value> ot, MapInter<StringValue, BufferedReader> ftbl,HeapInter h,IStmt prg)
    {
        exeStack=stk;
        symTable=symtbl;
        out = ot;
        originalProgram=prg.deepcopy();
        heap = h;
        FileTable = ftbl;
        stk.push(prg);
        id = createId();
    }

    public boolean isNotCompleted()
    {
        return !exeStack.isEmpty();
    }

   // public Stack<IStmt> getExeStack() { return exeStack; }
    public StackInter<IStmt> getExeStack() {return exeStack;}
    public MapInter<String, value> getSymTable() {return  symTable;}
    public QueueInter<value> getOut(){return out;}
    public IStmt getOriginalProgram(){return originalProgram;}
    public MapInter<StringValue,BufferedReader> getFileTable(){return FileTable;}
    public HeapInter getHeap(){return heap;}

    //public void setExeStack(Stack<IStmt> stk){exeStack = stk;}
    public void setExeStack(StackInter<IStmt> stk) {exeStack=stk;}
    public void setSymTable(MapInter<String , value> ceva)
    {
        symTable = ceva;
    }
    public void setOut(QueueInter<value> o)
    {
        out = o;
    }
    public void setOriginalProgram(IStmt op)
    {
        originalProgram=op;
    }
    public void setFileTable(MapInter<StringValue,BufferedReader> tbl) {FileTable = tbl;}


    public ProgState oneStep() throws SomeException
    {
        //Stack<IStmt> stk=state.getExeStack();
        if(exeStack.isEmpty()){
            throw  new SomeException("not much in stack");}
        IStmt crtStmt = exeStack.pop();
        //System.out.println(crtStmt.toString());
        return crtStmt.execute(this);
    }


    public String toString()
    {
        String res = "";
        res+= "Thread id:\n";
        res+=id.toString();
        res+="\n";
        res+="ExeStack:\n";
        res+=exeStack.toString();
        res+="\n";
        res+="SymTable:\n";
        res+=symTable.toString();
        res+="\n";
        res+="Out:\n";
        res+=out.toString();
        res+="\n";
        res+="FileTable:\n";
        res+=FileTable.toString() + "\n";
        res+="HeapTable\n";
        res+=heap.toString();


        return res + "\n";
    }


}
