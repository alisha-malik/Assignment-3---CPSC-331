package BST;

public interface BSTInterface<T> {
    boolean isEmpty();
    boolean isFull();
    int size();
    void clear();
    T add(T element); 
    boolean contains(T element);
    void remove(T element);
    void reset(int order);
    T getNext(int order);
    void inorderTraversal();
    void postorderTraversal();
    void preorderTraversal();
}