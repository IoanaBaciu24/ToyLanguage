package Module.Type;

import Module.Value.*;

public class IntType implements Type {

    public boolean equals(Object another)
    {
        if (another instanceof IntType)
            return true;
        else
            return false;
    }
    public String toString()
    {
        return "int";
    }

    public Value defaultValue() {
        return new IntValue(0);

    }
}
