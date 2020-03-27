package Module.Statement;
import Container.Heap;
import Container.MyDictionary;
import Exceptions.PancakeException;
import Module.*;
import Module.Expression.*;

import Module.Type.RefType;
import Module.Value.*;

public class HeapWriteStmt implements IStmt {
    private String var_name;
    private Exp exp;

    public HeapWriteStmt(String var, Exp e)
    {
        var_name = var;
        exp = e;
    }

    public PrgState execute(PrgState state) throws PancakeException {
        MyDictionary<String, Value> sym = state.getSymTable();
       Heap heap = state.getHeap();

        Value heapAddress;

        if(sym.isDefined(var_name) && sym.getValue(var_name) instanceof RefValue)
        {
            heapAddress = sym.getValue(var_name);
            Integer aux = (Integer)((RefValue)heapAddress).getVal();

            if(heap.isDefined(aux))
            {
                Value evall = exp.eval(sym, heap);
                Value var = sym.getValue(var_name);

                if(evall.getType().equals(((RefValue)var).getLocationType()))
                {
                    heap.update(aux, evall);
                }
                else throw new PancakeException(var_name + " and result of " + exp.toString() + " are of two different types after eval");
            }
            else
            {
                throw new PancakeException("Invalid address!");
            }
        }
        else
        {
            throw new PancakeException("Variable " + var_name + " not found or it is not of ref type");
        }
        return state;
    }

    public String toString() { return "Heap write in " + var_name + " expression " +exp.toString();}
}
