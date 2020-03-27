package Model.Containers;

import java.util.Collection;
import java.util.HashMap;

public class MonMap<F,S>  implements  MapInter<F,S>{

    HashMap<F,S> lemap;
    static int count = 0;

    public MonMap()
    {
        lemap = new HashMap<F,S>();
    }

    public void put(F f, S s)
    {
        lemap.put(f,s);
    }
    public boolean containsKey(F k)
    {
        return lemap.containsKey(k);
    }
    public void replace(F k, S v)
    {
        lemap.replace(k,v);
    }
    public S get(F k)
    {
        return lemap.get(k);
    }

    public void remove(F k)
    {
        lemap.remove(k);
    }

    public String toString()
    {
        return lemap.toString();
    }

    public Collection<S> values()
    {
        return lemap.values();
    }

    public MapInter<F,S> clone()
    {
        MapInter<F,S> clo = new MonMap<F,S>();
        for(F f: lemap.keySet())
        {
            clo.put(f,lemap.get(f));
        }
        return clo;

    }

}
