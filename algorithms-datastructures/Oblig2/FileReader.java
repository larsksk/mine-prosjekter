import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class FileReader {

    static Task[] read(String fileName) {
        Task[] tasks;
        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            int numTasks = Integer.parseInt(line);
            tasks = new Task[numTasks];

            for (int i = 0; i<numTasks; i++) {
                tasks[i] = new Task();
            }

            scanner.nextLine();

            for (int i = 0; i<numTasks; i++) {
                line = scanner.nextLine();

                String[] data = line.split("\\s+");

                tasks[i].id = Integer.parseInt(data[0]);
                tasks[i].name = data[1];
                tasks[i].time = Integer.parseInt(data[2]);
                tasks[i].staff = Integer.parseInt(data[3]);

                if (data.length > 5) {
                    tasks[i].outEdges = new LinkedList<>();
                    for (int j = 4; j<data.length-1;j++) {
                        tasks[i].outEdges.add(tasks[Integer.parseInt(data[j])-1]);
                    }
                }
            }
            return tasks;

        }
        catch (FileNotFoundException e) {
            System.out.println("Kunne ikke lese fra fil.");
            System.exit(1);
        }
        return null;
    }
}
