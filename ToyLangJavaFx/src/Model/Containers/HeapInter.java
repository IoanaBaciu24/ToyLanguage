package Model.Containers;

import Model.Value.value;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public interface HeapInter {
    public void put(value s);
    public boolean containsKey(int k);
    public void replace(int k, value v);
    public value get(int k);
    public String toString();
    public void remove(int k);
    public int getAddr();
    public Collection<value> values();
    public void setContent(Map<Integer, value> m);
    public Map<Integer,value> getContent();
    //TODO SYNCHRONIZE THE I/O METHODS FROM THE SHARED RESOURCES
    //TODO CONCURRENT HASHMAP THING

}
