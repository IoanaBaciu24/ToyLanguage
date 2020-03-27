package Module.Expression;

import Container.Heap;
import Container.MyDictionary;
import Exceptions.PancakeException;

import Module.Value.*;

public class VarExp implements Exp {
    private String id;
    public VarExp(String s)
    {
        id = s;
    }
    public Value eval(MyDictionary<String, Value> tbl, Heap heap) throws PancakeException {
        return tbl.lookUp(id);
    }
    public String toString()
    {
        return id;
    }
}
