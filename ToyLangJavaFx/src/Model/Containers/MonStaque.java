package Model.Containers;

import Model.Stmt.IStmt;

import java.util.Stack;

public class MonStaque<E> implements StackInter<E> {

    Stack<E> leStaque;

    public MonStaque()
    {
        leStaque = new Stack<E>();
    }

    @Override
    public void push(E st) {
        leStaque.push(st);
    }

    @Override
    public E pop() {
        return leStaque.pop();
    }

    @Override
    public E peek() {
        return leStaque.peek();
    }
    @Override
    public boolean isEmpty()
    {
        return leStaque.isEmpty();
    }

    @Override
    public String toString() {
        return leStaque.toString();
    }
}
