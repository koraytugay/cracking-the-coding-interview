package biz.tugay.ctci.ch03.modal;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class StackImplTest {

    Stack<String> stack;

    @Before
    public void before() {
        stack = new StackImpl<>();
    }

    @Test
    public void pushPopSingle() {
        assertThat(stack.isEmpty(), is(true));
        stack.push("foo");
        assertThat(stack.isEmpty(), is(false));
        assertThat(stack.peek(), equalTo("foo"));
        assertThat(stack.isEmpty(), is(false));
        assertThat(stack.pop(), equalTo("foo"));
        assertThat(stack.isEmpty(), is(true));
    }

    @Test
    public void pushPopMultiple() {
        stack.push("foo");
        stack.push("bar");
        assertThat(stack.peek(), is("bar"));
        assertThat(stack.pop(), is("bar"));
        assertThat(stack.peek(), is("foo"));
        assertThat(stack.pop(), is("foo"));
    }

    @Test
    public void peekEmptyStack() {
        assertThat(stack.peek(), nullValue());
    }
}