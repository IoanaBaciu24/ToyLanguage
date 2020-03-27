package Model.Type;

import Model.Value.IntValue;
import Model.Value.value;

public class IntType implements type {

    public boolean equals(Object a)
    {

        if(a instanceof IntType)
            return true;
        return false;
    }

    public String toString()
    {
        return "int";
    }

    @Override
    public value defaultValue() {
        IntValue val = new IntValue(0);
        return val;
    }
}
