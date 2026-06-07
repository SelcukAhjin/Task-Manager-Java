package projekt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private TaskDAO taskDAO = new TaskDAO();

    @GetMapping
    public List<Task> getAllTasks() {
        return taskDAO.showAllTasks(1);
    }
}
