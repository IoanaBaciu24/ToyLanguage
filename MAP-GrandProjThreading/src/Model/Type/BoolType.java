package Model.Type;

import Model.Value.BoolValue;
import Model.Value.value;

public class BoolType implements type {
    public boolean equals(Object a)
    {
        if(a instanceof BoolType)
            return true;
        return false;
    }
    public String toString()
    {
        return "bool";
    }

    @Override
    public value defaultValue() {
        BoolValue val = new BoolValue(false);
        return val;
    }
}
