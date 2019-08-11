package biz.tugay.ctci.ch03;

/**
 * Implements 3 stacks in a single array
 */
public class ArrayManyStackImpl {

    int stackCapacity = 2, stackCount = 3, stackPtr[] = {0, 2, 4};
    String[] data = new String[stackCount * stackCapacity];

    public String pop(int i) {
        return isEmpty(i) ? null : data[--stackPtr[i]];
    }

    public void push(int i, String datum) {
        if (stackPtr[i] == (i + 1) * stackCapacity)
            doubleCapacity();

        data[stackPtr[i]++] = datum;
    }

    public String peek(int i) {
        return isEmpty(i) ? null : data[stackPtr[i] - 1];
    }

    public boolean isEmpty(int i) {
        return stackPtr[i] == i * stackCapacity;
    }

    // Doubles the capacity, copies data, modifies stack pointers
    // ["foo", "bar", null, null, "baz", "qux"]
    // becomes
    // ["foo", "bar", null, null, null, null, null, null "baz", "qux", null, null]
    private void doubleCapacity() {
        String[] copy = new String[data.length * 2];

        for (int i = 0; i < stackCount; i++)
            for (int j = 0; j < stackCapacity; j++)
                copy[2 * (stackCapacity * i) + j] = data[stackCapacity * i + j];

        for (int i = 0; i < stackCount; i++)
            stackPtr[i] = stackPtr[i] + (i * stackCapacity);

        stackCapacity *= 2;
        data = copy;
    }

}
