package Model.Stmt;

import ExceptionThing.SomeException;
import Model.Containers.MapInter;
import Model.Containers.MonMap;
import Model.Containers.MonStaque;
import Model.ProgState;
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
}
