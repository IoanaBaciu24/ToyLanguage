package Model.Stmt;

import ExceptionThing.SomeException;
import Model.Containers.HeapInter;
import Model.Containers.MapInter;
import Model.Exp.Expr;
import Model.ProgState;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Type.type;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.value;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements IStmt {

    Expr exp;
    String var_name;

    public readFile(Expr e, String n)
    {
        exp = e;
        var_name = n;
    }
    public ProgState execute(ProgState state) throws SomeException
    {
        MapInter<String, value> tbl = state.getSymTable();
        MapInter<StringValue, BufferedReader> ftbl = state.getFileTable();
        HeapInter heap = state.getHeap();
        if(tbl.containsKey(var_name))
        {
            value v = tbl.get(var_name);
            if(v.getType().equals(new IntType()))
            {
                value v2 = exp.eval(tbl, heap);
                if(v2.getType().equals(new StringType()))
                {
                    StringValue leString = (StringValue)v2;
                    //String leString = str.getVal();
                    if(ftbl.containsKey(leString))
                    {
                        BufferedReader br = ftbl.get(leString);
                        try {
                            String line = br.readLine();
                            if(line == null )
                            {
                                value val = new IntValue(0);
                                tbl.replace(var_name,val);
                            }
                            else
                            {
                                try {
                                    value val = new IntValue(Integer.parseInt(line));
                                    tbl.replace(var_name, val);
                                }
                                catch (NumberFormatException e)
                                {
                                    value val = new IntValue(0);
                                    tbl.replace(var_name,val);
                                }
                            }


                        }
                        catch (IOException e)
                        {
                            throw new SomeException(e.toString());
                        }
                    }
                    else throw new SomeException("m-am saturat de atatea exceptii");
                }
                else throw new SomeException("not string when must be string");
            }
            else throw new SomeException("not int when it must be int");
        }
        else throw new SomeException("variable name not defined");


        return null;
    }
    public String toString()
    {
        return "reading " + var_name + " "+ exp.toString();
    }
    public IStmt deepcopy()
    {
        readFile evl = new readFile(exp.deepcopy(), var_name);
        return evl;

    }

    @Override
    public MapInter<String, type> typecheck(MapInter<String, type> typeEnv) throws SomeException {
        type typeVar=typeEnv.get(var_name);
        type typeExp=exp.typecheck(typeEnv);
        if(typeVar.equals(new IntType()) && typeExp.equals(new StringType()))
            return typeEnv;
        else throw new SomeException("Reading File:the type of variable name or exp are wrong!");
    }
}
