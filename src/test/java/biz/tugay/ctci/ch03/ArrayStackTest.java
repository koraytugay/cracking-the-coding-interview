package biz.tugay.ctci.ch03;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArrayStackTest {

    ArrayStack<String> arrayStack;

    @Before
    public void before() {
        arrayStack = new ArrayStack<>();
    }

    @Test
    public void pushPopSingle() {
        assertThat(arrayStack.isEmpty(), is(true));
        arrayStack.push("foo");
        assertThat(arrayStack.isEmpty(), is(false));
        assertThat(arrayStack.peek(), equalTo("foo"));
        assertThat(arrayStack.isEmpty(), is(false));
        assertThat(arrayStack.pop(), equalTo("foo"));
        assertThat(arrayStack.isEmpty(), is(true));
    }

    @Test
    public void pushPopMultiple() {
        arrayStack.push("foo");
        arrayStack.push("bar");
        assertThat(arrayStack.peek(), is("bar"));
        assertThat(arrayStack.pop(), is("bar"));
        assertThat(arrayStack.peek(), is("foo"));
        assertThat(arrayStack.pop(), is("foo"));
    }

    @Test
    public void peekEmptyStack() {
        assertThat(arrayStack.peek(), nullValue());
    }
}
