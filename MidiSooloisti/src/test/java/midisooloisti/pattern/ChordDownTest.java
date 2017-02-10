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

public class ChordDownTest {

    Scale scale;
    int[] notes;
    ChordDown chordDown;
    int minLimit;
    int maxLimit;

    @Before
    public void setUp() {
        Random random = new Random();
        this.notes = new int[]{0, 2, 3, 5, 7, 8, 11};
        this.minLimit = 45;
        this.maxLimit = 93;
        this.scale = new Scale(this.minLimit, this.maxLimit, notes);
        this.chordDown = new ChordDown(random);
    }

    @Test
    public void getNotesReturnsOnlyChordNotes() {
        boolean ok = true;
        ArrayList<MidiNote> midiNotes = chordDown.getNotes(scale, 99);
        boolean flag = false;

        for (int i = 0; i < 15; i++) {  // Leave last (16th) note out because it's a surprise note.
            for (int j = 0; j <= 4; j = j + 2) {
                if (midiNotes.get(i).getPitch() % 12 == notes[j]) {
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
        for (MidiNote note : chordDown.getNotes(scale, 24)) {
            if (note.getPitch() < this.minLimit || note.getPitch() > this.maxLimit) {
                ok = false;
            }
        }
        assertTrue(ok);
    }

}
