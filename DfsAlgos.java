import java.util.*;

public class DfsAlgos {
    private DirectedGraph graph;
    private LinkedList<Integer> greyNodesList;
    private Hashtable<Integer, ArrayList<Integer>> whiteNodesTable;
    ArrayList<Integer> whiteNodesList;

    public DfsAlgos(DirectedGraph g){
        this.graph = g;
        greyNodesList = new LinkedList<>();
        whiteNodesTable = new Hashtable<>();
        whiteNodesTable.putAll(graph.adjacencyList);
        whiteNodesList = new ArrayList<>();

    }
    public LinkedList<Integer> topSort(DirectedGraph g){
       if(detectCycle(g) == null){
           LinkedList<Integer> topSortList = new LinkedList<>();
           topSortList = depthFirstSearch(topSortList);
           System.out.println("The graph doesnt have a cycle. The topological sorting is: " + topSortList);
           return topSortList;
       } else {
           return null;
       }
    }

    public LinkedList<Integer> detectCycle(DirectedGraph g){
        LinkedList<Integer> cycleList = new LinkedList<>();

        Set<Integer> keySet = whiteNodesTable.keySet();
        whiteNodesList.addAll(keySet);

        for(Integer v : graph.adjacencyList.keySet()){
            if(whiteNodesList.contains(v)){
                cycleList = dfsVisitDetectCycle(graph, v, cycleList);
            }
        }
        if(cycleList.isEmpty()){
            return null;
        } else {
            System.out.println("The graph has a cycle. The list of the nodes in the cycle is: " + cycleList);
            return cycleList;
        }
    }

    public LinkedList<Integer> dfsVisitDetectCycle(DirectedGraph graph, Integer u, LinkedList<Integer> list){
        greyNodesList.addFirst(u);
        whiteNodesList.remove(u);

        for(Integer v : graph.adjacencyList.get(u)){
            //Prüfen, ob eine Rückärtskante existiert
            if(greyNodesList.contains(v)){
                list.addFirst(v);
                for(int i = 0; i < greyNodesList.size(); i++){
                    if(greyNodesList.get(i).equals(v)){
                        break;
                    }
                    list.addFirst(greyNodesList.get(i));
                }
                return list;
            }
            else if (whiteNodesList.contains(v)) {
                dfsVisitDetectCycle(graph, v, list);
            }
        }
        greyNodesList.remove(u);
        return list;
    }

    public LinkedList<Integer> depthFirstSearch(LinkedList<Integer> topSortList){
        Set<Integer> keySet = whiteNodesTable.keySet();
        whiteNodesList.addAll(keySet);
        for(Integer v : graph.adjacencyList.keySet()){
            if(whiteNodesList.contains(v)){
                topSortList = dfsVisit(graph, v, topSortList);
            }
        }
        return topSortList;
    }

    public LinkedList<Integer> dfsVisit(DirectedGraph graph, Integer u, LinkedList<Integer> list){
        greyNodesList.addFirst(u);
        whiteNodesList.remove(u);
        for(Integer v : graph.adjacencyList.get(u)){
            if(whiteNodesList.contains(v)){
               list = dfsVisit(graph, v, list);
            }
        }
        greyNodesList.remove(u);
        //U am Kopf der List einfügen
        list.addFirst(u);
        return list;
    }
}
