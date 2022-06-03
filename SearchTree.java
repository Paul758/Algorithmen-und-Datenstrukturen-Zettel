import java.util.ArrayList;

public class SearchTree<T extends Comparable<T>> {

    Treenode<T> root;

    public SearchTree(){
        this.root = null;
    }

    public void insert(Treenode<T> newNode){
        if(root == null){
            root = newNode;
            return;
        }
        Treenode<T> x = root;
        while(x != null){
            newNode.parent = x;
            if(newNode.key.compareTo(x.key) < 0){
                x = x.left;

            } else {
                x = x.right;

            }
        }
        //newNode.parent = x;
        if(newNode.key.compareTo(newNode.parent.key) < 0){
            newNode.parent.left = newNode;
        } else {
            newNode.parent.right = newNode;
        }
    }

    public void delete(T key){
        Treenode<T> z = search(root, key);
        Treenode<T> y;
        Treenode<T> x;

       //Bestimme Knoten, der gelöscht werden soll
        if(z.left == null || z.right == null){
            y = z;
        } else {
            y = findTreeSuccessor(z);
        }

        if(y.left != null){
            x = y.left;
        } else {
            x = y.right;
        }

        if(x != null){
            x.parent = y.parent;
        }
        if(y.parent == null){
            root = x;
        } else if(y == y.parent.left){
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }

        if(y != z){
            z.key = y.key;
        }
    }

    public Treenode<T> search(Treenode<T> x, T key) {
        if(x == null || x.key == key ){
            return x;
        }
        if(x.key.compareTo(key) < 0){
            return search(x.right,key);
        } else {
            return search(x.left,key);
        }
    }

    public Treenode<T> findTreeSuccessor(Treenode<T> keyNode) {
        if(keyNode.right != null){
            return getMinimum(keyNode.right);
        }
        Treenode<T> y = keyNode.parent;
        while(y != null && keyNode.equals(y.right)){
            keyNode = y;
            y = keyNode.parent;
        }
        return y;
    }

    public void InorderTreeWalk(Treenode<T> x){
       if(x != null){
           InorderTreeWalk(x.left);
           System.out.println(x.key);
           InorderTreeWalk(x.right);
       }
    }

    public ArrayList<T> toSortedArrayList(){
        ArrayList<T> sortedList = new ArrayList<>();
        return toSortedArrayListStart(sortedList, root);
    }

    public ArrayList<T> toSortedArrayListStart(ArrayList<T> arrayList, Treenode<T> x){

        if(x != null){
            toSortedArrayListStart(arrayList, x.left);
            arrayList.add(x.key);
            toSortedArrayListStart(arrayList, x.right);
        }
        return arrayList;
    }

    public Treenode<T> getMinimum(Treenode<T> startNode){
        Treenode<T> x = startNode;
        while(x.left != null){
            x = x.left;
        }
        return x;
    }

    public Treenode<T> getMaximum(){
        Treenode<T> x = root;
        while(x.right != null){
            x = x.right;
        }
        return x;
    }
}
