package Module.Type;

import Module.Value.Value;

public interface Type {
    public boolean equals(Object other);
    public Value defaultValue();
}
