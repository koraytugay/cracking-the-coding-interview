package biz.tugay.ctci;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class Ch02LinkedListsTest {

    Ch02LinkedLists linkedLists = new Ch02LinkedLists();

    @Test(timeout = 200) // Timeout to detect infinite loop
    public void removeDuplicatesInPlace() {
        Node a = sampleList();

        linkedLists.removeDuplicatesInPlace(a);

        assertThat(a.val, equalTo('a'));
        assertThat(a.next.val, equalTo('b'));
        assertThat(a.next.next.val, equalTo('c'));
        assertThat(a.next.next.next, equalTo(null));
    }

    @Test
    public void returnKthLast() {
        Node a = sampleList();

        assertThat(linkedLists.returnKthLast(a, 0).val, equalTo('c'));
        assertThat(linkedLists.returnKthLast(a, 1).val, equalTo('c'));
        assertThat(linkedLists.returnKthLast(a, 2).val, equalTo('b'));
        assertThat(linkedLists.returnKthLast(a, 6).val, equalTo('a'));
    }

    @Test(timeout = 200) // Timeout to detect infinite loop
    public void deleteMiddleNode() {
        Node a = sampleList();

        // a -> a -> b -> c -> b -> b -> c -> c
        linkedLists.deleteMiddleNode(a.next.next);
        // a -> a -> . -> c -> b -> b -> c -> c

        assertThat(a.val, equalTo('a'));
        assertThat(a.next.val, equalTo('a'));
        assertThat(a.next.next.val, equalTo('c'));
        assertThat(a.next.next.next.val, equalTo('b'));
        assertThat(a.next.next.next.next.val, equalTo('b'));
        assertThat(a.next.next.next.next.next.val, equalTo('c'));
        assertThat(a.next.next.next.next.next.next.val, equalTo('c'));
        assertThat(a.next.next.next.next.next.next.next, equalTo(null));
    }

    @Test(timeout = 200) // Timeout to detect infinite loop
    public void partition() {
        Node c = new Node('c');

        Node e = new Node('e');
        c.next = e;

        Node h = new Node('h');
        e.next = h;

        Node e_dup = new Node('e');
        h.next = e_dup;

        Node j = new Node('j');
        e_dup.next = j;

        Node b = new Node('b');
        j.next = b;

        b.next = new Node('a');

        Node partitioned = linkedLists.partition(c, 'e');
        assertThat(partitioned.val, equalTo('c'));
        assertThat(partitioned.next.val, equalTo('b'));
        assertThat(partitioned.next.next.val, equalTo('a'));
        assertThat(partitioned.next.next.next.val, equalTo('e'));
        assertThat(partitioned.next.next.next.next.val, equalTo('h'));
        assertThat(partitioned.next.next.next.next.next.val, equalTo('e'));
        assertThat(partitioned.next.next.next.next.next.next.val, equalTo('j'));
        assertThat(partitioned.next.next.next.next.next.next.next, nullValue());
    }

    // a -> a -> b -> c -> b -> b -> c -> c
    Node sampleList() {
        Node a = new Node('a');
        Node a_dup = new Node('a');
        Node b = new Node('b');
        Node c = new Node('c');
        Node b_dup = new Node('b');
        Node b_dup_dup = new Node('b');
        Node c_dup = new Node('c');
        Node c_dup_dup = new Node('c');

        a.next = a_dup;
        a_dup.next = b;
        b.next = c;
        c.next = b_dup;
        b_dup.next = b_dup_dup;
        b_dup_dup.next = c_dup;
        c_dup.next = c_dup_dup;
        return a;
    }
}