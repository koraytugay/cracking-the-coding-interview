package biz.tugay.ctci.ch03;

import java.util.TreeMap;

/**
 * Stack Implementation that can also peek or pop the minimum value from within.
 * See StackMinTest for example usage.
 */
class StackMin<V extends Comparable<V>> {

    // The underlying typical stack implementation with push and pop behaviour only
    ArrayStack<V> arrayStack = new ArrayStack<>();

    // Map of minimum value and the indices found within the underlying arrayStack
    TreeMap<V, Stack<Integer>> valIndices = new TreeMap<>();

    V peek() {
        refreshStack();
        return arrayStack.peek();
    }

    V pop() {
        refreshStack();
        if (isEmpty())
            return null;

        V v = arrayStack.pop();

        // Remove popped value from the valIndices map
        valIndices.get(v).pop();
        // Remove the key val from the map altogether if the value
        // not found in any indices
        if (valIndices.get(v).isEmpty())
            valIndices.remove(v);

        return v;
    }

    void push(V v) {
        arrayStack.push(v);

        // keep track to which index this value has been pushed
        if (valIndices.get(v) == null)
            valIndices.put(v, new ArrayStack<>());
        valIndices.get(v).push(arrayStack.head);
    }

    boolean isEmpty() {
        refreshStack();
        return arrayStack.isEmpty();
    }

    V peekMin() {
        // valIndices is a sorter map
        return isEmpty() ? null : valIndices.firstEntry().getKey();
    }

    V popMin() {
        if (valIndices.isEmpty())
            return null;

        V val = valIndices.firstEntry().getKey();
        Integer minIndex = valIndices.get(val).pop();
        if (valIndices.get(val).isEmpty())
            valIndices.remove(val);
        arrayStack.data.set(minIndex, null);
        return val;
    }

    // popMin sets indices in the underlying stack to null
    // underlying finds the current non-null head with this method
    private void refreshStack() {
        while (!arrayStack.isEmpty() && arrayStack.peek() == null)
            arrayStack.pop();
    }
}
