import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Regneklynge {
    private int rackSize;
    private String fileName;
    private ArrayList<Rack> rackList = new ArrayList<>();

    // Initialized Regneklynge and runs the readFile() method.
    Regneklynge(String fileName) {
        this.fileName = fileName;
        try{
            readFile();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // Reads through the file, first integer is rackSize and the rest is saved in a 2d array.
    private void readFile() throws Exception{
        Scanner file = new Scanner(new File(fileName));

        file.nextInt();
        int lines = 0;
        int dataNr = 3;
        // Counts line in file, and makes an appropriate sized 2d array for the values.
        while (file.hasNextInt()) {
            lines++;
            for (int i = 0; i < dataNr; i++) {
                file.nextInt();
            }
        }
        int[][] integers = new int[lines][dataNr];
        file.close();

        // Allocating the integers to the 2d array.
        file = new Scanner(new File(fileName));
        rackSize = file.nextInt();
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < dataNr; j++) {
                integers[i][j] = file.nextInt();
            }
        }
        file.close();

        // Creating all the Nodes, as stated in file.
        for (int i = 0; i < lines; i++) {
            Node tempNode = new Node(integers[i][1], integers[i][2]);
            for (int j = 0; j < integers[i][0]; j++) {
                addNode(tempNode);
            }
        }
    }

    // Adds new Node to a Rack. Runs addRack() if no space is found.
    private void addNode(Node node) {
        for (Rack rack : rackList) {
            if (rack.isSpace()) {
                rack.addNode(node);
                return;
            }
        }
        addRack(node);
    }

    // Creates a new rack if there is noe more space for a new Node,
    // and then adds the Node to the new rack
    private void addRack(Node node) {
        int x = rackSize;
        Node[] nodeList = new Node[x];
        Rack newRack = new Rack(x, nodeList);
        rackList.add(newRack);
        addNode(node);
    }

    // Returns number of racks.
    public int antRack() {
        return rackList.size();
    }

    // Counts and returns the number of processors in the existing Nodes.
    public int antProsessor() {
        int nr = 0;
        for (Rack rack : rackList) {
            for (Node node : rack.nodeList) {
                if (node != null) {
                    nr += node.antPros();
                }
            }
        }
        return nr;
    }

    // Counts and returns the number of nodes with at least paakrevdMinne memory.
    public int noderMedNokMinne(int paakrevdMinne) {
        int nr = 0;
        for (Rack rack : rackList) {
            for (Node node : rack.nodeList) {
                if (node != null) {
                    if (node.memory() >= paakrevdMinne) {
                        nr += 1;
                    }
                }
            }
        }
        return nr;
    }
}