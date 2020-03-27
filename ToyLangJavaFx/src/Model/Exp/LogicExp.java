package Model.Exp;

import ExceptionThing.SomeException;
import Model.Containers.HeapInter;
import Model.Containers.MapInter;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.type;
import Model.Value.BoolValue;
import Model.Value.value;

public class LogicExp implements Expr {

    Expr e1;
    Expr e2;
    char op; // op = 1 if & and 2 if |

    public LogicExp(char o, Expr ex1, Expr ex2)
    {
        /*if(Objects.equals(o,'&'))
            op=1;
        else if(Objects.equals(o,'|'))
            op=2;*/

        op = o;
        e1=ex1;
        e2=ex2;
    }

    public value eval(MapInter<String, value> tbl, HeapInter heap) throws SomeException
    {
        value v1,v2;
        v1 = e1.eval(tbl, heap);
        if(v1.getType().equals(new BoolType()))
        {
            v2 = e2.eval(tbl, heap);
            if(v2.getType().equals(new BoolType()))
            {
                BoolValue i1 = (BoolValue)v1;
                BoolValue i2 = (BoolValue)v2;

                boolean b1, b2;
                b1 = i1.getVal();
                b2 = i2.getVal();

                if(op=='&') return new BoolValue(b1&b2);
                if(op=='|') return new BoolValue(b1|b2);


            }
            else throw new SomeException("not bool when must be bool");
        }
        else throw new SomeException("not bool when must be bool");

        return null;
    }
    public String toString()
    {
        String o;
        if(op==1)
            o=" & ";
        else o = " | ";
        return e1.toString() + o + e2.toString();
    }

    @Override
    public Expr deepcopy() {

        LogicExp evl = new LogicExp(op, e1.deepcopy(), e2.deepcopy());
        return evl;
    }

    @Override
    public type typecheck(MapInter<String, type> typeEnv) throws SomeException {
        type t1, t2;
        t1 = e1.typecheck(typeEnv);
        t2 = e2.typecheck(typeEnv);

        if (t1.equals(new IntType())) {
            if (t2.equals(new IntType())) {
                return new IntType();
            } else
                throw new SomeException("second operand is not a bool");
        }else
            throw new SomeException("first operand is not a bool");
    }
}
