package biz.tugay.ctci.ch04;

import java.util.ArrayList;
import java.util.List;

class Node {
    String val;
    List<Node> children = new ArrayList<>();

    public Node(String val) {
        this.val = val;
    }

    public Node left() {
        if (children.isEmpty()) {
            return null;
        }
        try {
            return children.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public Node right() {
        if (children.isEmpty()) {
            return null;
        }
        try {
            return children.get(1);
        } catch (Exception e) {
            return null;
        }
    }

}
