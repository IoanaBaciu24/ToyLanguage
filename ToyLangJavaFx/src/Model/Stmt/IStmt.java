package Model.Stmt;

import ExceptionThing.SomeException;
import Model.Containers.MapInter;
import Model.ProgState;
import Model.Type.type;

public interface IStmt {

    public ProgState execute(ProgState state) throws SomeException;
    public String toString();
    public IStmt deepcopy();
    public MapInter<String, type> typecheck(MapInter<String, type> typeEnv) throws SomeException;
 }
