package biz.tugay.ctci.ch04;

import java.util.ArrayList;
import java.util.List;

class Node {
    String val;
    List<Node> children = new ArrayList<>();

    public Node(String val) {
        this.val = val;
    }
}
