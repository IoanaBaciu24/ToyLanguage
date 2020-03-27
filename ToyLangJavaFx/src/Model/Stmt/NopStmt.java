package Model.Stmt;

import ExceptionThing.SomeException;
import Model.Containers.MapInter;
import Model.ProgState;
import Model.Type.type;

public class NopStmt implements IStmt {

    public NopStmt()
    {}
    public ProgState execute(ProgState prg)
    {
        return null;
    }
    public String toString()
    {
        return "le nop";
    }

    @Override
    public IStmt deepcopy() {
        NopStmt evl= new NopStmt();
        return evl;
    }

    @Override
    public MapInter<String, type> typecheck(MapInter<String, type> typeEnv) throws SomeException {
        return typeEnv;
    }
}
