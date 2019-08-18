package biz.tugay.ctci.ch03;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QueueViaStacksTest {

    QueueViaStacks<String> queueStack;

    @Before
    public void before() {
        queueStack = new QueueViaStacks<>();
    }

    @Test
    public void pushPopPeekIsEmpty() {
        assertThat(queueStack.isEmpty(), is(true));

        queueStack.push("foo");
        queueStack.push("bar");

        assertThat(queueStack.peek(), is("foo"));
        assertThat(queueStack.pop(), is("foo"));

        assertThat(queueStack.isEmpty(), is(false));

        assertThat(queueStack.peek(), is("bar"));
        assertThat(queueStack.pop(), is("bar"));

        assertThat(queueStack.isEmpty(), is(true));
    }

    @Test
    public void pushPushPopPushPop() {
        assertThat(queueStack.isEmpty(), is(true));

        queueStack.push("foo");
        queueStack.push("bar");

        assertThat(queueStack.pop(), is("foo"));

        queueStack.push("baz");
        assertThat(queueStack.pop(), is("bar"));
        assertThat(queueStack.pop(), is("baz"));

        assertThat(queueStack.isEmpty(), is(true));
    }
}
