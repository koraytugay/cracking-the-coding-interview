package biz.tugay.ctci.ch04;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TreesAndGraphsTest {

    TreesAndGraphs treesAndGraphs = new TreesAndGraphs();

    Node a, b, c, d, e, f, g;

    @Before
    public void before() {
        a = new Node("0");
        b = new Node("1");
        c = new Node("2");
        d = new Node("3");
        e = new Node("4");
        f = new Node("5");
        g = new Node("6");
    }

    @Test
    public void depthFirstSearch() {
        sampleGraph();
        assertThat(asString(treesAndGraphs.depthFirstSearch(a)), equalTo("013245"));
    }

    @Test
    public void depthFirstSearch_Cyclic() {
        a.children.add(b);
        b.children.add(c);
        c.children.add(d);
        d.children.add(a);
        assertThat(asString(treesAndGraphs.depthFirstSearch(a)), equalTo("0123"));
    }

    @Test
    public void breathFirstSearch() {
        sampleGraph();
        assertThat(asString(treesAndGraphs.breadthFirstSearch(a)), equalTo("014532"));
    }

    @Test
    public void breathFirstSearch_Linear() {
        a.children.addAll(asList(b, c));
        b.children.add(d);
        c.children.add(d);
        d.children.addAll(asList((e), f));
        assertThat(asString(treesAndGraphs.breadthFirstSearch(a)), equalTo("012345"));
    }

    @Test
    public void pathExists() {
        sampleGraph();
        assertThat(treesAndGraphs.breadthFirstSearch(a).contains(d), is(true));
        assertThat(treesAndGraphs.breadthFirstSearch(a).contains(c), is(true));
        assertThat(treesAndGraphs.breadthFirstSearch(a).contains(f), is(true));
        assertThat(treesAndGraphs.breadthFirstSearch(b).contains(a), is(false));
        assertThat(treesAndGraphs.breadthFirstSearch(c).contains(d), is(true));
    }

    @Test
    public void minimalTreeSingleElement() {
        Node node = treesAndGraphs.minimalTree(asList("a"));
        assertThat(node.val, equalTo("a"));
        assertThat(node.children.size(), is(0));
    }

    @Test
    public void minimalTreeTwoElement() {
        Node node = treesAndGraphs.minimalTree(asList("a", "b"));
        assertThat(node.val, equalTo("b"));
        assertThat(node.children.get(0).val, equalTo("a"));
        assertThat(node.children.size(), is(1));
    }

    @Test
    public void minimalTreeThreeElements() {
        Node node = treesAndGraphs.minimalTree(asList("a", "b", "c"));
        assertThat(node.val, equalTo("b"));
        assertThat(node.children.get(0).val, equalTo("a"));
        assertThat(node.children.get(1).val, equalTo("c"));
    }

    @Test
    public void minimalTreeFourElements() {
        Node node = treesAndGraphs.minimalTree(asList("a", "b", "c", "d"));
        assertThat(node.val, equalTo("c"));
        assertThat(node.children.get(0).val, equalTo("b"));
        assertThat(node.children.get(1).val, equalTo("d"));
        assertThat(node.children.get(0).children.get(0).val, equalTo("a"));
    }

    @Test
    public void minimalTreeSevenElements() {
        Node node = treesAndGraphs.minimalTree(asList("a", "b", "c", "d", "e", "f", "g"));
        assertThat(node.val, equalTo("d"));
        assertThat(node.children.get(0).val, equalTo("b"));
        assertThat(node.children.get(1).val, equalTo("f"));
        assertThat(node.children.get(0).children.get(0).val, equalTo("a"));
        assertThat(node.children.get(0).children.get(1).val, equalTo("c"));
        assertThat(node.children.get(1).children.get(0).val, equalTo("e"));
        assertThat(node.children.get(1).children.get(1).val, equalTo("g"));
    }

    @Test
    public void listOfDepths() {
        sampleBinaryTree();
        List<List<Node>> listOfDepths = treesAndGraphs.listOfDepths(d);
        assertThat(listOfDepths.size(), is(3));
        assertThat(listOfDepths.get(0).size(), is(1));
        assertThat(listOfDepths.get(1).size(), is(2));
        assertThat(listOfDepths.get(2).size(), is(4));
    }

    @Test
    public void checkBalanced() {
        sampleBinaryTree();
        assertThat(treesAndGraphs.checkBalanced(d), is(true));
    }

    @Test
    public void checkBalanced_Unbalanced() {
        d.children.add(b);
        b.children.add(a);
        assertThat(treesAndGraphs.checkBalanced(d), is(false));
    }

    @Test
    public void isBinarySearchTree() {
        sampleBinaryTree();
        assertThat(treesAndGraphs.isBinarySearchTree(d), is(true));
    }

    @Test
    public void isBinarySearchTreeLeftFalse() {
        sampleBinaryTree();
        a.val = "9";
        assertThat(treesAndGraphs.isBinarySearchTree(d), is(false));
    }

    @Test
    public void isBinarySearchTreeRightFalse() {
        sampleBinaryTree();
        g.val = "1";
        assertThat(treesAndGraphs.isBinarySearchTree(d), is(false));
    }

    @Test
    public void sumPaths1Node() {
        assertThat(treesAndGraphs.sumPaths(b, 1), is(1L));
    }

    @Test
    public void sumPaths1NodeNoSums() {
        assertThat(treesAndGraphs.sumPaths(b, 2), is(0L));
    }

    @Test
    public void sumPaths2Nodes() {
        b.children.add(c);
        assertThat(treesAndGraphs.sumPaths(b, 3), is(1L));
    }

    @Test
    public void sumPaths2NodesNoSums() {
        b.children.add(c);
        assertThat(treesAndGraphs.sumPaths(b, 4), is(0L));
    }

    @Test
    public void sumPaths3Nodes() {
        Node d = new Node("2");
        b.children.addAll(Arrays.asList(c, d));
        assertThat(treesAndGraphs.sumPaths(b, 3), is(2L));
    }

    @Test
    public void sumPathsBinaryTree() {
        sampleBinaryTree();
        a.val = b.val = c.val = d.val = e.val = f.val = g.val = "0";

        d.val = "5";
        b.val = "2";
        a.val = "3";
        c.val = "10";
        f.val = "-2";
        e.val = "12";
        g.val = "7";

        assertThat(treesAndGraphs.sumPaths(d, 10), is(4L));
    }

    @Test
    public void isSubtreeSingleNode() {
        Node t1 = new Node("a");
        Node t2 = new Node("a");
        assertThat(treesAndGraphs.isSubtree(t1, t2), is(true));
    }

    @Test
    public void isSubtreeExactTrees() {
        sampleBinaryTree();
        assertThat(treesAndGraphs.isSubtree(d, d), is(true));
    }

    @Test
    public void isSubtreeLeftNodeIs() {
        sampleBinaryTree();
        assertThat(treesAndGraphs.isSubtree(d, b), is(true));
    }

    @Test
    public void isSubtreeRightNodeIs() {
        sampleBinaryTree();
        assertThat(treesAndGraphs.isSubtree(d, f), is(true));
    }

    @Test
    public void testStackOverflow() {
        Node oneOne = new Node("1");
        Node oneTwo = new Node("2");
        Node oneThree = new Node("3");
        Node oneFour = new Node("4");
        Node twoOne = new Node("1");
        Node twoTwo = new Node("2");
        Node twoThree = new Node("3");

        oneOne.children.add(oneTwo);
        oneOne.children.add(oneThree);
        oneThree.children.add(oneFour);
        oneFour.children.add(null);
        oneFour.children.add(twoOne);
        twoOne.children.add(twoTwo);
        twoTwo.children.add(twoThree);

        Node subOne = new Node("1");
        Node subTwo = new Node("2");
        Node subThree = new Node("3");

        subOne.children.add(subTwo);
        subTwo.children.add(subThree);

        System.out.println(treesAndGraphs.isSubtree(oneOne, subOne));
    }

    @Test
    public void isSubtreeFalse() {
        a = new Node("0");
        b = new Node("1");
        c = new Node("2");

        d = new Node("0");
        e = new Node("1");
        f = new Node("3");

        a.children.add(b);
        b.children.add(c);

        d.children.add(e);
        e.children.add(f);

        assertThat(treesAndGraphs.isSubtree(a, d), is(false));
    }

    @Test
    public void isSubtreeFalseTricky() {
        Node t1_a = new Node("a");
        Node t1_b = new Node("b");
        Node t1_c = new Node("c");
        Node t1_d = new Node("d");
        Node t1_e = new Node("e");
        Node t1_f = new Node("f");

        t1_a.children.add(t1_b);
        t1_b.children.add(t1_c);
        t1_c.children.add(t1_d);
        t1_d.children.add(t1_e);
        t1_e.children.add(t1_f);

        Node t2_a = new Node("a");
        Node t2_f = new Node("f");

        t2_a.children.add(t2_f);

        assertThat(treesAndGraphs.isSubtree(t1_a, t2_a), is(true));
    }

    @Test
    public void isSubtree_2() {
        Node t1_1 = new Node("a");
        Node t1_2 = new Node("b");
        Node t1_3 = new Node("a");
        Node t1_4 = new Node("d");

        t1_1.children.add(t1_2);
        t1_2.children.add(t1_3);
        t1_3.children.add(t1_4);

        Node t2_1 = new Node("a");
        Node t2_2 = new Node("d");

        t2_1.children.add(t2_2);

        assertThat(treesAndGraphs.isSubtree(t1_1, t2_1), is(true));
    }

    private void sampleGraph() {
        a.children.addAll(asList(b, e, f));
        b.children.addAll(asList(d, e));
        c.children.addAll(asList(b));
        d.children.addAll(asList(c, e));
    }

    private void sampleBinaryTree() {
        d.children.add(b);
        d.children.add(f);
        b.children.add(a);
        b.children.add(c);
        f.children.add(e);
        f.children.add(g);
    }

    private String asString(List<Node> nodes) {
        return nodes.stream().map(node -> node.val).collect(joining(""));
    }
}
