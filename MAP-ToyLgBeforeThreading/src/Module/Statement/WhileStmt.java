package Module.Statement;
import Container.MyDictionary;
import Container.*;
import Exceptions.PancakeException;
import Module.Type.BoolType;
import Module.Value.*;
import Module.Expression.*;
import Module.PrgState;

import java.util.Objects;

public class WhileStmt implements IStmt{
    private Exp exp;
    private IStmt stmt;
    public WhileStmt(Exp ex, IStmt st)
    {
        exp = ex;
        stmt = st;
    }
    public PrgState execute(PrgState state) throws PancakeException {

        MyDictionary<String, Value> sym = state.getSymTable();
        Heap heap= state.getHeap();
        MyStack<IStmt> stack = state.getStack();
        Value val = exp.eval(sym, heap);

        if(val.getType().equals(new BoolType()))
        {
            boolean evaluated = ((BoolValue)val).getVal();
            if(Objects.equals(evaluated, true))
            {
                stack.push(this);
                stmt.execute(state);
            }
        }
        else throw new PancakeException("While statement requires boolean expression");

        return state;

    }

    public String toString() { return "while " + exp.toString() + " do " + stmt.toString(); }
}
