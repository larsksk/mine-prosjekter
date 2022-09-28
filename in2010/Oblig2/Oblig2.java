public class Oblig2 {
    public static void main(String[] args) {

        String inputFile;
        if (args.length!=1) {
            return;
        }
        else {
            inputFile = args[0];
        }

        Task[] tasks = FileReader.read(inputFile);

        Planner planner = new Planner(tasks);
        boolean hasCycle = planner.hasCycles();
        if (!hasCycle) {
            System.out.println("No cycles found, project is executable\n");
            planner.setDepTasks();
            planner.setStartingTime();
            Printer.print(tasks);
        }
        else {
            System.out.println("Cycle was found, project is not executable");
            planner.setDepTasks();
            Printer.printNE(planner.cycleReturn());
        }
    }
}
