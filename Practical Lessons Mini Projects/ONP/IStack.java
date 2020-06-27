
public interface IStack {

    class FullStackException extends Exception {
        public String toString() {
            return "Stack is full";
        }
    }

    class EmptyStackException extends Exception {
        public String toString() {
            return "Trying to make operation while having less than 2 items";
        }
    }

    void Full() throws FullStackException;

    void Empty() throws EmptyStackException;

    boolean empty();

    void push(int value) throws FullStackException;

    int pop() throws EmptyStackException;

    public int size();
}
