package Model.Value;

import Model.Type.RefType;
import Model.Type.type;

import java.util.Objects;

public class RefValue implements value {

    int address;
    type locationType;
    public RefValue(int a, type l)
    {
        address = a;
        locationType = l;
    }
    public String toString()
    {
        return Integer.toString(address) + " " + locationType.toString();
    }
    public value deepcopy()
    {
        RefValue evl = new RefValue(address,locationType);
        return evl;
    }
    public boolean equals(Object a)
    {
        //todo e suficient sa face asa override?
        return super.equals(a) & Objects.equals(a,this);
    }
    public int getAddr() {return address;}
    public type getType() { return new RefType(locationType);}

    public type getLocationType() {
        return locationType;
    }
}
