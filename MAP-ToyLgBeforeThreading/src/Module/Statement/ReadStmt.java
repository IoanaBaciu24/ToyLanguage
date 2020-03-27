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

public class ReadStmt implements IStmt {
    private String var_name;
    private Exp exp;

    public ReadStmt(String file, Exp e)
    {
        var_name = file;
        exp = e;
    }

    public PrgState execute(PrgState state) throws PancakeException {
        MyDictionary<String, Value> sym = state.getSymTable();
        MyDictionary<StringValue, BufferedReader> ftbl = state.getFileTable();
        Heap heap = state.getHeap();
        if(sym.isDefined(var_name))
        {
            Value v2 = exp.eval(sym, heap);
            if(v2.getType().equals(new StringType()))
            {
                StringValue string = (StringValue)v2;
                if(ftbl.isDefined(string))
                {
                    BufferedReader br = ftbl.lookUp(string);
                    try {
                        String line = br.readLine();
                        if(line == null)
                        {
                            Value val = new IntValue(0);
                            sym.update(var_name, val);
                        }
                        else
                        { try {
                            Value val = new IntValue(Integer.parseInt(line));
                            sym.update(var_name, val);
                        }
                        catch(NumberFormatException e)
                        {
                            Value val = new IntValue(0);
                            sym.update(var_name, val);
                        }
                        }
                    }
                    catch (IOException e)
                    {
                        throw new PancakeException(e.toString());
                    }
                }
                else throw new PancakeException("No such file  was opened");
            }
            else throw new PancakeException("Filename must be string!");
        }
        else throw new PancakeException("Read data must be int");

        return state;
    }

    public String toString()
    {
        return var_name + " " + exp.toString();
    }
}
