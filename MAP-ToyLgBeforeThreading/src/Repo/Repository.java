package Repo;
import Exceptions.PancakeException;
import Module.PrgState;

import java.io.IOException;

public interface Repository {
    public PrgState getCurrentPrg();
    public void add(PrgState p);
    public void logPrgStateExec() throws PancakeException;
}
