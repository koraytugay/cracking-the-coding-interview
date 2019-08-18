package biz.tugay.ctci.ch03;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class StackMinTest {

    StackMin<Integer> stack;

    @Before
    public void before() {
        stack = new StackMin<>();
    }

    @Test
    public void peekPop() {
        assertThat(stack.isEmpty(), is(true));

        stack.push(10);
        assertThat(stack.isEmpty(), is(false));

        int peek = stack.peek();
        assertThat(peek, equalTo(10));
        assertThat(stack.isEmpty(), is(false));

        int pop = stack.pop();
        assertThat(pop, equalTo(10));
        assertThat(stack.isEmpty(), is(true));
    }

    @Test
    public void peekMin() {
        stack.push(50);
        assertThat(stack.peekMin(), is(50));
        assertThat(stack.isEmpty(), is(false));
    }

    @Test
    public void popMin() {
        stack.push(50);
        assertThat(stack.popMin(), is(50));
        assertThat(stack.isEmpty(), is(true));
    }

    @Test
    public void consumeStackPopMin() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertThat(stack.popMin(), is(1));
        assertThat(stack.popMin(), is(2));
        assertThat(stack.popMin(), is(3));

        assertThat(stack.isEmpty(), is(true));
    }

    @Test
    public void consumeStackPopMin_Unordered() {
        stack.push(3);
        stack.push(2);
        stack.push(1);

        assertThat(stack.popMin(), is(1));
        assertThat(stack.popMin(), is(2));
        assertThat(stack.popMin(), is(3));

        assertThat(stack.isEmpty(), is(true));
    }

    @Test
    public void consumeStackPopMin_Duplicate() {
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertThat(stack.popMin(), is(1));
        assertThat(stack.popMin(), is(1));
        assertThat(stack.popMin(), is(2));
        assertThat(stack.popMin(), is(2));
        assertThat(stack.popMin(), is(3));
        assertThat(stack.popMin(), is(3));

        assertThat(stack.isEmpty(), is(true));
    }

    @Test
    public void consumeStackPopPopMin() {
        stack.push(5);
        stack.push(10);
        stack.push(5);

        assertThat(stack.popMin(), is(5));
        assertThat(stack.pop(), is(10));
        assertThat(stack.popMin(), is(5));
    }

    @Test
    public void consumeStackPop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertThat(stack.pop(), is(3));
        assertThat(stack.pop(), is(2));
        assertThat(stack.pop(), is(1));

        assertThat(stack.peekMin(), nullValue());
    }

    @Test
    public void popMinFromMiddle() {
        stack.push(10);
        stack.push(5);
        stack.push(12);

        assertThat(stack.popMin(), is(5));
        assertThat(stack.pop(), is(12));
        assertThat(stack.pop(), is(10));
    }

    @Test
    public void popMinEmpty() {
        assertThat(stack.popMin(), nullValue());
    }

    @Test
    public void pushPopPopMin() {
        stack.push(1);
        stack.pop();
        assertThat(stack.popMin(), nullValue());
    }

    @Test
    public void popMultipleMinFromMiddle() {
        stack.push(10);
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(10);

        assertThat(stack.popMin(), is(1));
        assertThat(stack.popMin(), is(1));
        assertThat(stack.popMin(), is(1));
        assertThat(stack.isEmpty(), is(false));

        assertThat(stack.pop(), is(10));
        assertThat(stack.pop(), is(10));

        assertThat(stack.isEmpty(), is(true));
    }

    @Test
    public void peekMinAfterPopMinFromMiddle() {
        stack.push(10);
        stack.push(2);
        stack.push(5);
        stack.push(10);

        assertThat(stack.peekMin(), is(2));
        assertThat(stack.popMin(), is(2));

        assertThat(stack.peekMin(), is(5));
        assertThat(stack.popMin(), is(5));

        assertThat(stack.peekMin(), is(10));
        assertThat(stack.popMin(), is(10));

        assertThat(stack.peekMin(), is(10));
        assertThat(stack.popMin(), is(10));

        assertThat(stack.isEmpty(), is(true));
    }

    @Test
    public void peekMinPopMinAfterPop() {
        stack.push(10);
        stack.push(5);

        stack.pop();
        assertThat(stack.peekMin(), is(10));
        assertThat(stack.popMin(), is(10));

        assertThat(stack.peekMin(), nullValue());
        assertThat(stack.peek(), nullValue());
        assertThat(stack.isEmpty(), is(true));
    }

    @Test
    public void popMinAfterConsumed() {
        stack.push(10);
        stack.pop();

        assertThat(stack.peek(), nullValue());
        assertThat(stack.peekMin(), nullValue());
        assertThat(stack.popMin(), nullValue());
        assertThat(stack.isEmpty(), is(true));
    }
}
