package Module.Statement;
import Exceptions.PancakeException;

import Module.PrgState;



public class NopStmt implements IStmt {

    public String toString()
    {
        return "";
    }

    public PrgState execute(PrgState state) throws PancakeException {
        return state;
    }
}
