package midisooloisti.player;

import midisooloisti.player.MidiNote;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 *    Player-luokka avaa käytettävän Midi-laitteen ohjelman käyttöön ja soittaa sille listana annetut Midi-nuotit.
 */
public class Player {

    private String deviceName;
    private MidiDevice outputDevice;
    private Receiver receiver;
    private ArrayList<MidiNote> notes;
    private int tick;
    private int ticksLeft;
    private int index;

    public Player() {
        this.deviceName = "Gervill";    // Default built-in MidiSoftSynthesizer.
        //this.deviceName = "M4x4 [hw:2,0,0]";
        this.setMidiOutDeviceAndReceiver(); // Set and open Midi-outputDevice and receiver for it.
        this.notes = new ArrayList<>();
        this.tick = 0;          // Position in 1/16-notes.
        this.ticksLeft = 0;     // Ticks left for current note.
        this.index = 0;         // Index of next note in ArrayList.
    }

    private void setMidiOutDeviceAndReceiver() {
        try {
            this.outputDevice = MidiSystem.getMidiDevice(this.getMidiDeviceInfo());
            outputDevice.open();
            System.out.println("Midi out: " + outputDevice);
            this.receiver = outputDevice.getReceiver();
            System.out.println("Receiver: " + receiver);
        } catch (MidiUnavailableException e) {
            System.out.println("There's no Midi available.");
        }
    }

    private MidiDevice.Info getMidiDeviceInfo() {
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();

        for (int i = 0; i < infos.length; i++) {
            MidiDevice device;
            try {
                device = MidiSystem.getMidiDevice(infos[i]);
                if (device.getMaxReceivers() == 0) {
                    continue;
                }
            } catch (MidiUnavailableException ex) {
            }

            if (infos[i].getName().equals(this.deviceName)) {
                return infos[i];
            }
        }
        return null;
    }

    public void setNotes(ArrayList<MidiNote> notes) {
        this.notes = notes;
    }

    public void begin() {
        this.tick = 0;
        this.index = 0;
        this.receiver.send(this.notes.get(this.index).noteOn(), tick);
        this.ticksLeft = this.notes.get(this.index).length();
    }

    public void forward() {
        if (this.tick > 16) {
            return;
        }

        this.tick++;
        this.ticksLeft--;
        if (this.tick == 16) {
            this.receiver.send(this.notes.get(this.index).noteOff(), tick);
        } else if (this.ticksLeft <= 0) {
            this.receiver.send(this.notes.get(this.index).noteOff(), tick);
            this.index++;
            this.receiver.send(this.notes.get(this.index).noteOn(), tick);
            this.ticksLeft = this.notes.get(this.index).length();
        }
    }

    public void setSound(int channel, int sound) {
        ShortMessage soundChange = new ShortMessage();

        try {
            soundChange.setMessage(ShortMessage.PROGRAM_CHANGE, channel, sound, 0);
        } catch (InvalidMidiDataException ex) {
            System.out.println("Sound change failed.");
        }

        this.receiver.send(soundChange, 0);
    }

}
