package Model.Stmt;

import ExceptionThing.SomeException;
import Model.ProgState;

public interface IStmt {

    public ProgState execute(ProgState state) throws SomeException;
    public String toString();
    public IStmt deepcopy();
}
