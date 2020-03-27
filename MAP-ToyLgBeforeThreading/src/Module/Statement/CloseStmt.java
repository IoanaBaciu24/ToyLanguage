package Module.Statement;
import Container.Heap;
import Container.MyDictionary;
import Exceptions.PancakeException;
import Module.*;
import Module.Type.*;
import Module.Expression.*;
import Module.Value.*;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseStmt implements IStmt{

    private Exp exp;

    public CloseStmt(Exp e)
    {
        exp = e;
    }

    public PrgState execute(PrgState state) throws PancakeException {
        MyDictionary<String, Value> syms = state.getSymTable();
        MyDictionary<StringValue, BufferedReader> ftbl = state.getFileTable();
        Heap heap = state.getHeap();
        Value val = exp.eval(syms, heap);
        if(val.getType().equals(new StringType())) {
            StringValue string = (StringValue)val;
            if(ftbl.isDefined(string))
            {
                BufferedReader br = ftbl.lookUp(string);
                try
                {
                    br.close();
                    ftbl.remove(string);
                }
                catch (IOException e)
                {
                    throw new PancakeException("File cannot be opened!");
                }
            }
            else
                throw new PancakeException("No such file was opened!");
        }
        else
            throw new PancakeException("Filename must be string");
        return state;
    }

    public String toString()
    {
        return  "close " + exp.toString();
    }
}
