package biz.tugay.ctci.ch04;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Node {

    String val;

    List<Node> children = new ArrayList<>();

    public Node(String val) {
        this.val = val;
    }

    public Node left() {
        try {
            return children.isEmpty() ? null : children.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public Node right() {
        try {
            return children.isEmpty() ? null : children.get(1);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        return val.equals(((Node) o).val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }
}
