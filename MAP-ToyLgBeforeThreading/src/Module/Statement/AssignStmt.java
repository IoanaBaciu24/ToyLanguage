package Module.Statement;
import Container.Heap;
import Container.MyDictionary;
import Container.MyStack;
import Exceptions.PancakeException;
import Module.Type.*;
import Module.Value.*;
import Module.Expression.*;
import Module.PrgState;

public class AssignStmt implements IStmt {
    private String id;
    private Exp exp;

    public AssignStmt(String i, Exp ex)
    {
        id = i;
        exp = ex;
    }

    public String toString()
    {
        return id + "=" + exp.toString();
    }

    public PrgState execute(PrgState state) throws PancakeException
    {
        MyStack<IStmt> stk = state.getStack();
        MyDictionary<String, Value> syms = state.getSymTable();
        Heap heap = state.getHeap();
        Value val = exp.eval(syms, heap);

        if(syms.isDefined(id))
        {
            // you can delete this boie
//            System.out.println(val);
            Type typID = (syms.lookUp(id)).getType();
            if(val.getType().equals(typID))
                syms.put(id, val);
            else
                throw new PancakeException("Declared type of variable " + id + " and type of the assigned expression do not match!\n");
        }
        else
            throw new PancakeException("Variable " + id + " undeclared\n");
        return state;
    }
}
