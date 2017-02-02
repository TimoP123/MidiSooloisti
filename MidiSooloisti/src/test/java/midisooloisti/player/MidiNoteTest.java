package midisooloisti.player;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;
import midisooloisti.player.MidiNote;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MidiNoteTest {

    public MidiNoteTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void lenghtReturnsCorrectValue() {
        MidiNote note = new MidiNote(65, 2, 1, 80);
        assertEquals(2, note.length());
    }

    @Test
    public void lengthMustBeAtLeastOne() {
        MidiNote note = new MidiNote(65, 0, 1, 80);
        assertEquals(1, note.length());
    }

    @Test
    public void lenghtWillNotBeNegative() {
        MidiNote note = new MidiNote(65, -3, 1, 80);
        assertEquals(1, note.length());
    }
    
    @Test
    public void maximumLengthIs16() {
        MidiNote note = new MidiNote(65, 20, 1, 80);
        assertEquals(16, note.length());
    }

    @Test
    public void rightChannelInMidiMessage() {
        MidiNote note = new MidiNote(65, 2, 5, 80);
        assertEquals(5, note.noteOn().getChannel());
    }

    @Test
    public void channelWillBeAtLeastZero() {
        MidiNote note = new MidiNote(65, 2, 0, 80);
        assertEquals(0, note.noteOn().getChannel());
    }

    @Test
    public void maximumValueForChannelIs15() {
        MidiNote note = new MidiNote(65, 2, 16, 80);
        assertEquals(15, note.noteOn().getChannel());
    }

    @Test
    public void velocityOkInMidiMessage() {
        MidiNote note = new MidiNote(65, 2, 1, 80);
        ShortMessage compare = note.noteOn();
        assertEquals(80, compare.getData2());
    }
    
    @Test
    public void velocityIsAlwaysAtLeastZero() {
        MidiNote note = new MidiNote(65, 2, 1, -10);
        ShortMessage compare = note.noteOn();
        assertEquals(0, compare.getData2());
    }
    
    @Test
    public void maxVelocityIs127() {
        MidiNote note = new MidiNote(65, 2, 1, 300);
        ShortMessage compare = note.noteOn();
        assertEquals(127, compare.getData2());
    }
    
    @Test
    public void pitchIsOkInMidiMessage() {
        MidiNote note = new MidiNote(72, 2, 1, 90);
        ShortMessage compare = note.noteOn();
        assertEquals(72, compare.getData1());
    }
    
    @Test
    public void pitchIsAtLeastZero() {
        MidiNote note = new MidiNote(-35, 2, 1, 90);
        ShortMessage compare = note.noteOn();
        assertEquals(0, compare.getData1());
    }
    
    @Test
    public void maxValueForPitchIs127() {
        MidiNote note = new MidiNote(200, 2, 1, 90);
        ShortMessage compare = note.noteOn();
        assertEquals(127, compare.getData1());
    }

    @Test
    public void midiOnMessageGeneratingOk() {
        MidiNote note = new MidiNote(65, 2, 1, 80);
        ShortMessage compare = new ShortMessage();
        try {
            compare.setMessage(ShortMessage.NOTE_ON, 1, 65, 80);
        } catch (InvalidMidiDataException ex) {
        }
        assertEquals(compare.getChannel(), note.noteOn().getChannel());
        assertEquals(compare.getCommand(), note.noteOn().getCommand());
        assertEquals(compare.getData1(), note.noteOn().getData1());
        assertEquals(compare.getData2(), note.noteOn().getData2());
    }

    @Test
    public void midiOffMessageGeneratingOk() {
        MidiNote note = new MidiNote(65, 2, 1, 80);
        ShortMessage compare = new ShortMessage();
        try {
            compare.setMessage(ShortMessage.NOTE_OFF, 1, 65, 0);
        } catch (InvalidMidiDataException ex) {
        }
        assertEquals(compare.getChannel(), note.noteOff().getChannel());
        assertEquals(compare.getCommand(), note.noteOff().getCommand());
        assertEquals(compare.getData1(), note.noteOff().getData1());
        assertEquals(compare.getData2(), note.noteOff().getData2());
    }
}
