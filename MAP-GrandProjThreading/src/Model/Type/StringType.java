package Model.Type;

import Model.Value.StringValue;
import Model.Value.value;

public class StringType implements type {

    public boolean equals(Object another)
    {
        if(another instanceof StringType)
            return true;
        return false;
    }
    public String toString()
    {
        return "string";
    }
    public value defaultValue(){return new StringValue("");}
}
