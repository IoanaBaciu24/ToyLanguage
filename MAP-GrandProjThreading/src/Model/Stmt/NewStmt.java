package Model.Stmt;

import ExceptionThing.SomeException;
import Model.Containers.HeapInter;
import Model.Containers.MapInter;
import Model.Exp.Expr;
import Model.ProgState;
import Model.Type.RefType;
import Model.Value.RefValue;
import Model.Value.value;

public class NewStmt implements IStmt {
    String var_name;
    Expr expr;
    public NewStmt(String var, Expr e)
    {
        var_name = var;
        expr=e;
    }

    public ProgState execute(ProgState state) throws SomeException
    {
        MapInter<String, value> tbl = state.getSymTable();
        HeapInter heap  = state.getHeap();
        if(tbl.containsKey(var_name))
        {
            value val = tbl.get(var_name);
            if(val.getType() instanceof RefType)
            {
                value alt_val = expr.eval(tbl, heap);
                if(alt_val.getType().equals(((RefValue)val).getLocationType()))
                {
                    heap.put(alt_val);
                    int key = heap.getAddr();
                    RefValue nou = new RefValue(key, alt_val.getType());
                    tbl.replace(var_name, nou);
                }
                else throw new SomeException("not the same type");
            }
            else throw new SomeException("not ref type");

        }
        else throw new SomeException(var_name + " is not in symtable");

        return null;

    }
    public String toString()
    {
        return "NEW " + var_name + " "+ expr.toString();
    }
    public IStmt deepcopy()
    {
        NewStmt evl = new NewStmt(var_name, expr.deepcopy());
        return evl;
    }
}
