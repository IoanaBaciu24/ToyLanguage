package Module.Expression;

import Container.Heap;
import Container.MyDictionary;
import Exceptions.PancakeException;
import Module.Type.IntType;
import Module.Value.*;

public class RelExp implements Exp {
    private Exp e1, e2;
    private int op;// < 1, <= 2, == 3, != 4, > 5, >= 6

    public RelExp(int o, Exp E1, Exp E2)
    {
        op = o;
        e1 = E1;
        e2 = E2;
    }

    public Value eval(MyDictionary<String, Value> tbl, Heap heap) throws PancakeException {
        Value v1, v2;
        v1 = e1.eval(tbl, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                switch(op)
                {
                    case(1):
                    {
                        return new BoolValue(n1 < n2);
                    }
                    case(2):
                    {
                        return new BoolValue(n1 <= n2);
                    }
                    case(3):
                    {
                        return new BoolValue(n1 == n2);
                    }
                    case(4):
                    {
                        return new BoolValue(n1 != n2);
                    }
                    case(5):
                    {
                        return new BoolValue(n1 > n2);
                    }
                    case(6):
                    {
                        return new BoolValue(n1 >= n2);
                    }
                }
            }
            else
                throw new PancakeException("Second argument is not int\n");
        }
        else
            throw new PancakeException("First argument is not int\n");
        return new BoolValue(false);
//        return null;
    }

    public String toString()
    {
        String s = "";
        switch(op)
        {
            case(1):
            {
                s = "<";
                break;
            }
            case(2):
            {
                s = "<=";
                break;
            }
            case(3):
            {
                s = "==";
                break;
            }
            case(4):
            {
                s = "!=";
                break;
            }
            case(5):
            {
                s = ">";
                break;
            }
            case(6):
            {
                s = ">=";
                break;
            }
        }
        return e1.toString() + " " + s + " " + e2.toString();
    }
}
