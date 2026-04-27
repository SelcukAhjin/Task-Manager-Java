package Java_Workpath.projekt;

public class DeadlineTask extends Task {
    private String date = "";
    public DeadlineTask (String title,String description,boolean done,String date){
        super(title,description,done);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
