package Java_Workpath.projekt;

import java.time.LocalDate;

public class DeadlineTask extends Task {
    private LocalDate date;
    public DeadlineTask (String title,String description,boolean done,LocalDate date){
        super(title,description,done);
        this.date = date;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toFileString(){
        return super.toFileString()+";"+date;

    }
    @Override
    public void showTask(){
        super.showTask();
        System.out.println("Date: "+date);
    }
}
