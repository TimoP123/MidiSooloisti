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
    
    @Test
    public void getNotesReturnsOnlyNotesThatBelongToTheScale() {
        int[] notes = {0, 2, 4, 5, 7, 9, 11};
        Scale majorScale = new Scale(42, 88, notes);
        
        boolean ok = true;
        
        for(int note : majorScale.getNotes()) {
            boolean flag = false;
            for(int i = 0; i < notes.length; i++) {

                if(note % 12 == notes[i]) {
                    flag = true;
                }
            }
            
            if (!flag) {
                System.out.println("väärä nuotti: " + note);
                ok = false;
                break;
            }
        }
        
        assertTrue(ok);
    }

}
