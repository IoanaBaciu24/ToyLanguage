package Module.Statement;
import Container.Heap;
import Container.MyDictionary;
import Exceptions.PancakeException;
import Module.*;
import Module.Expression.*;

import Module.Type.RefType;
import Module.Type.Type;
import Module.Value.*;

import java.sql.Ref;

public class NewStmt implements IStmt {
    private String var_name;
    private Exp exp;

    public NewStmt(String var, Exp e)
    {
        var_name = var;
        exp = e;
    }

    public String toString(){
        return "new( " + var_name + ", " + exp + " )";
    }

    public PrgState execute(PrgState state) throws PancakeException {
        MyDictionary<String, Value> sym = state.getSymTable();
        Heap heap = state.getHeap();
        if(sym.isDefined(var_name)) {

            if(sym.getValue(var_name).getType() instanceof RefType) {

                Value val = exp.eval(sym, heap);
                Value var = sym.getValue(var_name);
                System.out.println(val.getType() + "\n" + ((RefValue)var).getLocationType());

                if(!(val.getType().equals(((RefValue)var).getLocationType()))) {
                    //System.out.println(val.getType() + "\n" + ((RefValue)var).getLocationType());

                   throw new PancakeException(var_name + " and result of " + exp.toString() + " are of two different types");
                    //state.setSymTable(sym);
                    //state.setHeap(heap);
                }
                else {
                    heap.put(val);
                    Integer heapAddress = heap.getAddr();
                    //sym.put(var_name, new RefValue(var.getType(), heapAddress));
                    sym.update(var_name, new RefValue(var.getType(), heapAddress));
                }
            }
            else throw new PancakeException("Give variable is not of type ref");
        }
        else throw new PancakeException(var_name + " was not found");

        return state;
    }
}
