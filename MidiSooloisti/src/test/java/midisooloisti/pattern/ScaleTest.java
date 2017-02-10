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
                ok = false;
                break;
            }
        }
        
        assertTrue(ok);
    }
    
    @Test
    public void getChordNotesReturnsOnlyNotesThatBelongToTheChord() {
        int[] notes = {0, 2, 4, 5, 7, 9, 11};
        Scale majorScale = new Scale(42, 88, notes);
        
        boolean ok = true;
        
        for(int note : majorScale.getChordNotes()) {
            boolean flag = false;
            for(int i = 0; i <= 4; i = i + 2) { // We'll use only 1. 3. and 5.

                if(note % 12 == notes[i]) {
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
    public void closestChordNoteWorks() {
        assertEquals(60, scale.closestChordNote(61));
    }
    
    @Test
    public void findIndexOfPitchWorks() {
        assertEquals(22, scale.findIndexOfPitch(79));
    }
    
    @Test
    public void findIndexOfClosestChordNoteWorks() {
        assertEquals(8, scale.findIndexOfClosestChordNote(57));
    }

    @Test
    public void findIndexOfPitchInChordNotesWorks() {
        assertEquals(6, scale.findIndexOfPitchInChordNotes(67));
    }
    
    @Test
    public void findIndexOfClosestChordNoteInChordNotesWorks() {
        assertEquals(7, scale.findIndexOfClosestChordNoteInChordNotes(71));
    }

}
