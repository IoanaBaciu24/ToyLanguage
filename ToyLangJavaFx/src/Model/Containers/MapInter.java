package Model.Containers;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MapInter<F,S> {
    public void put(F f, S s);
    public boolean containsKey(F k);
    public void replace(F k, S v);
    public S get(F k);
    public String toString();
    public void remove(F k);
    public Collection<S> values();
    public MapInter<F,S> clone();
    public Set<F> keySet();
    public Iterable<Map.Entry<F, S>> getAll();
}
