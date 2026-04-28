package Java_Workpath.projekt;
import java.io.FileWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
public class TaskManager implements Saveable {
    private final HashMap<Integer,Task> tasks = new HashMap<>();
    public void addTask(Task task) {
        tasks.put(task.getId(),task);
    }
    public void showAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available yet!");
        }else {
        for (Task task : tasks.values()) {
            task.showTask();
        }
        }
    }
    public void editDescription(String newDescription, int taskNumber) {
        if(isValidTaskNumber(taskNumber)){
            tasks.get(taskNumber ).changeDescription(newDescription);
        }
    }
    public void editTitle(String newTitle, int taskNumber) {
        if(isValidTaskNumber(taskNumber)){
            tasks.get(taskNumber ).changeTitle(newTitle);
        }
    }
    public void markTaskAsDone(int taskNumber) {
        if(isValidTaskNumber(taskNumber)){
            tasks.get(taskNumber ).markAsDone();
            System.out.println("Successful!");
        }
    }
    public void markTaskAsUndone(int taskNumber) {
        if (isValidTaskNumber(taskNumber)) {
            tasks.get(taskNumber ).markAsUndone();
            System.out.println("Task updated successfully!");
        }
    }
    public void deleteTask(int taskNumber){
        if(isValidTaskNumber(taskNumber)){
            tasks.remove(taskNumber);
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
                    tasks.put(task.getId(),task);
                } else if (parts.length == 4) {
                    String title = parts[0];
                    String description = parts[1];
                    boolean done = Boolean.parseBoolean(parts[2]);
                    String date = parts[3];
                    DeadlineTask deadlineTask = new DeadlineTask(title, description, done,date);
                    tasks.put(deadlineTask.getId(),deadlineTask);
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
            for (Task task : tasks.values()) {
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
        if (!tasks.containsKey(taskNumber)) {
            System.out.println("Invalid ID!");
            return false; }
        return true;
    }
    public void searchTasks(String keyword){
        for (Task task : tasks.values()){
            if (task.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                task.showTask();
            }
        }
    }
    public void filterTasks(boolean status){
        for (Task task : tasks.values()){
            if (status==task.isDone()) {
                task.showTask();
            }
        }
    }
    public void sortTasksAlphabetically(){
        ArrayList<Task> sortedList = new ArrayList<>(tasks.values());
            sortedList.sort(Comparator.comparing(Task::getTitle));
            for (Task task : sortedList) {
                task.showTask();
            }
    }
}
