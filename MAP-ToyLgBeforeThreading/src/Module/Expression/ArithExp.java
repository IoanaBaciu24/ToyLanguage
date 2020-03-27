package Module.Expression;
import Container.Heap;
import Container.MyDictionary;
import Exceptions.*;
import Module.Value.*;
import Module.Type.*;

public class ArithExp implements Exp {

    private Exp e1;
    private Exp e2;

    private int op; //1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(int o, Exp E1, Exp E2)
    {
        e1 = E1;
        e2 = E2;
        op = o;
    }

    public Value eval(MyDictionary<String, Value> tbl, Heap heap) throws PancakeException {
        Value v1,v2;
        v1 = e1.eval(tbl, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op == 1)
                    return new IntValue(n1 + n2);
                if (op == 2)
                    return new IntValue(n1 - n2);
                if(op == 3)
                    return new IntValue(n1 * n2);
                if(op == 4)
                    if(n2 == 0)
                        throw new PancakeException("division by zero");
                    else
                        return new IntValue(n1/n2);
            }
            else
                throw new PancakeException("second operand is not an integer");
        }
        else
            throw new PancakeException("first operand is not an integer");

        return new IntValue(0);
//        return null;
    }

    public String toString()
    {
        String s = "";
        switch(op)
        {
            case(1):
            {
                s = "+";
                break;
            }
            case(2):
            {
                s = "-";
                break;
            }
            case(3):
            {
                s = "*";
                break;
            }
            case(4): {
                s = "/";
                break;
            }

        }
        return e1.toString() + " " + s + " " + e2.toString();
    }


}
