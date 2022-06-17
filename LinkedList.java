public class LinkedList<T extends Comparable<T>> {
    ListElement<T> head;

    public LinkedList(){
        this.head = new ListElement<>();
    }

    public void insertAtHead(T key){
        if(head == null){
            head.key = key;
        } else {
            ListElement<T> temp = head;
            head = new ListElement<>(key);
            head.next = temp;
        }
    }

    public void remove(T key){
        ListElement<T> prev = new ListElement<>();
        ListElement<T> current = head;
        prev.next = current;

        while(current.next != null){
            if(current.key.equals(key)){
                if(current == head){
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                break;
            } else {
                prev = current;
                current = current.next;
            }
        }
    }

    public boolean search(T key){
        ListElement<T> current = head;

        while(current.next != null){
            if(current.key.equals(key)){
                return true;
            } else {
                current = current.next;
            }
        }
        return false;
    }
}
