package biz.tugay.ctci.ch02.modal;

public class Node {
    public char val;
    public Node next;

    public Node(char val) {
        this.val = val;
    }

    public Node(char val, Node next) {
        this.val = val;
        this.next = next;
    }
}
