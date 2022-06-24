public abstract class Hashmap<T extends Comparable<T>> {
    protected final int m;
    protected final T[] hashmap;
    protected final float c1;
    protected final float c2;
    protected final String DELETED = "DELETED";
    protected final CollisionHandling mode;

    public Hashmap(T[] hashmap, int size, CollisionHandling mode) {
        this.hashmap = hashmap;
        this.m = size;
        this.mode = mode;
        this.c1 = 0.5f;
        this.c2 = 0.5f;
    }

    protected enum CollisionHandling{
        LinearProbing, QuadraticProbing,  DoubleHashing
    }

    public void insert(T key){
        int i = 0;
        int j;
        while(i < m){
            j = (int) hashFunction(key, i, mode);
            if(hashmap[j] == null || hashmap[j].equals(DELETED)  ){
                hashmap[j] = key;
                return;
            } else {
                i++;
            }
        }
        System.out.println("Error at inserting value:" + key);
    }

    public int search(T key){
        int i = 0;
        int j;
        while(i < m){
            j = (int) hashFunction(key, i, mode);
            if(hashmap[j] == null){
                return -1;
            }
            if(hashmap[j].equals(key)){
                return j;
            }
            i++;
        }
        return -1;
    }
    public abstract void delete(T key);
    public abstract long hashFunction(T key, int index, CollisionHandling mode);
}
