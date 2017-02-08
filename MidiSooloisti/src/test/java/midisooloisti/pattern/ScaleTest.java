package midisooloisti.pattern;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScaleTest {

    Scale scale;

    @Before
    public void setUp() {
        int[] notes = {0, 2, 3, 5, 7, 8, 11};
        this.scale = new Scale(40, 90, notes);
    }

    @Test
    public void rightAmountOfNotesAvailable() {
        assertEquals(29, scale.getNotes().size());
    }

    @Test
    public void rightAmountOfChordNotesAvailable() {
        assertEquals(12, scale.getChordNotes().size());
    }

}
