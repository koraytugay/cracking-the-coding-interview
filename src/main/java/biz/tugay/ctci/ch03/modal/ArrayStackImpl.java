package biz.tugay.ctci.ch03.modal;

import java.util.ArrayList;

public class ArrayStackImpl<T> implements Stack<T> {

    ArrayList<T> data = new ArrayList<>();
    int head = -1;

    @Override
    public T pop() {
        return isEmpty() ? null : data.remove(head--);
    }

    @Override
    public void push(T t) {
        data.add(++head, t);
    }

    @Override
    public T peek() {
        return isEmpty() ? null : data.get(head);
    }

    @Override
    public boolean isEmpty() {
        return head == -1;
    }

}
