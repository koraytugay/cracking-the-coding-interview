package biz.tugay.ctci.ch03.modal;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArrayStackImplTest {

    ArrayStackImpl<String> arrayStackImpl;

    @Before
    public void before() {
        arrayStackImpl = new ArrayStackImpl<>();
    }

    @Test
    public void pushPopSingle() {
        assertThat(arrayStackImpl.isEmpty(), is(true));
        arrayStackImpl.push("foo");
        assertThat(arrayStackImpl.isEmpty(), is(false));
        assertThat(arrayStackImpl.peek(), equalTo("foo"));
        assertThat(arrayStackImpl.isEmpty(), is(false));
        assertThat(arrayStackImpl.pop(), equalTo("foo"));
        assertThat(arrayStackImpl.isEmpty(), is(true));
    }

    @Test
    public void pushPopMultiple() {
        arrayStackImpl.push("foo");
        arrayStackImpl.push("bar");
        assertThat(arrayStackImpl.peek(), is("bar"));
        assertThat(arrayStackImpl.pop(), is("bar"));
        assertThat(arrayStackImpl.peek(), is("foo"));
        assertThat(arrayStackImpl.pop(), is("foo"));
    }

    @Test
    public void peekEmptyStack() {
        assertThat(arrayStackImpl.peek(), nullValue());
    }
}
