public class Node<T> {
    Node<T> parent;
    Node<T> left;
    Node<T> right;
    T key;

    public Node(T key){
        this.key = key;
    }
}


