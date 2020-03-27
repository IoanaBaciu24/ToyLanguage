package Model.Exp;

import Model.Containers.HeapInter;
import Model.Containers.MapInter;
import Model.Value.value;

public class VarExp implements Expr {

    String id;
    public VarExp(String v)
    {
        id=v;
    }
    public value eval(MapInter<String, value> tbl, HeapInter heap)
    {
        return tbl.get(id);

    }
    public String toString()
    {
        return id;
    }

    @Override
    public Expr deepcopy() {
        VarExp evl = new VarExp(id);
        return evl;
    }
}
