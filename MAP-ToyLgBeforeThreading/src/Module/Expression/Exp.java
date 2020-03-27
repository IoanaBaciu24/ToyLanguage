package Module.Expression;
import Container.*;
import Module.Value.*;
import Exceptions.PancakeException;

public interface Exp {
    public Value eval(MyDictionary<String, Value> table, Heap heap) throws PancakeException;
    public String toString();
}
