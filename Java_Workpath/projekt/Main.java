package Java_Workpath.projekt;
import Java_Workpath.projekt.dao.UserDAO;

import java.time.LocalDate;
import java.util.Scanner;
public class Main {
    static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        UserManager lmanager = new UserManager(userDAO);
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        int choiceHub = 0;
        int choice = 0;
        User currentUser = null;

        while (running) {
            //LoggedOff Starting Point
            if (currentUser == null) {//methode ShowHubMenu Line#92
                showHubMenu();
                choiceHub = readValidInt(sc, "What would you like to do?", 1, 3);
                switch (choiceHub) {
                    case 1 -> {
                        //Methode to Login Line#114
                        currentUser = login(sc, lmanager);
                    }
                    case 2 ->
                        //Methode to Register Line#128
                            register(sc, lmanager);
                    case 3 -> {
                        //Exit
                        System.out.println("See you Soon!");
                        running = false;

                    }
                    default -> {
                        System.out.println("Invalid input");
                        System.out.println("Press Enter to continue...");
                        sc.nextLine();
                    }
                }
                //This else switchpoint to loggedIn
            } else {
                if (currentUser.getTaskManager().getTaskCount(currentUser.getId()) != 0){
                currentUser.getTaskManager().showDashboard(currentUser.getId());
                System.out.println("Press Enter to continue...");
                sc.nextLine();
                }
                //Methode ShowLoggedInMenu Line#99
                showLoggedMenu(currentUser);
                choice = readValidInt(sc, "What would you like to do ?", 1, 10);
                if ((choice >= 2 && choice <= 7) && currentUser.getTaskManager().getTaskCount(currentUser.getId()) == 0) {
                    System.out.println("No task yet!");
                    continue;
                }
                switch (choice) {
                    case 1: {
                        //create task Line#149
                        addTask(sc, currentUser);
                        break;
                    }
                    case 2: {
                        //Show all tasks Line#157
                        showAllTasks(currentUser, sc);
                        break;
                    }
                    case 3: {
                        System.out.println("1 for searching");
                        System.out.println("2 to show done tasks");
                        System.out.println("3 to show undone tasks");
                        System.out.println("4 to show all tasks A-Z");
                        System.out.println("5 to show all Deadline tasks A-Z");
                        switch (readValidInt(sc, "What would you like to do ?", 1, 5)) {
                            case 1 -> {
                                currentUser.getTaskManager().searchTasks(readNonEmptyString(sc, "Name of the Task: "),currentUser.getId());
                                System.out.println("Press Enter to continue...");
                                sc.nextLine();
                            }
                            case 2 -> {
                                currentUser.getTaskManager().filterTasks(true,currentUser.getId());
                                System.out.println("Press Enter to continue...");
                                sc.nextLine();
                            }
                            case 3 -> {
                                currentUser.getTaskManager().filterTasks(false,currentUser.getId());
                                System.out.println("Press Enter to continue...");
                                sc.nextLine();
                            }
                            case 4 -> {
                                currentUser.getTaskManager().sortTasksAlphabetically(currentUser.getId());
                                System.out.println("Press Enter to continue...");
                                sc.nextLine();
                            }
                            case 5 -> {
                                currentUser.getTaskManager().showDeadlinesSorted(currentUser.getId());
                                System.out.println("Press Enter to continue...");
                                sc.nextLine();
                            }
                        }

                        break;
                    }
                    case 4: {
                        // mark tasks as Done Line#162
                        taskDone(currentUser, sc);
                        break;
                    }
                    case 5: {
                        //mark tasks as Undone Line#174
                        taskUndone(sc, currentUser);
                        break;
                    }
                    case 6: {
                        //delete Task Line#186
                        deleteTask(sc, currentUser);
                        break;
                    }
                    case 7: {
                        //Edit Task Line#198
                        editTask(sc, currentUser);
                        break;
                    }
                    case 8: {
                        //change password
                        System.out.print("""
                                1. Change password\s
                                2. Delete User
                                """);
                        int subUserInterface = readValidInt(sc, "Your choice: ", 1, 2);
                        switch (subUserInterface) {
                            case 1: {

                                if (currentUser.checkPassword(readNonEmptyString(sc, "Current Password"))) {
                                    String newPassword = readNonEmptyString(sc, "New Password");
                                    if (lmanager.isValidPassword(newPassword)) {
                                        lmanager.updatePassword(currentUser.getUsername(), newPassword);
                                        System.out.println("Password changed successfully!");
                                    }

                                } else {
                                    System.out.println("Invalid Password!");
                                    break;
                                }
                                break;
                            }

                            case 2: {
                                if (currentUser.checkPassword(readNonEmptyString(sc, "Current Password"))) {
                                    lmanager.deleteUser(currentUser);
                                    currentUser = null;
                                    System.out.println("Account DELETED!");
                                    break;
                                }
                                break;
                            }
                        }
                    }
                    case 9: {
                        //logout
                        currentUser = null;
                        break;
                    }
                    case 10: {
                        //exit
                        running = false;
                        break;
                    }
                    default: {
                        System.out.println("Invalid input");
                        System.out.println("Press Enter to continue...");
                        sc.nextLine();
                    }
                }
            }
        }
    }

    public static void showHubMenu() {
        System.out.println("Welcome to the Hub!");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
    }

    public static void showLoggedMenu(User currentUser) {
        System.out.println("1. Add Task");
        if (currentUser.getTaskManager().getTaskCount(currentUser.getId()) != 0) {
            System.out.println("2. Show Tasks");
            System.out.println("3. Search tasks");
            System.out.println("4. Mark Task as Done");
            System.out.println("5. Mark Task as Undone");
            System.out.println("6. Delete Task");
            System.out.println("7. Edit Task");
        }

        System.out.println("8. Logout");
        System.out.println("9. Exit");
    }

    public static User login(Scanner sc, UserManager lmanager) {
        System.out.println("Enter your Username or Email: ");
        String loginInput = sc.nextLine();
        System.out.println("Enter your Password: ");
        String password = sc.nextLine();
        User user = lmanager.login(loginInput, password);
        if (user == null) {
            System.out.println("Wrong Username, Email or Password");
            return null;
        }
        System.out.println("Welcome " + user.getUsername() + "!");
        return user;
    }

    public static void register(Scanner sc, UserManager lmanager) {
        String username = readNonEmptyString(sc, "Enter your Username: ");
        if (lmanager.usernameExists(username)) {
            System.out.println("Username already in use!");
            return;
        }
        String email = readNonEmptyString(sc, "Enter your Email: ");
        if (!lmanager.isValidEmail(email)) {
            System.out.println("Invalid input");
            System.out.println("Press Enter to continue...");
            sc.nextLine();
            return;
        }
        if (lmanager.emailExists(email)) {
            System.out.println("Email already in use!");
            return;
        }
        String password = readNonEmptyString(sc, "Enter your Password: ");
        if (!lmanager.isValidPassword(password)) {
            System.out.println("Invalid input");
            System.out.println("Press Enter to continue...");
            sc.nextLine();
            return;
        }

        lmanager.addUser(username,email,password);
        System.out.println("Successfully Registered!");
        System.out.println("Press Enter to continue...");
        sc.nextLine();
    }

    public static void addTask(Scanner sc, User currentUser) {
        System.out.println("1. Normal Task");
        System.out.println("2. Deadline Task");
        int taskType = readValidInt(sc, "What type of task do you want to create?", 1, 2);
        String title = readNonEmptyString(sc, "Enter task title: ");
        String description = readNonEmptyString(sc, "Enter task description: ");
        boolean done = false;
        Task newTask;
        if (taskType == 1) {
            //Normal Task
            newTask = new Task(title, description, done);
        } else {
            // Deadline Task
            LocalDate date = readValidDate(sc,"Enter the deadline date (e.g., 2026-12-30): ");
            newTask = new DeadlineTask(title, description, done, date);
        }
        currentUser.getTaskManager().addTask(newTask, currentUser.getId());
    }

    public static void showAllTasks(User currentUser, Scanner sc) {
        currentUser.getTaskManager().showAllTasks(currentUser.getId());
        System.out.println("Press Enter to continue...");
        sc.nextLine();
    }

    public static void taskDone(User currentUser, Scanner sc) {
        currentUser.getTaskManager().showAllTasks(currentUser.getId());
        int taskChoice = readValidInt(sc, "Select a task number: ", 1, currentUser.getTaskManager().getTaskCount(currentUser.getId()));
        currentUser.getTaskManager().markTaskAsDone(taskChoice);
        System.out.println("Press Enter to continue...");
        sc.nextLine();
    }

    public static void taskUndone(Scanner sc, User currentUser) {
        currentUser.getTaskManager().showAllTasks(currentUser.getId());
        int taskChoice = readValidInt(sc, "Select a task number: ", 1, currentUser.getTaskManager().getTaskCount(currentUser.getId()));
        currentUser.getTaskManager().markTaskAsUndone(taskChoice);
        System.out.println("Press Enter to continue...");
        sc.nextLine();
    }

    public static void deleteTask(Scanner sc, User currentUser) {
        currentUser.getTaskManager().showAllTasks(currentUser.getId());
        int taskChoice = readValidInt(sc, "Select a task number: ", 1, currentUser.getTaskManager().getTaskCount(currentUser.getId()));
        currentUser.getTaskManager().deleteTask(taskChoice);
        System.out.println("Press Enter to continue...");
        sc.nextLine();
    }

    public static void editTask(Scanner sc, User currentUser) {
        TaskManager manager = currentUser.getTaskManager();
        int taskChoice = 0;
        System.out.println("1. Editing task title");
        System.out.println("2. Editing task description");
        System.out.println("3. Editing task title and description");
        int editChoice = readValidInt(sc, "Select what to edit: ", 1, 3);
        manager.showAllTasks(currentUser.getId());
        taskChoice = readValidInt(sc, "Select a task number: ", 1, currentUser.getTaskManager().getTaskCount(currentUser.getId()));

        if (editChoice == 1) {
            String newTitle = readNonEmptyString(sc, "The new title? : ");
            manager.editTitle(newTitle, taskChoice);
        } else if (editChoice == 2) {
            String newDescription = readNonEmptyString(sc, "The new description? : ");
            manager.editDescription(newDescription, taskChoice);
        } else if (editChoice == 3) {
            String newTitle = readNonEmptyString(sc, "The new title? : ");
            String newDescription = readNonEmptyString(sc, "The new description? : ");
            manager.editTitle(newTitle, taskChoice);
            manager.editDescription(newDescription, taskChoice);
        }

        System.out.println("Task edited successfully!");
        System.out.println("Press Enter to continue...");
        sc.nextLine();
    }

    public static String readNonEmptyString(Scanner sc, String message) {
        String input = "";
        do {
            System.out.println(message);
            input = sc.nextLine();
        } while (input.isBlank());
        return input;
    }

    public static int readValidInt(Scanner sc, String message, int min, int max) {
        while (true) {
            System.out.println(message);
            try {
                int number = Integer.parseInt(sc.nextLine());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println("Input a number between " + min + " and " + max + " !");
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
    }

    public static LocalDate readValidDate(Scanner sc, String message) {
        while (true) {
            System.out.println(message);
            try {
                return LocalDate.parse(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Input Date Format: yyyy-mm-dd");
            }
        }
    }

}
