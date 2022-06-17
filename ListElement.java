public class ListElement<T extends Comparable<T>> {
    T key;
    ListElement<T> next;

    public ListElement(T key){
        this.key = key;
        next = null;
    }
    public ListElement(){
    }

}
