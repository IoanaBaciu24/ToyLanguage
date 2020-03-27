package Model.Exp;

import ExceptionThing.SomeException;
import Model.Containers.HeapInter;
import Model.Containers.MapInter;
import Model.Type.RefType;
import Model.Value.RefValue;
import Model.Value.value;

public class HeapReadingExp implements Expr {
    Expr expr;

    public HeapReadingExp(Expr e)
    {
        expr = e;
    }
    public value eval(MapInter<String, value> tbl, HeapInter heap) throws SomeException
    {
        value val = expr.eval(tbl, heap);
        if(val.getType() instanceof RefType)
        {
            RefValue rval = (RefValue)val;
            int addr = rval.getAddr();
            if(heap.containsKey(addr))
            {
                return heap.get(addr);
            }
            else throw new SomeException(addr + " not in heap");

        }
        else throw new SomeException("not a refvalue");
    }
    public String toString()
    {
        return "read from heap " + expr.toString();
    }
    public Expr deepcopy()
    {
        HeapReadingExp evl = new HeapReadingExp(expr.deepcopy());
        return evl;
    }
}
