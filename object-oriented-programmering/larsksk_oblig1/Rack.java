public class Rack {
    private int rackSize;
    public Node[] nodeList;

    Rack(int rackSize, Node[] nodeList) {
        this.rackSize = rackSize;
        this.nodeList = nodeList;
    }

    // Checks if there are any empty spaces for Nodes in the Rack.
    // Returns True if there is, and otherwise returns False.
    public boolean isSpace() {
        int nr = 0;
        for (Node node : nodeList) {
            if (node != null) {
                nr++;
            }
        }
        return (nr != rackSize);
    }

    // Adds the new Node to an empty space in the Rack.
    public void addNode(Node node) {
        for (int i = 0; i < rackSize; i++) {
            if (nodeList[i] == null) {
                nodeList[i] = node;
                return;
            }
        }
    }
}