package biz.tugay.ctci.ch03;

/**
 * Implements stackCount of stacks in a single array with each having an initial capacity of initialCapacityPerStack.
 * push, peek, pop and isEmpty methods must be called with the desired stack index where index starts from 0.
 *
 * Each individual stack doubles in capacity when more capacity is needed and shrinks immediately in half when it can.
 * See SingleArrayManyStackTest for usage examples.
 */
class SingleArrayManyStack {

    // start inclusive, end exclusive
    int stackCount, initialCapacityPerStack, start[], head[], end[];
    String[] data;

    SingleArrayManyStack(int stackCount, int initialCapacityPerStack) {
        this.stackCount = stackCount;
        this.initialCapacityPerStack = initialCapacityPerStack;

        start = new int[stackCount];
        head = new int[stackCount];
        end = new int[stackCount];

        for (int i = 0; i < stackCount; i++) {
            start[i] = i * initialCapacityPerStack;
            end[i] = start[i] + initialCapacityPerStack;
            head[i] = start[i];
        }

        data = new String[stackCount * initialCapacityPerStack];
    }

    String pop(int stack) {
        if (isEmpty(stack))
            return null;

        String val = data[--head[stack]];
        data[head[stack]] = null;

        shrink(stack);
        return val;
    }

    void push(int stack, String datum) {
        if (head[stack] == end[stack])
            expand(stack);

        data[head[stack]] = datum;
        head[stack] = head[stack] + 1;
    }

    String peek(int stack) {
        return isEmpty(stack) ? null : data[head[stack] - 1];
    }

    boolean isEmpty(int stack) {
        return head[stack] == start[stack];
    }

    void expand(int stack) {
        int expansion = end[stack] - start[stack];
        String[] copy = new String[data.length + expansion];

        for (int i = 0; i < end[stack]; i++)
            copy[i] = data[i];

        if (stackCount > stack + 1) {
            for (int i = start[stack + 1]; i < data.length; i++)
                copy[i + expansion] = data[i];

            for (int i = stack + 1; i < stackCount; i++) {
                start[i] = start[i] + expansion;
                head[i] = head[i] + expansion;
                end[i] = end[i] + expansion;
            }
        }

        end[stack] += expansion;
        data = copy;
    }

    void shrink(int stack) {
        if (end[stack] - start[stack] != 2 * (head[stack] - start[stack]))
            return;

        int expansion = head[stack] - start[stack];
        String[] copy = new String[data.length - expansion];

        for (int i = 0; i < head[stack]; i++)
            copy[i] = data[i];

        if (stackCount > stack + 1) {
            for (int i = start[stack + 1]; i < data.length; i++)
                copy[i - expansion] = data[i];

            for (int i = stack + 1; i < stackCount; i++) {
                start[i] = start[i] - expansion;
                head[i] = head[i] - expansion;
                end[i] = end[i] - expansion;
            }
        }

        end[stack] = end[stack] - expansion;
        this.data = copy;
    }
}
