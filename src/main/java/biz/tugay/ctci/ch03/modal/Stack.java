package biz.tugay.ctci.ch03.modal;

public interface Stack<T> {

    /**
     * Removes the top item from the stack
     *
     * @return Top item
     */
    T pop();


    /**
     * Adds item to top of stack
     *
     * @param t Item to add to top of stack
     */
    void push(T t);


    /**
     * Peeks at the top item of the stack
     *
     * @return Top item from the stack
     */
    T peek();

    /**
     * @return true in case stack has no items
     */
    boolean isEmpty();
}
