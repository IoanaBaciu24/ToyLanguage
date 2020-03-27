package RepoStuff;

import ExceptionThing.SomeException;
import Model.ProgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Repo implements RepoInter {
    List<ProgState> myList;
    String logFilePath;

    public Repo(String pth)
    {
        myList = new ArrayList<ProgState>();
        //logFilePath = "C:\\Users\\papuci\\IdeaProjects\\MAP-GrandProj\\test.txt";
        logFilePath = pth;
    }
//    public ProgState getCrtPrg() throws SomeException
//    {
//        //pana vad ce tre sa faca functia asta
//        //return new ProgState(null, null,null,null);
//        return myList.getFirst();
//    }

    public void add(ProgState p)
    {
        myList.add(p);
    }

    @Override
    public void setPrgList(List<ProgState> prg) {
        myList = prg;
    }

    @Override
    public List<ProgState> getPrgList() {
        return myList;
    }

    @Override
    public void logPrgStateExec(ProgState prg) throws IOException, SomeException {
        PrintWriter logFile = new  PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.print(prg.toString());
        logFile.close();
        //TODO write error to file
    }
}
