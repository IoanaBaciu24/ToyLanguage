package Module.Value;

import Module.Type.*;

public class StringValue implements Value {

    private String val;
    public StringValue(String v) {
        val = v;
    }
    public String getVal(){ return val; }
    public Type getType() { return new StringType(); }

    public String toString() { return val; }
}
