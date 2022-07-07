import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class DirectedGraph {

    Hashtable<Integer, ArrayList<Integer>> adjacencyList;

    DirectedGraph(){
        adjacencyList = new Hashtable<>();
    }

    DirectedGraph(Integer n){
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
        } else {
            throw new RuntimeException("Cant create edge, because one or two nodes are missing");
        }
    }
    public void deleteEdge(Integer i, Integer j){
        if(adjacencyList.containsKey(i) && adjacencyList.containsKey(j)){
            adjacencyList.get(i).remove(j);
        } else {
            throw new RuntimeException("Cant create edge, because one or two nodes are missing");
        }
    }

}
