package Module.Statement;
import Container.MyStack;
import Module.*;
import java.util.Stack;

public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt second;

    public CompStmt(IStmt st, IStmt nd)
    {
        first = st;
        second = nd;
    }

    public PrgState execute(PrgState state)
    {
        MyStack<IStmt> stack = state.getStack();
        stack.push(second);
        stack.push(first);
        return state;
    }
    public String toString()
    {
        return "( " + first.toString() + ", " + second.toString() + " )";
    }
}
