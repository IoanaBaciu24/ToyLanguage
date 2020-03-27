package Model.Stmt;

import ExceptionThing.SomeException;
import Model.Containers.MapInter;
import Model.Containers.MonStaque;
import Model.Containers.StackInter;
import Model.ProgState;
import Model.Type.type;

import java.util.Stack;

public class CompSrmt implements IStmt {

    IStmt first, second;
    public CompSrmt(IStmt f, IStmt s)
    {
        first = f;
        second = s;
    }

    public String toString()
    {
        return  "("+first.toString() + ";" + second.toString()+")";
    }

    public ProgState execute(ProgState state) throws SomeException
    {
        StackInter<IStmt> staque = state.getExeStack();
        staque.push(second);
        staque.push(first);
        return null;
    }

    @Override
    public IStmt deepcopy() {
        CompSrmt evl = new CompSrmt(first.deepcopy(), second.deepcopy());
        return evl;
    }

    @Override
    public MapInter<String, type> typecheck(MapInter<String, type> typeEnv) throws SomeException {
        return second.typecheck(first.typecheck(typeEnv));
    }
}
