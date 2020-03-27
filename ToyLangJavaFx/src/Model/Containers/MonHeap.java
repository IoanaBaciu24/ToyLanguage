package Model.Containers;

import Model.Value.IntValue;
import Model.Value.value;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MonHeap implements HeapInter {

    Map<Integer, value> leheap;
    Integer count;
    //TODO ATOMIC INT & syncr
    public MonHeap()
    {
        leheap = new ConcurrentHashMap<Integer,value>();
        count = 0;
    }

    public synchronized void put(value elem)
    {
        count = count+1;
        leheap.put(count,elem);
    }

    public boolean containsKey(int k)
    {
        Integer i = k;
        return leheap.containsKey(i);
    }
    public void replace(int k, value v)
    {
        Integer i=k;
        leheap.replace(i,v);

    }
    public value get(int k)
    {
        return leheap.get(k);
    }
    public String toString()
    {
        return leheap.toString();
    }
    public void remove(int k)
    {
        leheap.remove(k);
    }

    @Override
    public int getAddr() {
        return count;
    }
    public Collection<value> values()
    {
        return leheap.values();
    }

    @Override
    public void setContent(Map<Integer, value> m) {
        leheap = m;
    }
    public Map<Integer, value> getContent()
    {
        return leheap;
    }
}
