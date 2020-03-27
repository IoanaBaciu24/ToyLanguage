package Model.Type;


import Model.Value.value;

public interface type {
    public boolean equals(Object another);
    public String toString();
    //public String getType();
    //public type deepcopy();
    public value defaultValue();
}
