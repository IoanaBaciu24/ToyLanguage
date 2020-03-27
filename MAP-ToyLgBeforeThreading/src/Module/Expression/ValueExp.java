package Module.Expression;

import Container.Heap;
import Container.MyDictionary;
import Module.Value.Value;

public class ValueExp implements Exp {
    private Value e;

    public ValueExp(Value aux)
    {
        e = aux;
    }
    public Value eval(MyDictionary<String, Value> tbl, Heap heap) {
        return e;
    }
    public String toString()
    {
        return e.toString();
    }
}
