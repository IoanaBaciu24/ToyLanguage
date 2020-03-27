package Container;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private Stack<T> actualStack = new Stack<T>();

    @Override
    public boolean isEmpty() {
        if(this.actualStack.isEmpty())
            return true;
        else return false;
    }

    @Override
    public T pop() {
        return actualStack.pop();
    }

    @Override
    public void push(T v) {
        actualStack.push(v);
    }

    @Override
    public String toString()
    {
        return actualStack.toString();
    }
}
