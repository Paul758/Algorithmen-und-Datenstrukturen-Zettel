import java.util.ArrayList;

public class  PriorityQueueMinHeap<K extends Comparable<K>> implements PriorityQueue<K> {

    ArrayList<K> a = new ArrayList<K>();

    public static <K extends Comparable<K>> void main(String[] args) throws KeyIsNotValidException {

    }

    //Für Tests
    public PriorityQueueMinHeap(ArrayList<K> list){
        this.a = list;
    }
    public PriorityQueueMinHeap(){

    }


    @Override
    public void addElement(K elem) throws KeyIsNotValidException {
        insertKey(a, elem);
    }

    @Override
    public K getFirst() {
        return a.get(0);
    }

    @Override
    public void deleteFirst() {
        a.set(0, a.get(a.size() - 1));
        a.remove(a.size() - 1);
        minHeapify(a, 0);
    }

    public static <K extends Comparable<K>> void insertKey(ArrayList<K> a, K key) throws KeyIsNotValidException {
        a.add(key);
        decreaseKey(a, a.size() - 1, key);
    }

    public static <K extends Comparable<K>> void decreaseKey(ArrayList<K> a, int i, K key) throws KeyIsNotValidException {
        if (key.compareTo(a.get(i)) > 0) {
            throw new KeyIsNotValidException(a.get(i));
        } else {
            a.set(i, key);
            while (i > 0 && (a.get(getParent(i)).compareTo(a.get(i))) > 0) {
                K buffer = a.get(i);
                a.set(i, a.get(getParent(i)));
                a.set(getParent(i), buffer);
                i = getParent(i);
            }
        }

    }

    private static <K extends Comparable<K>> void minHeapify(ArrayList<K> a, int i) {
        int left = getLeft(i, a);
        int right = getRight(i, a);
        int min = i;
        if (a.get(left).compareTo(a.get(min)) < 0) {
            min = left;
        } else {
            min = i;
        }
        if (a.get(right).compareTo(a.get(min)) < 0) {
            min = right;
        }
        if (min != i) {
            K buffer = a.get(i);
            a.set(i, a.get(min));
            a.set(min, buffer);
            minHeapify(a, min);
        }
    }

    public static int getParent(int i) {
        int index = (i - 1) / 2;
        if (i < 0) {
            return 0;
        }
        return index;
    }

    public static <K extends Comparable<K>> int getLeft(int i, ArrayList<K> a) {
        int index = (2 * i) + 1;
        if (index >= a.size()) {
            return i;
        }
        return index;
    }

    public static <K extends Comparable<K>> int getRight(int i, ArrayList<K> a) {
        int index = (2 * i) + 2;
        if (index >= a.size()) {
            return i;
        }
        return index;
    }

}