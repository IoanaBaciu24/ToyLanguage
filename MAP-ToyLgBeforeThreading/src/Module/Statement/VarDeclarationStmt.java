package Module.Statement;
import Container.MyDictionary;
import Exceptions.PancakeException;
import Module.Type.*;
import Module.Value.*;
import Module.Expression.*;
import Module.PrgState;

import javax.print.attribute.standard.PrinterMakeAndModel;
import java.util.HashMap;
import java.util.Stack;

public class VarDeclarationStmt implements IStmt{
    private String name;
    private Type type;

    public VarDeclarationStmt(String nam, Type tip)
    {
        name = nam;
        type = tip;
    }

    public String toString()
    {
        return type.toString() +"  "+name;
    }

    public PrgState execute(PrgState state) throws PancakeException {
        MyDictionary<String, Value> syms = state.getSymTable();
        if(type instanceof RefType)
        {
            Type ref = ((RefType)type).getInner();
            Value v = new RefType(ref).defaultValue();
            syms.put(name, type.defaultValue());
        }
        else syms.put(name, type.defaultValue());
        return state;
    }
}
