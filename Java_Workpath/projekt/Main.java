package Java_Workpath.projekt;
import java.util.Scanner;
public class Main {
    static void main(String[] args) {

        UserManager lmanager = new UserManager();
        lmanager.load("users.txt");
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        int choiceHub = 0;
        int choice = 0;
        User currentUser = null;

        while (running) {
            //LoggedOff Starting Point
            if (currentUser == null) {//methode ShowHubMenu Line#92
                showHubMenu();
                choiceHub = readValidInt(sc,"What would you like to do?",1,3);
                switch (choiceHub) {
                    case 1 -> {
                        //Methode to Login Line#114
                        currentUser = login(sc, lmanager);

                        if (currentUser != null) {
                            currentUser.getTaskManager().load(currentUser.getUsername()+"_tasks.txt");

                        }
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
            }else{
                //Methode ShowLoggedInMenu Line#99
                showLoggedMenu(currentUser);
                choice = readValidInt(sc,"What would you like to do ?",1,8);
                if ((choice >= 2 && choice <= 7)&&currentUser.getTaskManager().getTaskCount() == 0){
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
                        switch (readValidInt(sc,"What would you like to do ?",1,3)){
                            case 1 -> {
                                currentUser.getTaskManager().searchTasks(readNonEmptyString(sc,"Name of the Task: "));
                                System.out.println("Press Enter to continue...");
                                sc.nextLine();
                            }
                            case 2 -> {
                                currentUser.getTaskManager().filterTasks(true);
                                System.out.println("Press Enter to continue...");
                                sc.nextLine();
                            }
                            case 3 -> {
                                currentUser.getTaskManager().filterTasks(false);
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
                        //logout
                        currentUser.getTaskManager().save(currentUser.getUsername()+"_tasks.txt");
                        currentUser = null;
                        break;
                    }
                    case 9: {
                        //exit
                        currentUser.getTaskManager().save(currentUser.getUsername()+"_tasks.txt");
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
        System.out.println("Welcome " + currentUser.getUsername() + " to your Task Manager!");
        System.out.println("What would you like to do?");
        System.out.println("1. Add Task");
        if (currentUser.getTaskManager().getTaskCount() != 0) {
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
        String username = readNonEmptyString(sc,"Enter your Username: ");
        if (lmanager.usernameExists(username)) {
            System.out.println("Username already in use!");
            return;
        }
        String email = readNonEmptyString(sc,"Enter your Email: ");
        if (!lmanager.isValidEmail(email)){
            System.out.println("Invalid input");
            System.out.println("Press Enter to continue...");
            sc.nextLine();
            return;
            }
            if (lmanager.emailExists(email)) {
                System.out.println("Email already in use!");
                return;
            }
            String password = readNonEmptyString(sc,"Enter your Password: ");
            if (!lmanager.isValidPassword(password)){
                System.out.println("Invalid input");
                System.out.println("Press Enter to continue...");
                sc.nextLine();
                return;
            }
            User user = new User(username, email, password);
            lmanager.addUser(user);
            lmanager.save("users.txt");
            System.out.println("Successfully Registered!");
            System.out.println("Press Enter to continue...");
            sc.nextLine();
    }
    public static void addTask(Scanner sc, User currentUser) {
        System.out.println("1. Normal Task");
        System.out.println("2. Deadline Task");
        int taskType = readValidInt(sc, "What type of task do you want to create?", 1, 2);
        String title = readNonEmptyString(sc,"Enter task title: ");
        String description = readNonEmptyString(sc,"Enter task description: ");
        boolean done = false;
        Task newTask;
        if (taskType == 1) {
            //Normal Task
            newTask = new Task(title, description, done);
        } else {
            // Deadline Task
            String date = readNonEmptyString(sc, "Enter the deadline date (e.g., 31.12.2026): ");
            newTask = new DeadlineTask(title, description, done, date);
        }
        currentUser.getTaskManager().addTask(newTask);
        currentUser.getTaskManager().save(currentUser.getUsername() + "_tasks.txt");
    }
    public static void showAllTasks(User currentUser, Scanner sc) {
        currentUser.getTaskManager().showAllTasks();
        System.out.println("Press Enter to continue...");
        sc.nextLine();
    }
    public static void taskDone(User currentUser, Scanner sc) {
        currentUser.getTaskManager().showAllTasks();
        int taskChoice = readValidInt(sc,"Select a task number: ",1,currentUser.getTaskManager().getTaskCount());
            currentUser.getTaskManager().markTaskAsDone(taskChoice);
            System.out.println("Press Enter to continue...");
            sc.nextLine();
    }
    public static void taskUndone(Scanner sc,User currentUser){
        currentUser.getTaskManager().showAllTasks();
        int taskChoice = readValidInt(sc,"Select a task number: ",1,currentUser.getTaskManager().getTaskCount());
            currentUser.getTaskManager().markTaskAsUndone(taskChoice);
            System.out.println("Press Enter to continue...");
            sc.nextLine();
    }
    public static void deleteTask(Scanner sc,User currentUser){
        currentUser.getTaskManager().showAllTasks();
        int taskChoice = readValidInt(sc,"Select a task number: ",1,currentUser.getTaskManager().getTaskCount());
            currentUser.getTaskManager().deleteTask(taskChoice);
            System.out.println("Press Enter to continue...");
            sc.nextLine();
    }
    public static void editTask(Scanner sc,User currentUser){
        TaskManager manager = currentUser.getTaskManager();
        int taskChoice = 0;
        System.out.println("1. Editing task title");
        System.out.println("2. Editing task description");
        System.out.println("3. Editing task title and description");
        int editChoice = readValidInt(sc,"Select what to edit: ",1,3);
        manager.showAllTasks();
        taskChoice = readValidInt(sc,"Select a task number: ",1,currentUser.getTaskManager().getTaskCount());

        if (editChoice == 1) {
            String newTitle = readNonEmptyString(sc,"The new title? : ");
            manager.editTitle(newTitle, taskChoice);
        }
        else if (editChoice == 2) {
            String newDescription = readNonEmptyString(sc,"The new description? : ");
            manager.editDescription(newDescription, taskChoice);
        }
        else if (editChoice == 3) {
            String newTitle = readNonEmptyString(sc,"The new title? : ");
            String newDescription = readNonEmptyString(sc,"The new description? : ");
            manager.editTitle(newTitle, taskChoice);
            manager.editDescription(newDescription,taskChoice);
        }

        System.out.println("Task edited successfully!");
        System.out.println("Press Enter to continue...");
        sc.nextLine();
    }
    public static String readNonEmptyString(Scanner sc,String message){
        String input = "";
        do {
            System.out.println(message);
            input = sc.nextLine();
        } while(input.isBlank());
        return input;
    }
    public static int readValidInt(Scanner sc, String message, int min, int max) {
        while (true) {
            System.out.println(message);
            try {
                int number = Integer.parseInt(sc.nextLine());
                if ( number >= min&& number <= max ) {
                    return number;
                } else {
                    System.out.println("Input a number between " + min + " and " + max + " !");
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
    }
}
