package biz.tugay.ctci.ch04;

import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

class TreesAndGraphs {

    List<Node> depthFirstSearch(Node node) {
        ArrayList<Node> visited = new ArrayList<>();
        depthFirstSearch(node, visited);
        return visited;
    }

    List<Node> breadthFirstSearch(Node node) {
        List<Node> visited = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>(asList(node));

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

    List<List<Node>> listOfDepths(Node node) {
        List<List<Node>> listOfDepths = new LinkedList<>();
        List<Node> nodes = new ArrayList<>(asList(node));

        while (!nodes.isEmpty()) {
            listOfDepths.add(new ArrayList<>(nodes));
            nodes = nodes.stream().flatMap(n -> n.children.stream()).filter(Objects::nonNull).collect(toList());
        }

        return listOfDepths;
    }

    boolean checkBalanced(Node node) {
        return abs(deepest(node.left()) - deepest(node.right())) < 2;
    }

    boolean isBinarySearchTree(Node node) {
        if (node == null)
            return true;

        if (node.left() != null && node.left().val.compareTo(node.val) > -1)
            return false;

        if (node.right() != null && node.right().val.compareTo(node.val) < 1)
            return false;

        return isBinarySearchTree(node.left()) && isBinarySearchTree(node.right());
    }

    long sumPaths(Node node, int target) {
        Map<Node, Set<Integer>> nodeSums = new HashMap<>();
        sumPathsRecursive(node, nodeSums, new HashSet<>());
        return nodeSums.values().stream().flatMap(Collection::stream).filter(integer -> integer == target).count();
    }

    // Determine if t2 is a subtree of t1
    boolean isSubtree(Node t1, Node t2) {
        if (t1 == null || t2 == null)
            return false;

        boolean isLeftSubtree = false;
        boolean isRightSubtree = false;
        if (t1.val.equals(t2.val)) {
            isLeftSubtree = ((t1.left() == null) && (t2.left() == null)) || isSubtree(t1.left(), t2.left());
            isRightSubtree = ((t1.right() == null) && (t2.right() == null)) || isSubtree(t1.right(), t2.right());
        }

        if (isLeftSubtree && isRightSubtree)
            return true;

        return isSubtree(t1.left(), t2) || isSubtree(t1.right(), t2);
    }

    private void depthFirstSearch(Node node, ArrayList<Node> visited) {
        if (!visited.contains(node)) {
            visited.add(node);
            for (Node child : node.children)
                depthFirstSearch(child, visited);
        }
    }

    private int deepest(Node node) {
        return node == null ? 0 : node.children.isEmpty() ? 1 : max(deepest(node.left()), deepest(node.right())) + 1;
    }

    private void sumPathsRecursive(Node node, Map<Node, Set<Integer>> allNodeSums, Set<Integer> parentNodeSums) {
        if (node == null)
            return;

        Integer nodeVal = parseInt(node.val);
        Set<Integer> nodeSums = new HashSet<>();
        nodeSums.add(nodeVal);
        parentNodeSums.forEach(parentSum -> nodeSums.add(nodeVal + parentSum));
        allNodeSums.put(node, nodeSums);
        sumPathsRecursive(node.left(), allNodeSums, nodeSums);
        sumPathsRecursive(node.right(), allNodeSums, nodeSums);
    }

}
