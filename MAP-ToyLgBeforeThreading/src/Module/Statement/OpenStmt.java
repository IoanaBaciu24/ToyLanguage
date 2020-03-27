package Module.Statement;
import Container.Heap;
import Container.MyDictionary;
import Exceptions.PancakeException;
import Module.*;
import Module.Type.*;
import Module.Expression.*;
import Module.Value.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenStmt implements IStmt{
    private Exp exp;

    public OpenStmt(Exp e)
    {
        exp = e;
    }

    public PrgState execute(PrgState state) throws PancakeException {
        MyDictionary<String, Value> syms = state.getSymTable();
        MyDictionary<StringValue, BufferedReader> ftbl = state.getFileTable();
        Heap heap = state.getHeap();
        Value val = exp.eval(syms, heap);
        if(val.getType().equals(new StringType())) {
            if(ftbl.isDefined((StringValue) val) == false)
            {
                try
                {
                    BufferedReader br = new BufferedReader(new FileReader(val.toString()));
                    ftbl.put((StringValue) val, br);
                }
                catch (IOException e)
                {
                    throw new PancakeException("File cannot be opened!");
                }
            }
            else
                throw new PancakeException("Filename already exits!");
        }
        else
            throw new PancakeException("File name must be string!");
        return state;
    }

    public String toString()
    {
        return "open " + exp.toString();
    }
}
