import java.util.LinkedList;

public class Planner {
    private Task[] tasks;
    private boolean hasCycle = false;
    private int cycle;
    private Task startingTask;
    private int timeFrom = 0;

    Planner(Task[] tasks) {
        this.tasks = tasks;
    }

    public void setDepTasks() {
        for (Task task : tasks) {
            if (task.outEdges!=null) {
                for (Task edge: task.outEdges) {
                    if(edge.depTasks == null) {
                        edge.depTasks = new LinkedList<>();
                    }
                    edge.depTasks.add(task);
                }
            }
        }
    }

    public void setStartingTime() {
        for (Task task: tasks) {
            startingTask = task;
            earliestStartingTime(task, 0);
        }
        for (Task task: tasks) {
            latestStartingTime(task);
            if (task.slack == 0) {
                task.critical = true;
            }
        }
    }

    private void earliestStartingTime(Task task, int time) {

        if(task.earliestStart<=time) {
            this.startingTask.earliestStart = time;
        }
        if (task.outEdges!=null) {
            for(Task edge: task.outEdges) {
                earliestStartingTime(edge, time+edge.time);
            }
        }
    }

    private void latestStartingTime(Task task) {
        int fastestTime = fastestTime(this.tasks);
        timeFrom(task, task.earliestStart);
        task.slack = fastestTime - this.timeFrom;
        task.latestStart = task.earliestStart + task.slack;
        this.timeFrom = 0;
    }

    private void timeFrom(Task task, int time) {
        int newTime = time + task.time;
        if (task.depTasks != null) {
            for (Task depTask : task.depTasks) {
                timeFrom(depTask, newTime);
            }
        }
        if (newTime > this.timeFrom) {
            this.timeFrom = newTime;
        }
    }

    private static int fastestTime(Task[] tasks) {
        int time = 0;
        for (Task task : tasks) {
            if(time<task.earliestStart+task.time) {
                time = task.earliestStart+task.time;
            }
        }
        return time;
    }

    public boolean hasCycles() {
        for (Task task: tasks) {
            this.cycle = task.id;
            cycleDFS(task);
            reset();
        }
        return this.hasCycle;
    }

    private void cycleDFS(Task task) {
        if (task.visited) {
            this.hasCycle = true;
            task.partOfCycle = true;
            return;
        }
        else {
            task.visited = true;
            if (task.outEdges!=null) {
                for(Task edge: task.outEdges) {
                    cycleDFS(edge);
                }
            }
        }
    }

    private void reset() {
        for (Task task : tasks) {
            task.visited = false;
        }
    }

    public LinkedList<Task> cycleReturn() {
        LinkedList<Task> cycleList = new LinkedList<>();
        for (Task task: this.tasks) {
            if (task.partOfCycle) {
                cycleList.add(task);
            }
        }
        return cycleList;
    }
}
