import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class UndirectedGraph {

    Hashtable<Integer, ArrayList<Integer>> adjacencyList;

    UndirectedGraph(){
        adjacencyList = new Hashtable<>();
    }
    UndirectedGraph(Integer n){
        adjacencyList = new Hashtable<>(n);
        for(int i = 1; i <= n; i++){
            adjacencyList.put(i, new ArrayList<Integer>());
        }
    }
    public void addVertex(Integer i){
        if(adjacencyList.containsKey(i)){
            return;
        } else {
            adjacencyList.put(i, new ArrayList<Integer>());
        }
    }
    public void addEdge(Integer i, Integer j){
        if(adjacencyList.containsKey(i) && adjacencyList.containsKey(j)){
            adjacencyList.get(i).add(j);
            adjacencyList.get(j).add(i);
        } else {
            throw new RuntimeException("Cant create edge, because one or two nodes are missing");
        }

    }
    public void deleteEdge(Integer i, Integer j){
        if(adjacencyList.containsKey(i) && adjacencyList.containsKey(j)){
            adjacencyList.get(i).remove(j);
            adjacencyList.get(j).remove(i);
        } else {
            throw new RuntimeException("Cant create edge, because one or two nodes are missing");
        }

    }
}
