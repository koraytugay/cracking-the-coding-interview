package biz.tugay.ctci.ch03.modal;

import static java.util.Optional.*;

public class StackImpl<T> implements Stack<T> {

    Node<T> head;

    private static class Node<T> {
        T val;
        Node<T> prev;

        public Node(T val, Node<T> prev) {
            this.val = val;
            this.prev = prev;
        }
    }

    @Override
    public T pop() {
        T val = head.val;
        head = head.prev;
        return val;
    }

    @Override
    public void push(T t) {
        head = new Node<>(t, head);
    }

    @Override
    public T peek() {
        return ofNullable(head).orElse(new Node<>(null, null)).val;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

}
