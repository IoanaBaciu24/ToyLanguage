package Model.Exp;

import ExceptionThing.SomeException;
import Model.Containers.HeapInter;
import Model.Containers.MapInter;
import Model.Type.type;
import Model.Value.value;

public interface Expr {

    public value eval(MapInter<String, value> tbl, HeapInter heap) throws SomeException;
    public String toString();
    public Expr deepcopy();
    public type typecheck(MapInter<String, type> typeEnv) throws SomeException;
}
