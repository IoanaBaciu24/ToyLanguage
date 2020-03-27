package Model.Value;

import Model.Type.StringType;
import Model.Type.type;

import java.util.Objects;

public class StringValue implements value {

    String str;

    public StringValue(String v)
    {
        str = v;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) & Objects.equals(obj,this);
    }

    public type getType()
    {
        return new StringType();
    }
    public String getVal()
    {
        return str;
    }
    ///public value getValue();
    public String toString()
    {
        return str;
    }
    public value deepcopy()
    {
        StringValue s = new StringValue(str);
        return s;
    }
}
