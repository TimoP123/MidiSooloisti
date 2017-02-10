
package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Random;
import midisooloisti.player.MidiNote;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BachTest {
    
    Scale scale;
    int[] notes;
    Bach bach;
    int minLimit;
    int maxLimit;

    @Before
    public void setUp() {
        Random random = new Random();
        this.notes = new int[]{0, 2, 3, 5, 7, 8, 11};
        this.minLimit = 45;
        this.maxLimit = 93;
        this.scale = new Scale(this.minLimit, this.maxLimit, notes);
        this.bach = new Bach(random);
    }
    
    @Test
    public void getNotesReturnsOnlyNotesInTheScale() {
        boolean ok = true;
        boolean flag = false;
        for (MidiNote note : bach.getNotes(scale, 75)) {
            for (int i = 0; i < notes.length; i++) {
                if (note.getPitch() % 12 == notes[i]) {
                    flag = true;
                }
            }
            if (!flag) {
                ok = false;
                break;
            }
        }
        assertTrue(ok);
    }
    
    @Test
    public void thePatternShapeIsCorrect() {
        ArrayList<MidiNote> notes = bach.getNotes(scale, 77);
        assertEquals(79, notes.get(0).getPitch());
        assertEquals(75, notes.get(1).getPitch());
        assertEquals(74, notes.get(2).getPitch());
        assertEquals(75, notes.get(3).getPitch());
        assertEquals(72, notes.get(4).getPitch());
        assertEquals(75, notes.get(5).getPitch());
        assertEquals(74, notes.get(6).getPitch());
        assertEquals(75, notes.get(7).getPitch());
    }
    
    @Test
    public void getNotesReturnsSixteenNotes() {
        assertEquals(16, bach.getNotes(scale, 77).size());
    }

}
