package Repo;
import Exceptions.PancakeException;
import Module.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class CompilerRepo implements Repository {
    private LinkedList<PrgState> programStates = new LinkedList<PrgState>();
    private String logFilePath;

    public CompilerRepo(PrgState p, String file)
    {
        programStates.add(p);
        logFilePath = file;
    }

    public PrgState getCurrentPrg() {
        return programStates.getLast();
    }

    public void add(PrgState p)
    {
        programStates.add(p);
    }

    public void logPrgStateExec() throws PancakeException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.print(getCurrentPrg().toString());
            logFile.print("\n--------------------------------------------------------------------------------\n");
            logFile.close();
        }
        catch (IOException e)
        {
            throw new PancakeException("An error occured");
        }
    }

}
