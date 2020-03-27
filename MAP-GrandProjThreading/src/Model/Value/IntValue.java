package Model.Value;

import Model.Type.IntType;
import Model.Type.type;

import java.util.Objects;

public class IntValue implements value {

    int val;
    public IntValue(int v){val =v;}
    public type getType()
    {
        return new IntType();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) & Objects.equals(obj, this);
        //check if type is IntValue
        //TODO ask about how to do this
        //both values or just type?


    }

    public int getVal(){return val;}
    public String toString()
    {
        return Integer.toString(val);
    }
    public value deepcopy()
    {
        IntValue ivl = new IntValue(val);

        return ivl;
    }

}
