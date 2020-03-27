package Model.Containers;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MonQ<E> implements QueueInter<E> {

    List<E> lelist;

    public MonQ()
    {
        //lelist = new LinkedList<E>();
        lelist = Collections.synchronizedList(new LinkedList<E>());
    }
//
    @Override
    public synchronized void add(E elem) {
        lelist.add(elem);
    }
    public String toString()
    {
        return lelist.toString();
    }
    public List<E> getLelist()
    {
        return lelist;
    }
}
