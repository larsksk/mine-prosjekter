import java.util.List;
import java.util.LinkedList;

class Node {
    private int label;
    private boolean visited = false;
    private List<Node> neighbors = new LinkedList<Node>();

    public Node(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public boolean isVisited() {
        return visited;
    }

    public void visit() {
        visited = true;
    }

    public void leave() {
        visited = false;
    }

    public void addNeighbor(Node n) {
        // legger til en uretta kant fra this til n
        if (!neighbors.contains(n)) {
            neighbors.add(n);
            n.addNeighbor(this);
        }
    }

    public void addSuccessor(Node n) {
        // legger til en retta kant fra this til n
        if (!neighbors.contains(n)) {
            neighbors.add(n);
        }
    }

    public String toString() {
        return Integer.toString(label);
    }
}

class Graph {
    private Node[] nodes;

    public Graph(Node[] nodes) {
        this.nodes = nodes;
    }

    public void printNeighbors() {
        for (Node n1 : nodes) {
            String s = n1.toString() + ": ";
            for (Node n2 : n1.getNeighbors()) {
                s += n2.toString() + " ";
            }
            System.out.println(s.substring(0, s.length() - 1));
        }
    }

    private static Graph buildExampleGraph() {
        // ukeoppgave
        Node[] nodes = new Node[7];
        for (int i = 0; i < 7; i++) {
            nodes[i] = new Node(i);
        }
        nodes[0].addNeighbor(nodes[1]);
        nodes[0].addNeighbor(nodes[2]);
        nodes[1].addNeighbor(nodes[2]);
        nodes[2].addNeighbor(nodes[3]);
        nodes[2].addNeighbor(nodes[5]);
        nodes[3].addNeighbor(nodes[4]);
        nodes[4].addNeighbor(nodes[5]);
        nodes[5].addNeighbor(nodes[6]);
        return new Graph(nodes);
    }

    private static Graph buildRandomSparseGraph(int numberofV, long seed) {
        // seed brukes av java.util.Random for å generere samme sekvens for samme frø
        // (seed) og numberofV
        java.util.Random tilf = new java.util.Random(seed);
        int tilfeldig1 = 0, tilfeldig2 = 0;
        Node[] nodes = new Node[numberofV];

        for (int i = 0; i < numberofV; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < numberofV; i++) {
            tilfeldig1 = tilf.nextInt(numberofV);
            tilfeldig2 = tilf.nextInt(numberofV);
            if (tilfeldig1 != tilfeldig2)
                nodes[tilfeldig1].addNeighbor(nodes[tilfeldig2]);
        }
        return new Graph(nodes);
    }

    private static Graph buildRandomDenseGraph(int numberofV, long seed) {
        java.util.Random tilf = new java.util.Random(seed);
        int tilfeldig1 = 0, tilfeldig2 = 0;
        Node[] nodes = new Node[numberofV];

        for (int i = 0; i < numberofV; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < numberofV * numberofV; i++) {
            tilfeldig1 = tilf.nextInt(numberofV);
            tilfeldig2 = tilf.nextInt(numberofV);
            if (tilfeldig1 != tilfeldig2)
                nodes[tilfeldig1].addNeighbor(nodes[tilfeldig2]);
        }
        return new Graph(nodes);
    }

    private static Graph buildRandomDirGraph(int numberofV, long seed) {
        java.util.Random tilf = new java.util.Random(seed);
        int tilfeldig1 = 0, tilfeldig2 = 0;
        Node[] nodes = new Node[numberofV];

        for (int i = 0; i < numberofV; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < 2 * numberofV; i++) {
            tilfeldig1 = tilf.nextInt(numberofV);
            tilfeldig2 = tilf.nextInt(numberofV);
            if (tilfeldig1 != tilfeldig2)
                nodes[tilfeldig1].addSuccessor(nodes[tilfeldig2]);
        }
        return new Graph(nodes);
    }

    public void DFS(Node s) {
        s.visit();
        for (Node n : s.getNeighbors()) {
            if (!n.isVisited()) {
                DFS(n);
            }
        }
    }

    public void DFSFull() {
        // TODO
    }

    public int numberOfComponents() {
        // Oppgave 1A
        int components = 0;
        for (Node n : nodes) {
            if (!n.isVisited()) {
                components++;
                DFS(n);
            }
        }
        reset();
        return components;
    }

    public void transDFS(Node s, Node[] newNodes) {
        s.visit();
        for (Node n : s.getNeighbors()) {
            s.addNeighbor(n);
            newNodes[s.getLabel()].addNeighbor(newNodes[n.getLabel()]);
            if (!n.isVisited()) {
                transDFS(n, newNodes);
            }
        }
    }

    public Graph transformDirToUndir() {
        // Oppgave 1B
        int size = nodes.length;
        Node[] newNodes = new Node[size];
        for (int i = 0; i<size; i++) {
            newNodes[i] = new Node(i);
        }

        for (Node n : nodes) {
            if (!n.isVisited()) {
                transDFS(n, newNodes);
            }
        }

        reset();
        return new Graph(newNodes); // returner en NY graf
    }

    public boolean isConnected(){
        // Oppgave 1C
        // TODO
        Graph transformed = transformDirToUndir();
        return (transformed.numberOfComponents()==1);
    }

    public LinkedList<Node> biggestDFS(Node s, LinkedList<Node> list) {
        s.visit();
        list.add(s);
        for (Node n : s.getNeighbors()) {
            if (!n.isVisited()) {
                biggestDFS(n, list);
            }
        }
        return list;
    }

    public Graph biggestComponent() {
        // Oppgave 1D
        LinkedList<Node> biggestList = new LinkedList<>();

        for (Node n : nodes) {
            if (!n.isVisited()) {
                LinkedList<Node> nodeList = new LinkedList<>();
                nodeList = biggestDFS(n, nodeList);
                if (nodeList.size() > biggestList.size()) {
                    biggestList = nodeList;
                }
            }
        }

        Node[] newNodes = new Node[biggestList.size()];
        for (int i = 0; i < biggestList.size(); i++) {
            newNodes[i] = biggestList.get(i);
        }
        reset();
        return new Graph(newNodes);
    }

    public int[][] buildAdjacencyMatrix() {
        // Oppgave 1E
        int l = nodes.length;
        int[][] adjMatrix = new int[l][l];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                adjMatrix[i][j] = 0;
            }
        }

        for (int k = 0; k < l; k++) {
            for (Node n : nodes[k].getNeighbors()) {
                adjMatrix[k][n.getLabel()] = 1;
            }
        }
        return adjMatrix;
    }

    public void printMatrix(int[][] matrix) {
        int l = nodes.length;
        System.out.print("   ");
        for (int n = 0; n < l; n++) {
            System.out.print(" " + n);
        }
        System.out.print("\n   ");
        for (int m = 0; m < l; m++) {
            System.out.print(" -");
        }
        for (int i = 0; i < l; i++) {
            System.out.print("\n" + i + " |");
            for (int j = 0; j < l; j++) {
                System.out.print(" " + matrix[i][j]);
            }

        }
        System.out.println("");
    }

    public void resetDFS(Node s) {
        s.leave();
        for (Node n : s.getNeighbors()) {
            if (n.isVisited()) {
                resetDFS(n);
            }
        }
    }

    public void reset() {
        for (Node n : nodes) {
            if (n.isVisited()) {
                resetDFS(n);
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = buildExampleGraph();
        System.out.println("Neighbors:");
        graph.printNeighbors();

        System.out.println("");
        System.out.println("Number of components in graph: " + graph.numberOfComponents());

        System.out.println("\nTransformed graph:");
        Graph transGraph = graph.transformDirToUndir();
        transGraph.printNeighbors();

        System.out.println("\nConnected:");
        System.out.println(graph.isConnected());

        System.out.println("\nBiggest component:");
        Graph biggest = graph.biggestComponent();
        biggest.printNeighbors();

        System.out.println("\nAdjacency matrix:");
        graph.printMatrix(graph.buildAdjacencyMatrix());


        /*graph = buildRandomSparseGraph(11, 201909202359L);
        graph.printNeighbors();
        Graph biggest2 = graph.biggestComponent();
        biggest2.printNeighbors();
        System.out.println("");


        graph = buildRandomDenseGraph(15, 201909202359L);
        graph.printNeighbors();*/
    }
}