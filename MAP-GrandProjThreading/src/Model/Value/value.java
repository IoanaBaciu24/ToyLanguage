package Model.Value;

import Model.Type.type;

import java.util.Objects;

public interface value {
   public type getType();
   ///public value getValue();
   public String toString();
   public value deepcopy();
   public boolean equals(Object a);
}
