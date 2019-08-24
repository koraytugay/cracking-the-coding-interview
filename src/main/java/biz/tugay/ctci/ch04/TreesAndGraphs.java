package biz.tugay.ctci.ch04;

import java.util.*;

import static java.util.Optional.*;

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

    Node minimalTree(List<String> values) {
        if (values.isEmpty())
            return null;

        int pivotIndex = values.size() / 2;
        Node node = new Node(values.get(pivotIndex));
        ofNullable(minimalTree(values.subList(0, pivotIndex))).ifPresent(n -> node.children.add(n));
        ofNullable(minimalTree(values.subList(pivotIndex + 1, values.size()))).ifPresent(n -> node.children.add(n));

        return node;
    }

    private void depthFirstSearch(Node node, ArrayList<Node> visited) {
        if (!visited.contains(node)) {
            visited.add(node);
            for (Node child : node.children)
                depthFirstSearch(child, visited);
        }
    }
}
