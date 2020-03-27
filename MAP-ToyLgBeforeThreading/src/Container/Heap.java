package Container;

import Exceptions.PancakeException;
import Module.Value.Value;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Heap implements HeapI {
    private HashMap<Integer, Value> heap;
    private Integer firstEmpty;

    public Heap()
    {
        heap = new HashMap<Integer, Value>();
        firstEmpty = 0;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void put(Value value) {
        firstEmpty += 1;
        heap.put(firstEmpty, value);
    }

    public int size() {
        return heap.size();
    }

    public boolean isDefined(Integer key) {
        return heap.containsKey(key);
    }

    public Value getValue(Integer key) {
        return heap.get(key);
    }

    public void update(Integer key, Value value) {
        heap.replace(key, value);
    }

    public Value lookUp(Integer key) throws PancakeException {
        if(this.heap.containsKey(key))
            return this.heap.get(key);
        else throw new PancakeException("Variable is not defined");
    }

    public Value remove(Integer key) {
        return heap.remove(key);
    }

    public Integer getAddr() {
        return firstEmpty ;
    }

    public HashMap<Integer, Value> getContent() {
        HashMap<Integer, Value> map = new HashMap<Integer, Value>();
        map = this.heap;
        return map;
    }

    public String toString() { return heap.toString(); }

    public void setContent(HashMap<Integer, Value> map) {
        heap = map;
    }

    @Override
    public Collection<Value> values() {
        return heap.values();
    }
}
