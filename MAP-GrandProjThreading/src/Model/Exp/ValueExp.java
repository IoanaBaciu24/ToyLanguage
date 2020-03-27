package Model.Exp;

import Model.Containers.HeapInter;
import Model.Containers.MapInter;
import Model.Value.value;

public class ValueExp implements Expr {

    value e;
    //String e;
    public ValueExp(value ex) {e=ex;}
    //public ValueExp(String ex) {e=ex;}
    public value eval(MapInter<String, value> tbl, HeapInter heap)
    {

       // return  tbl.get(e);
        return e;
    }
    public String toString()
    {
        return e.toString() ;
    }

    @Override
    public Expr deepcopy() {
        ValueExp evl = new ValueExp(e.deepcopy());
        return evl;
    }
}
