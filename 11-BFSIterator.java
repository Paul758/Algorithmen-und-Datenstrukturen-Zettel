import java.util.*;

public class BFSIterator implements Iterator<Integer> {

    private UndirectedGraph graph;
    private Queue<Integer> greyNodesQueue;
    private Hashtable<Integer, Integer> vertexDistance;
    private Hashtable<Integer, ArrayList<Integer>> whiteNodesTable;
    public BFSIterator(UndirectedGraph g, Integer s){
        this.graph = g;
        greyNodesQueue = new LinkedList<Integer>();
        vertexDistance = new Hashtable<>();
        whiteNodesTable = new Hashtable<>();
        whiteNodesTable.putAll(graph.adjacencyList);
        breadthFirstSearch(s);

    }

    @Override
    public boolean hasNext() {
        if(greyNodesQueue.peek() == null){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Integer next(){
        Integer u;
        if((u = greyNodesQueue.poll()) == null){
            return null;
        } else {
            for(Integer v : graph.adjacencyList.get(u)){
                if(whiteNodesTable.containsKey(v)){
                    whiteNodesTable.remove(v);
                    vertexDistance.put(v, vertexDistance.get(u) + 1);
                    greyNodesQueue.add(v);
                }
            }
            return u;
        }
    }

    public Integer dist(Integer v){
       return vertexDistance.get(v);
    }

    public void breadthFirstSearch(Integer s){
        whiteNodesTable.remove(s);
        vertexDistance.put(s, 0);
        greyNodesQueue.add(s);
    }
}
