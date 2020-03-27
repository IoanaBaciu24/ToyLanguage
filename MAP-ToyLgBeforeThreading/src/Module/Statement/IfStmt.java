package Module.Statement;
import Container.Heap;
import Container.MyDictionary;
import Exceptions.PancakeException;
import Module.Type.*;
import Module.Value.*;
import Module.Expression.*;
import Module.PrgState;

public class IfStmt implements IStmt {
    private Exp exp;
    private IStmt thenSt, elseSt;

    public IfStmt(Exp e, IStmt t, IStmt el)
    {
        exp = e;
        thenSt = t;
        elseSt = el;
    }

    public String toString()
    {
        return "if(" + exp.toString() + ")\n then "+ thenSt.toString() + "\nelse " + elseSt;
    }

    public PrgState execute(PrgState state) throws PancakeException
    {
        Heap heap = state.getHeap();
        MyDictionary<String, Value> syms = state.getSymTable();
        if(exp.eval(syms, heap).getType() instanceof  BoolType)
        {
            if(exp.eval(syms, heap) == new BoolValue(true))
                thenSt.execute(state);
            else
                elseSt.execute(state);
        }
        else
            throw new PancakeException(exp.toString() + " must be of type bool\n");
        return state;
    }
}
