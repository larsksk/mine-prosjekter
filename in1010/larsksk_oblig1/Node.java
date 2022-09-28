public class Node {
    private int memory;
    private int antPros;

    Node(int memory, int antPros) {
        this.memory = memory;
        this.antPros = antPros;
    }

    // Returns memory value of the Node.
    public int memory() {
        return memory;
    }

    // Returns the number of processors in the Node.
    public int antPros() {
        return antPros;
    }
}