package Module.Statement;
import Container.Heap;
import Container.MyDictionary;
import Container.MyList;
import Exceptions.PancakeException;
import Module.*;
import Module.Expression.*;

import Module.Value.*;

public class PrintStmt implements IStmt {
    private Exp exp;
    public PrintStmt(Exp e)
    {
        exp = e;
    }
    public PrgState execute(PrgState state) throws PancakeException
    {
        Heap heap = state.getHeap();
        MyList<String> out = state.getOut();
        MyDictionary<String, Value> syms = state.getSymTable();
        out.add(exp.eval(syms, heap).toString());
        return state;
    }
    public String toString()
    {
        return "print " + exp.toString();
    }
}
