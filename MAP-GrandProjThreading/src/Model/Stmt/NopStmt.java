package Model.Stmt;

import Model.ProgState;

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
}
