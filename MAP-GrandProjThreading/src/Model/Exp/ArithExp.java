package Model.Exp;

import ExceptionThing.SomeException;
import Model.Containers.HeapInter;
import Model.Containers.MapInter;
import Model.Type.IntType;
import Model.Value.IntValue;
import Model.Value.value;

public class ArithExp implements Expr {

    Expr e1;
    Expr e2;
    char op; //1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(char o, Expr ex1, Expr ex2)

    {

       /* if (Objects.equals(o, '+'))
            op = 1;
           else if (Objects.equals(o, '-'))
               op=2;
               else if (Objects.equals(o, '*'))
                   op=3;
                   else if (Objects.equals(o, '/'))
                       op=4;*/
        op = o;
        e1=ex1;
        e2=ex2;
    }

    public value eval(MapInter<String, value> tbl, HeapInter heap) throws SomeException
    {
        value v1,v2;
        v1= e1.eval(tbl, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1= i1.getVal();
                n2 = i2.getVal();
                if (op=='+') return new IntValue(n1+n2);
                if (op =='-') return new IntValue(n1-n2);
                if(op=='*') return new IntValue(n1*n2);
                if(op=='/')
                    if(n2==0) throw new SomeException("division by zero");
                    else return new IntValue(n1/n2);
            }else
                throw new SomeException("second operand is not an integer");
        }else
            throw new SomeException("first operand is not an integer");

        return new IntValue(0);
    }

    public String toString() {
    String o;
    if(op==1)
        o="+";
    else if (op==2)
        o="-";
    else if(op==3)
        o="*";
    else o="/";

    return e1.toString() + o + e2.toString();
    }

    @Override
    public Expr deepcopy() {
        ArithExp evl = new ArithExp(op,e1.deepcopy(), e2.deepcopy());
        return evl;
    }
}
