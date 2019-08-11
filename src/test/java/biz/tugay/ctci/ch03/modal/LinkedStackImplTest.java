package biz.tugay.ctci.ch03.modal;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class LinkedStackImplTest {

    LinkedStackImpl<String> linkedStack;

    @Before
    public void before() {
        linkedStack = new LinkedStackImpl<>();
    }

    @Test
    public void pushPopSingle() {
        assertThat(linkedStack.isEmpty(), is(true));
        linkedStack.push("foo");
        assertThat(linkedStack.isEmpty(), is(false));
        assertThat(linkedStack.peek(), equalTo("foo"));
        assertThat(linkedStack.isEmpty(), is(false));
        assertThat(linkedStack.pop(), equalTo("foo"));
        assertThat(linkedStack.isEmpty(), is(true));
    }

    @Test
    public void pushPopMultiple() {
        linkedStack.push("foo");
        linkedStack.push("bar");
        assertThat(linkedStack.peek(), is("bar"));
        assertThat(linkedStack.pop(), is("bar"));
        assertThat(linkedStack.peek(), is("foo"));
        assertThat(linkedStack.pop(), is("foo"));
    }

    @Test
    public void peekEmptyStack() {
        assertThat(linkedStack.peek(), nullValue());
    }
}
