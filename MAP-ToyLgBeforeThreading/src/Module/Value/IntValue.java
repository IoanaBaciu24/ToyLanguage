package Module.Value;

import Module.Type.IntType;
import Module.Type.Type;

public class IntValue implements Value {
    private int val;
    public IntValue(int v)
    {
        val = v;
    }
    public int getVal()
    {
        return val;
    }
    public String toString()
    {
        return Integer.toString(val);
    }
    public Type getType()
    {
        return new IntType();
    }

}
