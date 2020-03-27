package Module.Statement;
import Exceptions.PancakeException;
import Module.*;
public interface IStmt {
    public PrgState execute(PrgState state) throws PancakeException;
    public String toString();

}
