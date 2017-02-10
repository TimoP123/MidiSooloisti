
package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import midisooloisti.player.MidiNote;

public class LinearTest {

    Scale scale;
    int[] notes;
    Linear linear;
    int minLimit;
    int maxLimit;

    @Before
    public void setUp() {
        Random random = new Random();
        this.notes = new int[]{0, 2, 3, 5, 7, 8, 11};
        this.minLimit = 45;
        this.maxLimit = 93;
        this.scale = new Scale(this.minLimit, this.maxLimit, notes);
        this.linear = new Linear(random);
    }

    @Test
    public void getNotesReturnsOnlyNotesInTheScale() {
        boolean ok = true;
        boolean flag = false;
        for (MidiNote note : linear.getNotes(scale, 75)) {
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
    public void getNotesReturnsOnlyNotesInScaleArea() {
        boolean ok = true;
        for (MidiNote note: linear.getNotes(scale, 24)) {
            if (note.getPitch() < this.minLimit || note.getPitch() > this.maxLimit) {
                ok = false;
            }
        }
        assertTrue(ok);
    }
    
    @Test
    public void notesOnBeatsAreChordNotes() {
        boolean ok = true;
        ArrayList<MidiNote> midiNotes = linear.getNotes(scale, 110);
        for(int i = 0; i <= 11; i = i + 4) {
            boolean flag = false;
            
            for(int j = 0; j <= 4; j = j + 2) {
                if(midiNotes.get(i).getPitch() % 12 == notes[j]) {
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
}