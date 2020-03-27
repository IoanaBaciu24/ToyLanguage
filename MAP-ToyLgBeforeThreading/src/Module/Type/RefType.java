package Module.Type;

import Module.Value.RefValue;
import Module.Value.Value;

public class RefType implements Type {

    private Type inner;
    public RefType(Type in)
    {
        inner = in;
    }
    public Type getInner() { return inner; }

    public boolean equals(Object another)
    {
        if (another instanceof RefType)
            return inner.equals(((RefType) another).getInner());
        else
            return false;
    }
    public String toString() { return "Ref(" +inner.toString()+")";}

    public Value defaultValue() {
        return new RefValue(inner, 0);
    }
}
