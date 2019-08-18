package biz.tugay.ctci.ch03;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SetOfStacksTest {

    SetOfStacks<String> setOfStacks;

    @Before
    public void before() {
        setOfStacks = new SetOfStacks<>(2);
    }

    @Test
    public void pushPeekPopIsEmpty() {
        assertThat(setOfStacks.isEmpty(), is(true));
        setOfStacks.push("foo");
        assertThat(setOfStacks.isEmpty(), is(false));
        assertThat(setOfStacks.peek(), is("foo"));
        assertThat(setOfStacks.pop(), is("foo"));

        assertThat(setOfStacks.peek(), nullValue());
        assertThat(setOfStacks.pop(), nullValue());
        assertThat(setOfStacks.isEmpty(), is(true));
        assertThat(setOfStacks.listOfStacks.size(), is(0));
    }

    @Test
    public void pushPeekPopIsEmptyExceedThreshold() {
        setOfStacks.push("foo");
        setOfStacks.push("bar");
        setOfStacks.push("baz");

        assertThat(setOfStacks.isEmpty(), is(false));
        assertThat(setOfStacks.peek(), is("baz"));
        assertThat(setOfStacks.pop(), is("baz"));


        assertThat(setOfStacks.isEmpty(), is(false));
        assertThat(setOfStacks.peek(), is("bar"));
        assertThat(setOfStacks.pop(), is("bar"));

        assertThat(setOfStacks.isEmpty(), is(false));
        assertThat(setOfStacks.peek(), is("foo"));
        assertThat(setOfStacks.pop(), is("foo"));

        assertThat(setOfStacks.isEmpty(), is(true));
        assertThat(setOfStacks.listOfStacks.size(), is(0));
    }

    @Test
    public void pushPeekPopIsEmptyExceedThresholdTwice() {
        setOfStacks.push("foo");
        setOfStacks.push("bar");
        setOfStacks.push("baz");
        setOfStacks.push("foo");
        setOfStacks.push("bar");
        setOfStacks.push("baz");


        assertThat(setOfStacks.isEmpty(), is(false));
        assertThat(setOfStacks.peek(), is("baz"));
        assertThat(setOfStacks.pop(), is("baz"));


        assertThat(setOfStacks.isEmpty(), is(false));
        assertThat(setOfStacks.peek(), is("bar"));
        assertThat(setOfStacks.pop(), is("bar"));

        assertThat(setOfStacks.isEmpty(), is(false));
        assertThat(setOfStacks.peek(), is("foo"));
        assertThat(setOfStacks.pop(), is("foo"));

        assertThat(setOfStacks.isEmpty(), is(false));
        assertThat(setOfStacks.peek(), is("baz"));
        assertThat(setOfStacks.pop(), is("baz"));


        assertThat(setOfStacks.isEmpty(), is(false));
        assertThat(setOfStacks.peek(), is("bar"));
        assertThat(setOfStacks.pop(), is("bar"));

        assertThat(setOfStacks.isEmpty(), is(false));
        assertThat(setOfStacks.peek(), is("foo"));
        assertThat(setOfStacks.pop(), is("foo"));

        assertThat(setOfStacks.isEmpty(), is(true));
        assertThat(setOfStacks.listOfStacks.size(), is(0));
    }

    @Test
    public void exceedThresholdPopFromZero() {
        setOfStacks.push("foo");
        setOfStacks.push("bar");
        setOfStacks.push("baz");

        assertThat(setOfStacks.popAt(0), is("bar"));
        assertThat(setOfStacks.popAt(0), is("foo"));
        assertThat(setOfStacks.isEmpty(), is(false));
        assertThat(setOfStacks.pop(), is("baz"));
        assertThat(setOfStacks.isEmpty(), is(true));
        assertThat(setOfStacks.listOfStacks.size(), is(0));
    }

    @Test
    public void exceedThresholdPopFromZeroPushAgainAndExceedAgain() {
        setOfStacks.push("foo");
        setOfStacks.push("bar");
        setOfStacks.push("baz");
        assertThat(setOfStacks.listOfStacks.size(), is(2));

        setOfStacks.popAt(0);
        setOfStacks.popAt(0);
        setOfStacks.popAt(0);

        assertThat(setOfStacks.listOfStacks.size(), is(0));

        setOfStacks.push("foo");
        setOfStacks.push("bar");
        setOfStacks.push("baz");
        assertThat(setOfStacks.listOfStacks.size(), is(2));
        setOfStacks.push("foo");
        setOfStacks.push("bar");
        setOfStacks.push("baz");
        assertThat(setOfStacks.listOfStacks.size(), is(3));

    }

}
