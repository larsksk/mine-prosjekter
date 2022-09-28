import java.util.LinkedList;

class Printer {

    static void print(Task[] tasks){
        formatTime(tasks);
        System.out.println("\n\nTASKS:");
        for (Task task: tasks) {
            System.out.print(task.toString());
            if (task.depTasks != null) {
                System.out.print("\nDependency tasks: ");
                for (Task depTask : task.depTasks) {
                    System.out.print(depTask.id + " ");
                }
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }

    static void formatTime(Task[] tasks) {
        int fastestTime = fastestTime(tasks);
        int manpower = 0;
        for (int i = 0; i<fastestTime+1; i++) {
            LinkedList<Task> starting = new LinkedList<>();
            LinkedList<Task> ending = new LinkedList<>();
            boolean changeStarting = false;
            boolean changeEnding = false;
            for (Task task : tasks) {
                if (task.earliestStart == i) {
                    changeStarting = true;
                    starting.add(task);
                    manpower += task.staff;
                }
                if (task.earliestStart+task.time == i) {
                    changeEnding = true;
                    ending.add(task);
                    manpower -= task.staff;
                }
            }
            if (changeStarting || changeEnding) {
                System.out.println("Time: " + i);
                if (changeEnding) {
                    for (Task task : ending) {
                        System.out.println("         Finished: " + task.name);
                    }
                }
                if (changeStarting) {
                    for (Task task : starting) {
                        System.out.println("         Starting: " + task.name);
                    }
                }
                if (manpower!=0) {
                    System.out.println("         Current staff: " + manpower);
                }
                System.out.println();
            }
        }
        System.out.println("***** Shortest possible project execution is " + fastestTime + " ****");
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

    static void printNE(LinkedList<Task> cycleTasks) {
        System.out.println("\nCO-DEPENDING TASKS:");
        for (Task task: cycleTasks) {
            System.out.print(task.toString());
            if (task.depTasks != null) {
                System.out.print("\nDependency tasks: ");
                for (Task depTask : task.depTasks) {
                    System.out.print(depTask.id + " ");
                }
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }
}
