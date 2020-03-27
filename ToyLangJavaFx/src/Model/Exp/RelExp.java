package Model.Exp;

import ExceptionThing.SomeException;
import Model.Containers.HeapInter;
import Model.Containers.MapInter;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.value;

public class RelExp implements Expr {
    Expr exp1;
    Expr exp2;
    String op;

    public RelExp(String o, Expr e1, Expr e2)
    {
        exp1 = e1;
        exp2 = e2;
        op = o;
    }

    public value eval(MapInter<String, value> tbl, HeapInter heap) throws SomeException
    {
        value v1, v2;
        v1 = exp1.eval(tbl, heap);
        //v2 = exp2.eval(tbl);

        if (v1.getType().equals(new IntType())) {
            v2 = exp2.eval(tbl, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1= i1.getVal();
                n2 = i2.getVal();
                if (op=="<") return new BoolValue(n1<n2);
                if (op =="<=") return new BoolValue(n1<=n2);
                if(op=="==") return new BoolValue(n1==n2);
                if(op=="!=") return new BoolValue(n1!=n2);
                if(op==">") return new BoolValue(n1>n2);
                if(op==">=") return new BoolValue(n1>=n2);

            }else
                throw new SomeException("second operand is not an integer");
        }else
            throw new SomeException("first operand is not an integer");

        return new IntValue(0);
    }
    public String toString(){
        return exp1.toString() + " " + op + " " + exp2.toString();
    }
    public Expr deepcopy()
    {
        RelExp evl = new RelExp(op, exp1.deepcopy(),exp2.deepcopy());
        return evl;
    }

    @Override
    public type typecheck(MapInter<String, type> typeEnv) throws SomeException {
        type t1, t2;
        t1 = exp1.typecheck(typeEnv);
        t2 = exp2.typecheck(typeEnv);

        if (t1.equals(new IntType())) {
            if (t2.equals(new IntType())) {
                return new BoolType();
            } else
                throw new SomeException("second operand is not an integer");
        }else
            throw new SomeException("first operand is not an integer");
    }
}
