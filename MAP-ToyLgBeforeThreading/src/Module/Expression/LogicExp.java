package Module.Expression;
import Exceptions.*;
import Module.Type.*;
import Module.Value.*;
import Container.*;
public class LogicExp implements Exp {
    private Exp e1;
    private Exp e2;
    private int op;// 1 = && ; 2 == ||

    public LogicExp(int o, Exp E1, Exp E2)
    {
        e1 = E1;
        e2 = E2;
        op = o;
    }

    public Value eval(MyDictionary<String, Value> tbl, Heap heap) throws PancakeException {
        Value v1, v2;
        v1 = e1.eval(tbl, heap);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(tbl, heap);
            if (v2.getType().equals(new BoolType())) {
                BoolValue i1 = (BoolValue)v1;
                BoolValue i2 = (BoolValue)v2;
                boolean n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op == 1)
                    return new BoolValue(n1 && n2);
                if (op == 2)
                    return new BoolValue(n1 || n2);
            }
            else
                throw new PancakeException("second operand is not a boolean");
        }
        else
            throw new PancakeException("first operand is not a boolean");
    return null;
    }

    public String toString()
    {
        String s = "";
        switch(op) {
            case (1): {
                s = "&&";
                break;
            }
            case (2): {
                s = "||";
                break;
            }
        }
        return e1.toString() + " " + s + " " + e2.toString();
    }
}

