package biz.tugay.ctci.ch03;

class QueueViaStacks<T> implements Stack<T> {

    Stack<T> pushStack = new ArrayStack<>();
    Stack<T> popStack = new ArrayStack<>();

    @Override
    public T pop() {
        transferFromTo(pushStack, popStack);
        return popStack.pop();
    }

    @Override
    public void push(T t) {
        transferFromTo(popStack, pushStack);
        pushStack.push(t);
    }

    @Override
    public T peek() {
        transferFromTo(pushStack, popStack);
        return popStack.peek();
    }

    @Override
    public boolean isEmpty() {
        transferFromTo(popStack, pushStack);
        return pushStack.isEmpty();
    }

    private void transferFromTo(Stack<T> from, Stack<T> to) {
        T t;
        if (!from.isEmpty())
            while ((t = from.pop()) != null)
                to.push(t);
    }
}
