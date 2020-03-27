package Model.Stmt;

import ExceptionThing.SomeException;
import Model.Containers.MapInter;
import Model.Containers.MonMap;
import Model.Containers.MonStaque;
import Model.ProgState;
import Model.Type.BoolType;
import Model.Type.type;
import Model.Value.value;

public class ForkStmt implements IStmt {

    IStmt statement;

    public ForkStmt(IStmt s)
    {
        statement = s;
    }

    public ProgState execute(ProgState state) throws SomeException
    {
        MapInter<String, value> tbl = state.getSymTable();
        MapInter<String,value> ntbl = tbl.clone();

        return new ProgState(new MonStaque<IStmt>(), ntbl,state.getOut(),state.getFileTable(), state.getHeap(),statement);

    }
    public String toString()
    {
        return "Fork (" + statement.toString() + ")";
    }
    public IStmt deepcopy()
    {
        ForkStmt evl = new ForkStmt(statement.deepcopy());
        return evl;
    }

    @Override
    public MapInter<String, type> typecheck(MapInter<String, type> typeEnv) throws SomeException {
        /*type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(typeEnv.clone());

            elseS.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else
            throw new SomeException("The condition of IF has not the type bool");*/

        statement.typecheck(typeEnv.clone());
        return typeEnv;
    }
}
