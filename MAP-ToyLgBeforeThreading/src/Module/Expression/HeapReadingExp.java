package Module.Expression;

import Container.Heap;
import Container.MyDictionary;
import Exceptions.PancakeException;
import Module.Value.RefValue;
import Module.Value.Value;

public class HeapReadingExp implements Exp {
    private Exp exp;

    public HeapReadingExp(Exp ex) { exp = ex;}

    public Value eval(MyDictionary<String, Value> table, Heap heap) throws PancakeException {
        Value val = exp.eval(table, heap);
        if(val instanceof RefValue)
        {
            if(heap.isDefined(((RefValue) val).getAddr()))
            {
                return heap.getValue(((RefValue) val).getAddr());
            }
            else
                throw new PancakeException("No such address in heap");
        }
        else
            throw new PancakeException("Expression was not evaluated to ref");
    }

    public String toString() { return " readFromHeap " + exp.toString();}
}
