package Java_Workpath.projekt;

import Java_Workpath.projekt.DAO.taskdao;

@SuppressWarnings({"CallToPrintStackTrace", "TryStatementWithMultipleResources", "DataFlowIssue"})
public class TaskManager {
    taskdao taskDAO = new taskdao();


    public void addTask(Task task, int userID) {
        taskDAO.addTask(task, userID);
    }

    public void showAllTasks(int userID) {
        taskDAO.showAllTasks(userID);
    }

    public void editDescription(String newDescription, int taskChoice) {
        taskDAO.editDescription(newDescription, taskChoice);
    }

    public void editTitle(String newTitle, int taskChoice) {
        taskDAO.editTitle(newTitle, taskChoice);
    }

    public void markTaskAsDone(int taskChoice) {
        taskDAO.markTaskAsDone(taskChoice);
    }

    public void markTaskAsUndone(int taskChoice) {
        taskDAO.markTaskAsUndone(taskChoice);
    }

    public void deleteTask(int taskChoice) {
        taskDAO.deleteTask(taskChoice);
    }

    public int getTaskCount(int id) {
        return taskDAO.getTaskCount(id);
    }

    public void searchTasks(String keyword, int userID) {
        taskDAO.searchTasks(keyword, userID);
    }

    public void filterTasks(boolean status, int userID) {
        taskDAO.filterTasks(status, userID);
    }

    public void sortTasksAlphabetically(int userID) {
        taskDAO.sortTasksAlphabetically(userID);
    }

    public void showDeadlinesSorted(int userID) {
        taskDAO.showDeadlinesSorted(userID);
    }

    public void showDashboard(int userID) {
        taskDAO.showDashboard(userID);
    }
}
