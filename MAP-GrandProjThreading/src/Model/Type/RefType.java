package Model.Type;

import Model.Value.RefValue;
import Model.Value.value;
import com.sun.jdi.Value;

public class RefType implements type {
    type inner;
    public RefType(type inner) {this.inner=inner;}
    public type getInner() {return inner;}
    public boolean equals(Object another){
        if (another instanceof RefType)
        {
            return inner.equals(((RefType) another).getInner());
        }
        else
            return false;
    }
    public String toString() { return "Ref(" +inner.toString()+")";}
    public value defaultValue() { return new RefValue(0,inner);}

}
