package Container;

import Exceptions.PancakeException;

import java.util.Collection;

public interface MyIDictionary<Key,T> {
    public boolean isEmpty();
    public void put(Key key, T value);
    public int size();
    public boolean isDefined(Key key);
    public T getValue(Key key);
    public void update(Key key, T value);
    public T lookUp(Key key) throws PancakeException;
    public String toString();
    public T remove(Key key);

    Collection<T> values();
}
