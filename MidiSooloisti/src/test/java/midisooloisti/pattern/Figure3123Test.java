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

public class Figure3123Test {

    Scale scale;
    int[] notes;
    Figure3123 figure;
    int minLimit;
    int maxLimit;

    @Before
    public void setUp() {
        Random random = new Random();
        this.notes = new int[]{0, 2, 3, 5, 7, 8, 11};
        this.minLimit = 45;
        this.maxLimit = 93;
        this.scale = new Scale(this.minLimit, this.maxLimit, notes);
        this.figure = new Figure3123(random);
    }

    @Test
    public void getNotesReturnsOnlyNotesInTheScale() {
        boolean ok = true;
        boolean flag = false;
        for (MidiNote note : figure.getNotes(scale, 75)) {
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
    public void firstNoteOfNotePatternIsChordNote() {
        boolean ok = false;
        int pitch = figure.getNotes(scale, 77).get(0).getPitch();

        for (int i = 0; i <= 4; i = i + 2) {
            if (pitch % 12 == notes[i]) {
                ok = true;
            }
        }

        assertTrue(ok);
    }

    @Test
    public void getNotesReturnsOnlyNotesInScaleArea() {
        boolean ok = true;
        for (MidiNote note : figure.getNotes(scale, 65)) {
            if (note.getPitch() < this.minLimit || note.getPitch() > this.maxLimit) {
                ok = false;
            }
        }
        assertTrue(ok);
    }

    @Test
    public void thePatternIsCorrect() {
        ArrayList<MidiNote> notes = figure.getNotes(scale, 70);
        assertEquals(72, notes.get(0).getPitch());
        assertEquals(68, notes.get(1).getPitch());
        assertEquals(71, notes.get(2).getPitch());
        assertEquals(72, notes.get(3).getPitch());
        // Two choices, depends on the direction.
        assertTrue((notes.get(12).getPitch() == 67) || (notes.get(12).getPitch() == 77));
        assertTrue((notes.get(13).getPitch() == 63) || (notes.get(13).getPitch() == 74));
        assertTrue((notes.get(14).getPitch() == 67) || (notes.get(14).getPitch() == 77));
        assertTrue((notes.get(15).getPitch() == 65) || (notes.get(15).getPitch() == 75));
    }

    @Test
    public void getNotesReturnsSixteenNotes() {
        assertEquals(16, figure.getNotes(scale, 77).size());
    }
}
