package Module;

import Container.*;
import Module.Statement.IStmt;
import Module.Value.*;

import java.io.BufferedReader;

public class PrgState{

    private MyStack<IStmt> ExeStack;
    private MyDictionary<String, Value> SymTable;
    private MyList<String> Out;
    private MyDictionary<StringValue, BufferedReader> FileTable = new MyDictionary<StringValue, BufferedReader>();
    private Heap Heap = new Heap();//to do--->integrate heap in functionalities. How? dunno
   // private IStmt originalProgram; //long live
    private Integer lastId = 1;

    public PrgState(MyStack<IStmt> exe, MyDictionary<String, Value> sym, MyList<String> ou, IStmt og)
    {
        this.ExeStack = exe;
        this.SymTable = sym;
        this.Out = ou;
        this.ExeStack.push(og);
    }
    public MyStack<IStmt> getStack()
    {
        return ExeStack;
    }

    public MyList<String> getOut()
    {
        return Out;
    }
    public void setOut(MyList<String> ou)
    {
        Out = ou;
    }
    public void setStack(MyStack<IStmt> st) {ExeStack = st;}
    public MyDictionary<String, Value> getSymTable()
    {
        return SymTable;
    }

    public MyDictionary<StringValue, BufferedReader> getFileTable()
    {
        return FileTable;
    }

    public String toString()
    {
        return "Execution Stack -> "+ExeStack.toString() +"\nSymbols Table -> " + SymTable.toString() + "\nOut -> " + Out.toString() + "\nFileTable -> " + FileTable.toString() + "\n" +
                "Heap -> " + Heap.toString() + "\n";
    }

    public Heap getHeap() {
        return Heap;
    }

    public Integer getNewAddress() {
        return lastId + 1;
    }

    public void setSymTable(MyDictionary<String, Value> sym) {
        SymTable = sym;
    }

    public void setHeap(Heap heap) {
        Heap = heap;
    }
}
