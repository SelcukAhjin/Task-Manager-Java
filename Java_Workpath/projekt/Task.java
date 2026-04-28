package Java_Workpath.projekt;

public class Task {
    private String title;
    private String description;
    private boolean done;
    private static int idCounter = 0;
    private int id;
    public Task(String title,String description,boolean done){
        idCounter++;
        this.title = title;
        this.description = description;
        this.done = done;
        this.id = idCounter;
    }

    public int getId() {
        return id;
    }

    public void showTask(){
        System.out.println("Your task: "+title);
        System.out.println("Description:  "+description);
        System.out.println("Status: "+ (done ? "done" : "undone"));
        System.out.println("Id: "+id+".");
    }
    public void markAsDone(){
        done = true;
    }
    public void changeTitle(String newTitle){
        title = newTitle;
    }
    public void changeDescription(String newDescription){
        description = newDescription;
    }
    public String toFileString(){
        return title + ";" + description + ";" + done;
    }
    public void markAsUndone() {
        done = false;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public boolean isDone() {
        return done;
    }
}
