package RepoStuff;

import ExceptionThing.SomeException;
import Model.ProgState;

import java.io.IOException;
import java.util.List;

public interface RepoInter {
    //public ProgState getCrtPrg() throws SomeException;
    public void add(ProgState p);
    public void logPrgStateExec(ProgState prg) throws IOException, SomeException;

    public List<ProgState> getPrgList();
    public void setPrgList(List<ProgState> prg);
}
