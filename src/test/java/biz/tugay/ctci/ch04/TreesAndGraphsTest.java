package biz.tugay.ctci.ch04;

import org.junit.Before;
import org.junit.Test;

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
