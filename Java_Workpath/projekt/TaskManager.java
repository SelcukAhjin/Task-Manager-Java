package Java_Workpath.projekt;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.util.Comparator;
import java.util.Scanner;
public class TaskManager implements Saveable {
    private final ArrayList<Task> tasks = new ArrayList<>();
    public void addTask(Task task) {
        tasks.add(task);
    }
    public void showAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available yet!");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ".");
            tasks.get(i).showTask();
        }
    }

    public void editDescription(String newDescription, int taskNumber) {
        if(isValidTaskNumber(taskNumber)){
            tasks.get(taskNumber - 1).changeDescription(newDescription);
        }
    }

    public void editTitle(String newTitle, int taskNumber) {
        if(isValidTaskNumber(taskNumber)){
            tasks.get(taskNumber - 1).changeTitle(newTitle);
        }
    }


    public void markTaskAsDone(int taskNumber) {
        if(isValidTaskNumber(taskNumber)){
            tasks.get(taskNumber - 1).markAsDone();
            System.out.println("Successful!");
        }
    }
    public void markTaskAsUndone(int taskNumber) {
        if (isValidTaskNumber(taskNumber)) {
            tasks.get(taskNumber - 1).markAsUndone();
            System.out.println("Task updated successfully!");
        }
    }
    public void deleteTask(int taskNumber){
        if(isValidTaskNumber(taskNumber)){
            tasks.remove(taskNumber-1);
            System.out.println("Task deleted successfully!");
        }
    }
    public int getTaskCount() {
        return tasks.size();
    }
    public void load(String fileName) {
        tasks.clear();

        try {
            File file = new File(fileName);

            if (!file.exists()) {
                return;
            }
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String title = parts[0];
                    String description = parts[1];
                    boolean done = Boolean.parseBoolean(parts[2]);
                    Task task = new Task(title, description, done);
                    tasks.add(task);
                } else if (parts.length == 4) {
                    String title = parts[0];
                    String description = parts[1];
                    boolean done = Boolean.parseBoolean(parts[2]);
                    String date = parts[3];
                    DeadlineTask deadlineTask = new DeadlineTask(title, description, done,date);
                    tasks.add(deadlineTask);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error while loading tasks!");
        }
    }
    public void save(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.write("\n");
            }
            writer.close();
            System.out.println("Tasks saved successfully!");
        } catch (Exception e) {
            System.out.println("Error while saving tasks!");
        }
    }
    private boolean isValidTaskNumber(int taskNumber) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available yet!");
            return false;
        }
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            System.out.println("Invalid input!");
            return false;
        }
        return true;
    }
    public void searchTasks(String keyword){
        for (Task task : tasks){
            if (task.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                task.showTask();
            }
        }
    }
    public void filterTasks(boolean status){
        for (Task task : tasks){
            if (status==task.isDone()) {
                task.showTask();
            }
        }
    }
    public void sortTasksAlphabetically(){
            tasks.sort(Comparator.comparing(Task::getTitle));
    }
}
