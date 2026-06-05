package projekt;

public class Task {
    private String title;
    private String description;
    private boolean done;
    private int id;
    public enum Priority {LOW,MEDIUM,HIGH};
    public Priority prio =  Priority.LOW;
    public Task(String title,String description,boolean done){
        this.title = title;
        this.description = description;
        this.done = done;
    }

    public void setPrio(Priority Choice){
        this.prio = Choice;
    }

    public Priority getPrio(){
        return prio;
    }

    public void setId(int id) {
        this.id = id;
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
