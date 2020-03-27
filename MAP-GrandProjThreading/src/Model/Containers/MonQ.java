package Model.Containers;

import java.util.LinkedList;

public class MonQ<E> implements QueueInter<E> {

    LinkedList<E> lelist;

    public MonQ(){
        lelist = new LinkedList<E>();
    }

    @Override
    public void add(E elem) {
        lelist.add(elem);
    }
    public String toString()
    {
        return lelist.toString();
    }
}
