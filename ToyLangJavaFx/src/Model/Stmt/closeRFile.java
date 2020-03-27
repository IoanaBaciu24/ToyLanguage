package Model.Stmt;

import ExceptionThing.SomeException;
import Model.Containers.HeapInter;
import Model.Containers.MapInter;
import Model.Exp.Expr;
import Model.ProgState;
import Model.Type.StringType;
import Model.Type.type;
import Model.Value.StringValue;
import Model.Value.value;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IStmt {

    Expr exp;
    public closeRFile(Expr e)
    {
        exp = e;
    }

    public ProgState execute(ProgState state) throws SomeException
    {
        MapInter<String, value> tbl = state.getSymTable();
        MapInter<StringValue, BufferedReader> ftbl = state.getFileTable();
        HeapInter heap = state.getHeap();
        value val = exp.eval(tbl, heap);
        if(val.getType().equals(new StringType()))
        {
            StringValue leString = (StringValue)val;
            //String leString = str.getVal();
            if(ftbl.containsKey(leString))
            {
                try {
                    BufferedReader br = ftbl.get(leString);
                    br.close();
                    ftbl.remove(leString);
                }
                catch (IOException e)
                {
                    throw new SomeException(e.toString());
                }

            }
            else throw new SomeException("not a file name");
        }
        else throw new SomeException("not string when I need string");

        return null;
    }
    public String toString()
    {
        return "close " + exp.toString();
    }
    public IStmt deepcopy()
    {
        closeRFile evl = new closeRFile(exp.deepcopy());
        return evl;
    }

    @Override
    public MapInter<String, type> typecheck(MapInter<String, type> typeEnv) throws SomeException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}
