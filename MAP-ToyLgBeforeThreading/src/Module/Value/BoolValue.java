package Module.Value;

import Module.Type.BoolType;
import Module.Type.Type;

public class BoolValue implements Value {
    private boolean val;
    public BoolValue(boolean v)
    {
        val = v;
    }
    public boolean getVal()
    {
        return val;
    }
    public String toString()
    {
        return Boolean.toString(val);
    }
    public Type getType()
    {
        return new BoolType();
    }
}
