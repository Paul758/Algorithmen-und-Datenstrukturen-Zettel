public class Treenode<T extends Comparable<T>> {
    T key;
    Treenode<T> left;
    Treenode<T> right;
    Treenode<T> parent;

    public Treenode(T key){
        this.key = key;
    }
}
