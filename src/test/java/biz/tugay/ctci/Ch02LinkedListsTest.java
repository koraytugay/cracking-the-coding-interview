package biz.tugay.ctci;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Ch02LinkedListsTest {

    Ch02LinkedLists linkedLists = new Ch02LinkedLists();

    @Test(timeout = 200)
    public void removeDuplicatesInPlace() {
        Node a = sampleList();

        linkedLists.removeDuplicatesInPlace(a);

        assertThat(a.val, equalTo('a'));
        assertThat(a.next.val, equalTo('b'));
        assertThat(a.next.next.val, equalTo('c'));
        assertThat(a.next.next.next, equalTo(null));
    }

    @Test(timeout = 200)
    public void returnKthLast() {
        Node a = sampleList();

        assertThat(linkedLists.returnKthLast(a, 0).val, equalTo('c'));
        assertThat(linkedLists.returnKthLast(a, 1).val, equalTo('c'));
        assertThat(linkedLists.returnKthLast(a, 2).val, equalTo('b'));
        assertThat(linkedLists.returnKthLast(a, 6).val, equalTo('a'));
    }

    @Test(timeout = 200)
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

    @Test(timeout = 200)
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

    @Test(timeout = 200)
    public void sumLists() {
        IntegerNode x = new IntegerNode(2);
        x.next = new IntegerNode(2);

        IntegerNode y = new IntegerNode(3);
        y.next = new IntegerNode(3);

        IntegerNode sum = linkedLists.sumLists(x, y);
        assertThat(sum.val, equalTo(5));
        assertThat(sum.next.val, equalTo(5));
        assertThat(sum.next.next, nullValue());

        x = new IntegerNode(6);
        x.next = new IntegerNode(6);

        y = new IntegerNode(6);
        y.next = new IntegerNode(6);

        sum = linkedLists.sumLists(x, y);
        assertThat(sum.val, equalTo(2));
        assertThat(sum.next.val, equalTo(3));
        assertThat(sum.next.next.val, equalTo(1));
        assertThat(sum.next.next.next, nullValue());


        x = new IntegerNode(7);
        x.next = new IntegerNode(1);
        x.next.next = new IntegerNode(6);

        y = new IntegerNode(5);
        y.next = new IntegerNode(9);
        y.next.next = new IntegerNode(2);

        sum = linkedLists.sumLists(x, y);
        assertThat(sum.val, equalTo(2));
        assertThat(sum.next.val, equalTo(1));
        assertThat(sum.next.next.val, equalTo(9));
        assertThat(sum.next.next.next, nullValue());
    }

    @Test(timeout = 200)
    public void isPalindrome() {
        Node node = sampleList();
        assertThat(linkedLists.isPalindrome(node), is(false));

        node = new Node('a', new Node('a'));
        assertThat(linkedLists.isPalindrome(node), is(true));

        node = new Node('a', new Node('a', new Node('b')));
        assertThat(linkedLists.isPalindrome(node), is(false));
    }

    @Test(timeout = 200)
    public void intersection() {
        Node intersection = new Node('i', new Node('z'));

        Node a = new Node('a', new Node('b', intersection));
        Node c = new Node('c', new Node('d', intersection));

        assertThat(linkedLists.intersecting(a, c), is(true));


        a = new Node('a', new Node('b', intersection));
        c = new Node('c', new Node('d', new Node('e')));

        assertThat(linkedLists.intersecting(a, c), is(false));
    }

    @Test(timeout = 200)
    public void loopDetection() {
        Node e = new Node('e');
        Node c = new Node('c', new Node('d', e));
        e.next = c;
        Node a = new Node('a', new Node('b', c));

        assertThat(linkedLists.loopDetection(a), is(true));
        assertThat(linkedLists.loopDetection(new Node('a', new Node('b', new Node('c')))), is(false));
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