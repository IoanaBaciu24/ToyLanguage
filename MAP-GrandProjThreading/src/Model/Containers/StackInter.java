package Model.Containers;

import Model.Stmt.IStmt;

public interface StackInter<E> {
    public void push(E st);
    public E pop();
    public E peek();
    public boolean isEmpty();

    public String toString();
}
