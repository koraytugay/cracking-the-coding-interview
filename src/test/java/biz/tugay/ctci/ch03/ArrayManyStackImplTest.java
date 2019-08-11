package biz.tugay.ctci.ch03;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArrayManyStackImplTest {

    ArrayManyStackImpl stack;

    @Before
    public void before() {
        stack = new ArrayManyStackImpl();
    }

    @Test
    public void initialAllEmpty() {
        assertThat(stack.isEmpty(0), is(true));
        assertThat(stack.isEmpty(1), is(true));
        assertThat(stack.isEmpty(2), is(true));
    }

    @Test
    public void popNoGrow() {
        stack.data[0] = "foo";
        stack.data[1] = "bar";
        stack.stackPtr[0] = 2;

        assertThat(stack.isEmpty(0), is(false));
        assertThat(stack.pop(0), is("bar"));
        assertThat(stack.pop(0), is("foo"));
        assertThat(stack.isEmpty(0), is(true));
    }

    @Test
    public void popNull() {
        assertThat(stack.isEmpty(0), is(true));
        assertThat(stack.pop(0), nullValue());
    }


    @Test
    public void pushNoGrow() {
        stack.push(0, "foo");
        stack.push(0, "bar");

        stack.push(1, "baz");
        stack.push(1, "qux");

        stack.push(2, "quux");
        stack.push(2, "quuz");

        assertThat(stack.data, equalTo(new String[]{"foo", "bar", "baz", "qux", "quux", "quuz"}));
    }

    @Test
    public void pushGrow() {
        stack.push(0, "foo");
        stack.push(0, "bar");
        stack.push(0, "baz");
        stack.push(0, "qux");

        assertThat(stack.pop(0), is("qux"));
        assertThat(stack.pop(0), is("baz"));
        assertThat(stack.pop(0), is("bar"));
        assertThat(stack.pop(0), is("foo"));
    }

    @Test
    public void integration() {
        for (int i = 0; i < 3; i++)
            assertThat(stack.isEmpty(i), is(true));

        for (int i = 0; i < 3; i++)
            assertThat(stack.peek(i), nullValue());

        for (int i = 0; i < 3; i++)
            assertThat(stack.pop(i), nullValue());

        // Push - Pop without grow
        stack.push(0, "a");
        stack.push(1, "b");
        stack.push(2, "c");

        for (int i = 0; i < 3; i++)
            assertThat(stack.isEmpty(i), is(false));

        assertThat(stack.peek(0), is("a"));
        assertThat(stack.peek(1), is("b"));
        assertThat(stack.peek(2), is("c"));

        assertThat(stack.pop(0), is("a"));
        assertThat(stack.pop(1), is("b"));
        assertThat(stack.pop(2), is("c"));

        for (int i = 0; i < 3; i++)
            assertThat(stack.isEmpty(i), is(true));

        stack.push(0, "d");
        stack.push(1, "e");
        stack.push(2, "f");

        assertThat(stack.peek(0), is("d"));
        assertThat(stack.peek(1), is("e"));
        assertThat(stack.peek(2), is("f"));

        assertThat(stack.pop(0), is("d"));
        assertThat(stack.pop(1), is("e"));
        assertThat(stack.pop(2), is("f"));

        for (int i = 0; i < 3; i++)
            assertThat(stack.isEmpty(i), is(true));

        stack.push(0, "a");
        stack.push(1, "b");
        stack.push(2, "c");

        stack.push(0, "d");
        stack.push(1, "e");
        stack.push(2, "f");

        assertThat(stack.peek(0), is("d"));
        assertThat(stack.peek(1), is("e"));
        assertThat(stack.peek(2), is("f"));

        assertThat(stack.pop(0), is("d"));
        assertThat(stack.pop(1), is("e"));
        assertThat(stack.pop(2), is("f"));

        assertThat(stack.peek(0), is("a"));
        assertThat(stack.peek(1), is("b"));
        assertThat(stack.peek(2), is("c"));

        assertThat(stack.pop(0), is("a"));
        assertThat(stack.pop(1), is("b"));
        assertThat(stack.pop(2), is("c"));

        for (int i = 0; i < 3; i++)
            assertThat(stack.isEmpty(i), is(true));
    }

    @Test
    public void grow() {
        stack.push(1, "b");
        stack.push(1, "e");
        stack.push(1, "h");

        assertThat(stack.peek(1), is("h"));
        assertThat(stack.pop(1), is("h"));
        assertThat(stack.isEmpty(1), is(false));

        assertThat(stack.peek(1), is("e"));
        assertThat(stack.pop(1), is("e"));
        assertThat(stack.isEmpty(1), is(false));

        assertThat(stack.peek(1), is("b"));
        assertThat(stack.pop(1), is("b"));
        assertThat(stack.isEmpty(1), is(true));
    }

    @Test
    public void growMultiple() {
        stack.push(1, "a");
        stack.push(1, "b");
        stack.push(1, "c");

        stack.push(2, "a");
        stack.push(2, "b");
        stack.push(2, "c");
        stack.push(2, "d");
        stack.push(2, "e");

        assertThat(stack.isEmpty(0), is(true));

        assertThat(stack.pop(1), is("c"));
        assertThat(stack.pop(1), is("b"));
        assertThat(stack.pop(1), is("a"));
        assertThat(stack.isEmpty(1), is(true));

        assertThat(stack.isEmpty(2), is(false));
        assertThat(stack.pop(2), is("e"));
        assertThat(stack.pop(2), is("d"));
        assertThat(stack.pop(2), is("c"));
        assertThat(stack.pop(2), is("b"));
        assertThat(stack.pop(2), is("a"));
        assertThat(stack.isEmpty(2), is(true));
    }

}
