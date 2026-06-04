import org.junit.jupiter.api.Test;
import projekt.DeadlineTask;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
public class DeadlineTaskTest {
    @Test
    public void isOverdue_dateInPast_returnsTrue() {
        DeadlineTask deadt = new DeadlineTask("de","",false, LocalDate.now().minusDays(5));
        assertTrue(deadt.isOverdue());
    }
}
