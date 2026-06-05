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
    @Test
    void getKategorieSchool_returntrue(){
        Task t = new Task("de","",false);
        t.setKat(Task.Kategorie.SCHOOL);
        assertEquals(Task.Kategorie.SCHOOL,t.getKat());
    }
    @Test
    void getKategorieWork_returntrue() {
        Task t = new Task("de", "", false);
        t.setKat(Task.Kategorie.WORK);
        assertEquals(Task.Kategorie.WORK, t.getKat());
    }
    @Test
    void getKategoriePersonal_returntrue() {
        Task t = new Task("de", "", false);
        t.setKat(Task.Kategorie.PERSONAL);
        assertEquals(Task.Kategorie.PERSONAL,t.getKat());
    }
}
