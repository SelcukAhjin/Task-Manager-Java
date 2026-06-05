import org.junit.jupiter.api.Test;
import projekt.Task;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    void setPrioLow_returntrue(){
        Task t = new Task("de","",false);
        t.setPrio(Task.Priority.LOW);
        assertEquals(Task.Priority.LOW,t.getPrio());
    }
    @Test
    void setPrioHigh_returntrue(){
        Task t = new Task("de","",false);
        t.setPrio(Task.Priority.HIGH);
        assertEquals(Task.Priority.HIGH,t.getPrio());
    }
    @Test
    void getPrioMedium_returntrue(){
        Task t = new Task("de","",false);
        t.setPrio(Task.Priority.MEDIUM);
        assertEquals(Task.Priority.MEDIUM,t.getPrio());
    }
}
