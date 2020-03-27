package Model.Stmt;

import ExceptionThing.SomeException;
import Model.Containers.HeapInter;
import Model.Containers.MapInter;
import Model.Exp.Expr;
import Model.ProgState;
import Model.Type.RefType;
import Model.Value.RefValue;
import Model.Value.value;

public class HWriteStmt implements IStmt {
    String var_name;
    Expr expr;

    public HWriteStmt(String s, Expr e)
    {
        var_name = s;
        expr=e;
    }


    public ProgState execute(ProgState state) throws SomeException
    {
        MapInter<String, value> tbl = state.getSymTable();
        HeapInter heap = state.getHeap();

        if(tbl.containsKey(var_name))
        {
            value val = tbl.get(var_name);
            if(val.getType() instanceof RefType)
            {
                int addr = ((RefValue)val).getAddr();

                if(heap.containsKey(addr))
                {
                    value evaluated = expr.eval(tbl,heap);
                    //System.out.println(evaluated.getType());
                    //System.out.println(val);
                    if(evaluated.getType().equals(((RefValue)val).getLocationType()))
                    {
                        heap.replace(addr, evaluated);
                    }
                    else throw new SomeException("different type");
                }
                else throw new SomeException("not in heap");            }
            else throw new SomeException("not refvalue");
        }
        else throw new SomeException("var not defined in symtbl");

        return null;
    }
    public String toString()
    {
        return "write to heap "+var_name+" "+expr.toString();
    }
    public IStmt deepcopy()
    {
        HWriteStmt evl = new HWriteStmt(var_name,expr.deepcopy());
        return evl;
    }
}
