package Model.Value;

import Model.Type.BoolType;
import Model.Type.type;

import java.util.Objects;

public class BoolValue implements value {

    boolean val;
    public BoolValue(boolean v){val =v;}
    public type getType()
    {
        return new BoolType();
    }

    public boolean getVal(){return val;}
    public String toString()
    {
        return String.valueOf(val);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) & Objects.equals(obj,this);
    }

    @Override
    public value deepcopy() {
        BoolValue bvl = new BoolValue(val);
        return bvl;
    }
}
