import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import projekt.DeadlineTask;

import java.time.LocalDate;

public class DeadlineTaskTest {
    @Test
    public void isOverdue_dateInPast_returnsTrue() {
        DeadlineTask deadt = new DeadlineTask("de","",false, LocalDate.now().minusDays(5));
        Assertions.assertTrue(deadt.isOverdue());
    }
    @Test
    public void isOverdue_dateInFuture_returnsFalse() {
        DeadlineTask dt = new DeadlineTask("de","",false, LocalDate.now().plusDays(5));
        Assertions.assertFalse(dt.isOverdue());
    }
    @Test
    public void isOverdue_dateInPast_TaskDone_returnsFalse() {
        DeadlineTask dt = new DeadlineTask("de","",true, LocalDate.now().minusDays(5));
        Assertions.assertFalse(dt.isOverdue());
    }
    @Test
    public void isOverdue_dateInFuture_TaskDone_returnsFalse() {
        DeadlineTask dt = new DeadlineTask("de","",true, LocalDate.now().plusDays(5));
        Assertions.assertFalse(dt.isOverdue());
    }
}
