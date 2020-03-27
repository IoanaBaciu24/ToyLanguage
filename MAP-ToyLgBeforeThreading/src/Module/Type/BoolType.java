package Module.Type;

import Module.Value.*;

public class BoolType implements Type {

    public boolean equals(Object another)
    {
        if (another instanceof BoolType)
            return true;
        else
            return false;
    }
    public String toString()
    {
        return "bool";
    }

    public Value defaultValue() {
        return new BoolValue(false);

    }
}
