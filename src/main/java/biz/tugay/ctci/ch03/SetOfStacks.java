package biz.tugay.ctci.ch03;

import java.util.ArrayList;
import java.util.List;

class SetOfStacks<T> implements Stack<T> {

    List<Stack<T>> listOfStacks = new ArrayList<>();
    List<Integer> count = new ArrayList<>();

    int threshold;

    public SetOfStacks(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public T pop() {
        for (int i = count.size() - 1; i >= 0; i--)
            if (!listOfStacks.get(i).isEmpty())
                return getVal(i);
        return null;
    }

    @Override
    public void push(T t) {
        if (count.isEmpty() || count.get(count.size() - 1) == threshold) {
            listOfStacks.add(new ArrayStack<>());
            count.add(0);
        }
        listOfStacks.get(listOfStacks.size() - 1).push(t);
        count.set(count.size() - 1, count.get(count.size() - 1) + 1);
    }

    @Override
    public T peek() {
        for (int i = count.size() - 1; i >= 0; i--)
            if (!listOfStacks.get(i).isEmpty())
                return listOfStacks.get(i).peek();
        return null;
    }

    @Override
    public boolean isEmpty() {
        for (int i = count.size() - 1; i >= 0; i--)
            if (!listOfStacks.get(i).isEmpty())
                return false;
        return true;
    }

    T popAt(int i) {
        if (!listOfStacks.get(i).isEmpty())
            return getVal(i);
        return null;
    }

    private T getVal(int i) {
        T val = listOfStacks.get(i).pop();
        count.set(i, count.get(i) - 1);
        if (count.get(i) == 0) {
            listOfStacks.remove(i);
            count.remove(i);
        }
        return val;
    }
}
