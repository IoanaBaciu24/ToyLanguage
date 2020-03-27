package Module.Value;
import Module.Type.*;

public class RefValue implements Value {
    private int address;
    private Type locationType;

    public RefValue(Type loc, int addr)
    {
        locationType = loc;
        address = addr;
    }

    public Type getType() {
        return new RefType(locationType);
    }

    public Object getVal() {
        return address;
    }

    public Type getLocationType()
    {
        return locationType;

    }

    public int getAddr() {
        return address;
    }

    public String toString() { return "locType " + locationType.toString() + " at addr " + address;
    }
}
