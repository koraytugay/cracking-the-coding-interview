package biz.tugay.ctci.ch03;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static java.lang.String.valueOf;
import static org.junit.Assert.*;

public class SingleArrayManyStackTest {

    SingleArrayManyStack stack;

    @Before
    public void before() {
        stack = null;
    }

    @Test
    public void capacityOneGrowOnce() {
        stack = new SingleArrayManyStack(1, 2);

        stack.push(0, "0-0");
        stack.push(0, "0-1");
        assertEquals("0-1", stack.peek(0));
        assertEquals("[0-0, 0-1]", Arrays.toString(stack.data));

        stack.push(0, "0-2");
        stack.push(0, "0-3");

        assertEquals("0-3", stack.peek(0));
        assertEquals("[0-0, 0-1, 0-2, 0-3]", Arrays.toString(stack.data));
    }

    @Test
    public void capacityOneGrowTwice() {
        stack = new SingleArrayManyStack(1, 2);

        stack.push(0, "0-0");
        stack.push(0, "0-1");
        assertEquals("[0-0, 0-1]", Arrays.toString(stack.data));

        stack.push(0, "0-2");
        stack.push(0, "0-3");

        stack.push(0, "0-4");
        stack.push(0, "0-5");
        assertEquals("[0-0, 0-1, 0-2, 0-3, 0-4, 0-5, null, null]", Arrays.toString(stack.data));
    }

    @Test
    public void capacityOneGrowThreeTimes() {
        stack = new SingleArrayManyStack(1, 2);
        stack.push(0, "0-0");
        stack.push(0, "0-1");
        assertEquals(2, stack.data.length);

        stack.push(0, "0-2"); // doubles capacity
        stack.push(0, "0-3");
        assertEquals(4, stack.data.length);

        stack.push(0, "0-4"); // doubles capacity
        stack.push(0, "0-5");
        stack.push(0, "0-6");
        stack.push(0, "0-7");
        assertEquals(8, stack.data.length);

        stack.push(0, "0-8");  // doubles capacity
        assertEquals(16, stack.data.length);
    }

    @Test
    public void capacityOnePushPop() {
        stack = new SingleArrayManyStack(1, 2);

        stack.push(0, "0");
        assertEquals("0", stack.pop(0));

        stack.push(0, "0");
        stack.push(0, "1");
        assertEquals("1", stack.pop(0));
        assertEquals("0", stack.pop(0));

        stack.push(0, "0");
        stack.push(0, "1");
        stack.push(0, "2");
        assertEquals("2", stack.pop(0));
        assertEquals("1", stack.pop(0));
        assertEquals("0", stack.pop(0));

        stack.push(0, "0");
        stack.push(0, "1");
        stack.push(0, "2");
        assertEquals("2", stack.pop(0));

        stack.push(0, "3");
        assertEquals("3", stack.pop(0));
        assertEquals("1", stack.pop(0));
        assertEquals("0", stack.pop(0));

        assertTrue(stack.isEmpty(0));
    }

    @Test
    public void isEmpty_InitialAllEmpty() {
        stack = new SingleArrayManyStack(3, 2);
        assertTrue(stack.isEmpty(0));
        assertTrue(stack.isEmpty(1));
        assertTrue(stack.isEmpty(2));
    }

    @Test
    public void pushPop_OneItemSingleStack() {
        stack = new SingleArrayManyStack(3, 2);
        assertNull(stack.pop(0));
        assertTrue(stack.isEmpty(0));

        stack.push(0, "foo");
        assertFalse(stack.isEmpty(0));

        assertEquals(stack.pop(0), "foo");
        assertTrue(stack.isEmpty(0));
    }

    @Test
    public void pushPop_TwoItemsStack_0() {
        stack = new SingleArrayManyStack(3, 2);
        assertNull(stack.pop(0));
        assertTrue(stack.isEmpty(0));

        stack.push(0, "foo");
        assertFalse(stack.isEmpty(0));

        stack.push(0, "bar");
        assertFalse(stack.isEmpty(0));

        assertEquals(stack.pop(0), "bar");
        assertFalse(stack.isEmpty(0));


        assertEquals(stack.pop(0), "foo");
        assertTrue(stack.isEmpty(0));
    }

    @Test
    public void push_PushDataAfterExpand() {
        stack = new SingleArrayManyStack(3, 2);
        stack.push(0, "0-0");
        stack.push(0, "0-1");
        stack.push(0, "0-2");

        stack.push(2, "2-0");
        stack.push(2, "2-1");
        assertEquals("[0-0, 0-1, 0-2, null, null, null, 2-0, 2-1]", Arrays.toString(stack.data));
    }

    @Test
    public void expandStack_Expand_0_Once() {
        stack = new SingleArrayManyStack(3, 2);
        stack.push(2, "2-0");
        stack.push(2, "2-1");
        stack.push(0, "0-0");
        stack.push(0, "0-1");
        stack.push(0, "0-2");
        assertEquals("[0-0, 0-1, 0-2, null, null, null, 2-0, 2-1]", Arrays.toString(stack.data));
    }

    @Test
    public void expandStack_Expand_0_Twice() {
        stack = new SingleArrayManyStack(3, 2);
        for (int i = 0; i < 6; i++)
            stack.push(0, valueOf(i));
        assertEquals("[0, 1, 2, 3, 4, 5, null, null, null, null, null, null]", Arrays.toString(stack.data));
    }

    @Test
    public void expandStack_Expand_1_Once() {
        stack = new SingleArrayManyStack(3, 2);
        stack.push(2, "2-0");
        stack.push(2, "2-1");

        stack.push(0, "1-0");
        stack.push(0, "1-1");
        stack.push(0, "1-2");
        assertEquals("[1-0, 1-1, 1-2, null, null, null, 2-0, 2-1]", Arrays.toString(stack.data));
    }

    @Test
    public void expandStack_expandStack_1_StartFull() {
        stack = new SingleArrayManyStack(3, 2);

        stack.push(0, "0-0");
        stack.push(0, "0-1");

        stack.push(1, "1-0");
        stack.push(1, "1-1");

        stack.push(2, "2-0");
        stack.push(2, "2-1");

        stack.push(1, "1-2");
        stack.push(1, "1-3");
        assertEquals("[0-0, 0-1, 1-0, 1-1, 1-2, 1-3, 2-0, 2-1]", Arrays.toString(stack.data));
    }

    @Test
    public void shrink_StackCount_1_Capacity_2() {
        stack = new SingleArrayManyStack(1, 2);

        stack.push(0, "0-0");
        stack.push(0, "0-1");
        assertEquals("[0-0, 0-1]", Arrays.toString(stack.data));

        stack.push(0, "0-2");
        stack.push(0, "0-3");
        assertEquals("[0-0, 0-1, 0-2, 0-3]", Arrays.toString(stack.data));

        stack.pop(0);
        assertEquals("[0-0, 0-1, 0-2, null]", Arrays.toString(stack.data));

        stack.pop(0);
        assertEquals("[0-0, 0-1]", Arrays.toString(stack.data));
    }

    @Test
    public void shrink_StackCount_2_Capacity_2() {
        stack = new SingleArrayManyStack(2, 2);

        stack.push(0, "0-0");
        stack.push(0, "0-1");
        stack.push(1, "1-0");
        stack.push(1, "1-1");
        assertEquals("[0-0, 0-1, 1-0, 1-1]", Arrays.toString(stack.data));

        stack.push(0, "0-2");
        stack.push(0, "0-3");
        assertEquals("[0-0, 0-1, 0-2, 0-3, 1-0, 1-1]", Arrays.toString(stack.data));

        stack.pop(0);
        assertEquals("[0-0, 0-1, 0-2, null, 1-0, 1-1]", Arrays.toString(stack.data));

        stack.pop(0);
        assertEquals("[0-0, 0-1, 1-0, 1-1]", Arrays.toString(stack.data));
        assertEquals("0-1", stack.peek(0));
        assertEquals("1-1", stack.peek(1));
    }
}
