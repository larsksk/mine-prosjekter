import java.util.List;
import java.util.LinkedList;

class Task {
    boolean visited = false;
    boolean partOfCycle = false;
    boolean critical = false;
    int id, time, staff;
    String name;
    int earliestStart, latestStart;
    List<Task> outEdges;
    int slack;
    List<Task> depTasks;

    public String toString() {
        return ("-----------------"
                + "\nId:               " + id
                + "\nName:             " + name
                + "\nTime:             " + time
                + "\nStaff:            " + staff
                + "\nEarliest start:   " + earliestStart
                + "\nSlack:            " + slack);
    }
}