package Model.Stmt;

import ExceptionThing.SomeException;
import Model.*;
import Model.Containers.MapInter;
import Model.Containers.MonMap;
import Model.Type.*;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.value;

import java.util.HashMap;

public class VarDeclStmt implements IStmt {

    String name;
    type typ;
    public VarDeclStmt(String n, type t)
    {
        name = n;
        typ = t;
    }
    public String toString()
    {
        return typ.toString() + " " + name;

    }
    public ProgState execute(ProgState prg) throws SomeException
    {

        MapInter<String, value> tbl = prg.getSymTable();

        IntType a = new IntType();
        BoolType b = new BoolType();
        StringType s = new StringType();

        if(typ.equals(a))
        {
            IntValue v = (IntValue) a.defaultValue();
            tbl.put(name,v);
        }
        else if(typ.equals(s))
        {
            StringValue str = (StringValue) s.defaultValue();
            tbl.put(name, str);
        }
        else if(typ instanceof RefType)
        {
            type ref = ((RefType)typ).getInner();
            value v = new RefType(ref).defaultValue();
            tbl.put(name,v);
        }
        else
        {
            BoolValue v2 = (BoolValue) b.defaultValue();
            tbl.put(name,v2);
        }

        //Todo cum o facut cristi
        /*
        value v = typ.defaultValue();*/
        //tbl.put(name, v);
        return null;
    }

    @Override
    public IStmt deepcopy() {
        VarDeclStmt evl = new VarDeclStmt(name, typ);
        return evl;
    }
}
