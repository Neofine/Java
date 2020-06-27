public class Stack implements IStack {
    private int tab[] = new int[10];
    private int iterator = -1;
    public void Full() throws FullStackException {
        throw new FullStackException();
    }
    public void Empty() throws EmptyStackException {
        throw new EmptyStackException();
    }
    @Override
    public boolean empty() {
        if (iterator == -1)
            return true;
        else return false;
    }

    @Override
    public void push(int value) throws FullStackException {
        if (iterator == 9) {
            Full();
        }
        else tab[++iterator] = value;
    }

    @Override
    public int pop() throws EmptyStackException{
        if (iterator == -1) {
            Empty();
        }
        return tab[iterator--];
    }

    @Override
    public int size() {
        return iterator+1;
    }
}
