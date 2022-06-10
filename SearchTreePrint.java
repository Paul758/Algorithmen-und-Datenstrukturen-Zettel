import java.lang.*;
import java.util.ArrayList;

public class SearchTreePrint {

    public static void main(String[] args) {
        BalancedSearchTree<Integer> tree = new BalancedSearchTree<>();
        tree.root = new Node<>(30);


        tree.root.left = new Node<>(15);
        tree.root.right = new Node<>(45);



        tree.root.left.left = new Node<>(5);
        tree.root.left.right = new Node<>(25);

        tree.root.right.left = new Node<>(35);
        tree.root.right.right = new Node<>(60);



        tree.root.left.left.left = new Node<>(3);
        tree.root.left.left.right = new Node<>(11);

        tree.root.left.right.left = new Node<>(20);
        tree.root.left.right.right = new Node<>(29);

        tree.root.right.left.left = new Node<>(31);
        tree.root.right.left.right = new Node<>(40);

        tree.root.right.right.left = new Node<>(50);
        tree.root.right.right.right = new Node<>(70);

        SearchTreePrint.printTree(tree);
    }

    public static void printTree(BalancedSearchTree treeToPrint){
        int rootLevel = getNodeLevel(treeToPrint.root);
        int height = rootLevel;
        System.out.println("The height of the Tree is: " + rootLevel);


        ArrayList<Integer> nodes = new ArrayList<>(10);
        for(int i = 0; i < Math.pow(2,(rootLevel)) - 1; i++){
            nodes.add(null);
        }

        ArrayList<Integer> newNodes = createNodeList(0, nodes, treeToPrint.root);
        System.out.println(newNodes);


        for(int i = height, listCounter = 0; i > 0; i--, listCounter++){
            printWhiteSpaces(Math.pow(2,(i)) - 1);
            printNodesOnCurrentLevel(i, listCounter, newNodes);
        }
    }

    private static void printNodesOnCurrentLevel(int height, int listCounter, ArrayList<Integer> newNodes) {
        for(int k = (int) Math.pow(2,(listCounter)) - 1, i = 0; i < Math.pow(2,listCounter); k++, i++){
            System.out.print(newNodes.get(k));
            printWhiteSpaces(Math.pow(2,(height + 1)) - 1);
        }
        System.out.println(" ");
        System.out.println(" ");

    }

    public static void printWhiteSpaces(double count){
        for(int i = 0; i < count; i++){
            System.out.print(" ");
        }
    }

    private static ArrayList<Integer> createNodeList(int i, ArrayList<Integer> nodes, Node currentNode) {
        if(currentNode == null){
            return nodes;
        } else {
            nodes.set(i, (Integer) currentNode.key);
            createNodeList((2 * i) + 1, nodes, currentNode.left);
            createNodeList((2 * i) + 2, nodes, currentNode.right);
        }
        return nodes;
    }

    public static int getNodeLevel(Node node){
        if(node == null){
            return 0;
        } else {
            return Math.max(getNodeLevel(node.left), getNodeLevel(node.right)) + 1;
        }
    }
}
