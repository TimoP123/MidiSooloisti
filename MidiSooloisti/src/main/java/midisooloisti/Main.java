package midisooloisti;

import midisooloisti.player.Player;
import midisooloisti.player.MidiNote;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import midisooloisti.pattern.ChordUp;
import midisooloisti.pattern.Linear;
import midisooloisti.pattern.Pattern;
import midisooloisti.pattern.Scale;

public class Main {

    public static void main(String[] args) {

        // This is test code until graphicalInterface is implemented.
        //
        //
        Random random = new Random();
        Player player = new Player();
        player.setSound(1, 81);

        int[] notes = {0, 2, 3, 5, 7, 8, 11};   //  C harmonic minor
        Scale scale = new Scale(45, 85, notes);

        System.out.println(scale);

        int currentNote = 54;

        Pattern lineaarinen = new Linear(random);
        Pattern chordUp = new ChordUp(random);

        for (int i = 0; i < 16; i++) {
            ArrayList<Integer> pattern = lineaarinen.getNotes(scale, currentNote);
            playIntList(player, pattern);
            printList(pattern);
            currentNote = pattern.get(pattern.size() - 1);

            pattern = chordUp.getNotes(scale, currentNote);
            playIntList(player, pattern);
            printList(pattern);
            currentNote = pattern.get(pattern.size() - 1);
        }

    }

    public static void playIntList(Player player, ArrayList<Integer> list) {
        ArrayList<MidiNote> notes = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            notes.add(new MidiNote(list.get(i), 1, 1, 100));
        }

        player.setNotes(notes);
        player.begin();
        for (int i = 0; i < 16; i++) {
            try {
                Thread.sleep(90);
            } catch (InterruptedException ex) {
            }
            player.forward();
        }

    }

    public static ArrayList<MidiNote> notes(int[] pitch, int[] length) {
        ArrayList<MidiNote> notes = new ArrayList<>();

        for (int i = 0; i < pitch.length; i++) {
            notes.add(new MidiNote(pitch[i], length[i], 1, 100));
        }

        return notes;
    }

    public static void printList(ArrayList<Integer> list) {
        System.out.println("Lista:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + ", ");
        }
        System.out.println("");
    }
}
