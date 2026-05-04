package Java_Workpath.projekt;

import java.time.LocalDate;

public class DeadlineTask extends Task {
    private final LocalDate date;

    public DeadlineTask(String title, String description, boolean done, LocalDate date) {
        super(title, description, done);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
    @Override
    public void showTask() {
        super.showTask();
        System.out.println("Date: " + date);
        if (date.isBefore(LocalDate.now()) && !super.isDone()) {
            System.out.println("*** [ÜBERFÄLLIG] ***");
        }
    }
}

