package biz.tugay.ctci.ch04;

import java.util.*;

class TreesAndGraphs {

    List<Node> depthFirstSearch(Node node) {
        ArrayList<Node> visited = new ArrayList<>();
        depthFirstSearch(node, visited);
        return visited;
    }

    List<Node> breadthFirstSearch(Node node) {
        List<Node> visited = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>(Arrays.asList(node));

        Node element;
        while ((element = queue.poll()) != null)
            if (!visited.contains(element)) {
                visited.add(element);
                queue.addAll(element.children);
            }

        return visited;
    }

    private void depthFirstSearch(Node node, ArrayList<Node> visited) {
        if (!visited.contains(node)) {
            visited.add(node);
            for (Node child : node.children)
                depthFirstSearch(child, visited);
        }
    }
}
