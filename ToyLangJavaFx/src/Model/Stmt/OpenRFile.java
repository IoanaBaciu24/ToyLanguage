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
import java.nio.file.Files;
import java.nio.file.Paths;

public class OpenRFile implements IStmt {

    Expr exp;
    public OpenRFile(Expr e)
    {
        exp = e;
    }

    public ProgState execute(ProgState state) throws SomeException
    {
        MapInter<String, value> tbl = state.getSymTable();
        MapInter<StringValue, BufferedReader> ftbl= state.getFileTable();
        HeapInter heap = state.getHeap();
        value val = exp.eval(tbl, heap);
        if(val.getType().equals(new StringType()))
        {
            StringValue leString = (StringValue)val;
            //String leString = str.getVal();
            if(!(ftbl.containsKey(leString)))
            {
                try {
                    String lleString = leString.getVal();
                    BufferedReader br = Files.newBufferedReader(Paths.get(lleString))  ;
                    ftbl.put(leString,br);
                }
                catch (IOException e)
                {
                    throw new SomeException(e.toString());
                }
            }
            else throw new SomeException("file already open");
        }
        else throw new SomeException("not a string when it must be string");

        return null;

    }
    public String toString(){
        return "opening " + exp.toString();
    }
    public IStmt deepcopy()
    {
        OpenRFile evl = new OpenRFile(exp.deepcopy());
        return evl;
    }

    @Override
    public MapInter<String, type> typecheck(MapInter<String, type> typeEnv) throws SomeException {
        type typeExp=exp.typecheck(typeEnv);
        return typeEnv;
    }
}
