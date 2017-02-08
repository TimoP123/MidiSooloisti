package midisooloisti.player;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

/**
 *    MidiNote-luokan avulla saadaan tallennettua yksittäiseen nuottiin liittyvät tiedot. Luokka tarjoaa metodit nuottien soittamiseen
 *    tarvittavien ShortMessage-olioiden muodostamiseen sekä nuotin pituuden tarkistamiseen.
 */
public class MidiNote {

    private int pitch;
    private int length;
    private int channel;
    private int velocity;

    public MidiNote(int pitch, int length, int channel, int velocity) {
        this.pitch = limitValue(pitch, 0, 127);
        this.length = limitValue(length, 1, 16); // Must be at least one, maximum is 16.
        this.channel = limitValue(channel, 0, 15); // There are only 16 possible midi channels.
        this.velocity = limitValue(velocity, 0, 127);
    }

    private int limitValue(int value, int lower, int upper) {
        if (value >= lower && value <= upper) {
            return value;
        } else if (value < lower) {
            return lower;
        } else {
            return upper;
        }
    }

/**
 *    Metodi muodostaa ShortMessage-olion, jonka avulla saadaan Midi-laite soittamaan yksittäisen nuotin.
 *
 *    @return    ShortMessage, joka asettaa nuotin soimaan.
 */
    public ShortMessage noteOn() {
        ShortMessage noteOn = this.midiMessage(true);
        return noteOn;
    }


/**
 *    Metodi muodostaa ShortMessage-olion, jonka avulla saadaan soiva nuotti sammutettua Midi-laitteelta.
 *
 *    @return    ShortMessage, joka sammuttaa nuotin.
 */
    public ShortMessage noteOff() {
        ShortMessage noteOff = this.midiMessage(false);
        return noteOff;
    }

    private ShortMessage midiMessage(boolean on) {
        ShortMessage midiMessage = new ShortMessage();

        try {
            if (on) {
                midiMessage.setMessage(ShortMessage.NOTE_ON, this.channel, this.pitch, this.velocity);
            } else {
                midiMessage.setMessage(ShortMessage.NOTE_OFF, this.channel, this.pitch, 0);
            }
            return midiMessage;
        } catch (InvalidMidiDataException e) {
            System.out.println("Invalid Midi data!");
        }
        return null;
    }

    public int length() {
        return this.length;
    }

    public int getPitch() {
        return this.pitch;
    }
}
