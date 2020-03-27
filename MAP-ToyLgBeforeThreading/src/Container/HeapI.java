package Container;

import Exceptions.PancakeException;
import Module.Value.Value;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface HeapI {
    public boolean isEmpty();
    public void put(Value value);
    public int size();
    public boolean isDefined(Integer key);
    public Value getValue(Integer key);
    public void update(Integer key, Value value);
    public Value lookUp(Integer key) throws PancakeException;
    public String toString();
    public Value remove(Integer key);
    public Integer getAddr();
    public HashMap<Integer, Value> getContent();
    public void setContent(HashMap<Integer, Value> map);

    Collection<Value> values();
}
