package Container;

import Exceptions.PancakeException;

import java.util.Collection;
import java.util.HashMap;

public class MyDictionary<Key,T> implements MyIDictionary<Key, T> {
    private HashMap<Key,T> dictionary = new HashMap<Key, T>();
    @Override
    public boolean isEmpty() {
        return this.dictionary.isEmpty();
    }


    @Override
    public void put(Key o, T value) {
        this.dictionary.put(o, value);
    }

    @Override
    public int size() {
        return this.dictionary.size();
    }

    @Override
    public boolean isDefined(Key o) {
        return this.dictionary.containsKey(o);
    }

    @Override
    public T getValue(Key o) {
        return this.dictionary.get(o);
    }

    @Override
    public void update(Key o, T value) {
        this.dictionary.replace(o, value);
    }

    @Override
    public T lookUp(Key o) throws PancakeException {
        if(this.dictionary.containsKey(o))
            return this.dictionary.get(o);
        else throw new PancakeException("Variable is not defined");
    }

    @Override
    public String toString()
    {
        return dictionary.toString();
    }

    @Override
    public T remove(Key key) {
        return this.dictionary.remove(key);
    }

    @Override
    public Collection<T> values() {
        return dictionary.values();
    }


}
