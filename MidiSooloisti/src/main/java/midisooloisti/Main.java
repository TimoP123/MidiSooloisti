package midisooloisti;

import midisooloisti.player.Player;
import midisooloisti.player.MidiNote;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import midisooloisti.pattern.Bach;
import midisooloisti.pattern.ChordDown;
import midisooloisti.pattern.ChordUp;
import midisooloisti.pattern.Figure3123;
import midisooloisti.pattern.Linear;
import midisooloisti.pattern.Pattern;
import midisooloisti.pattern.Scale;
import midisooloisti.pattern.TwoOctavesDown;

public class Main {

    public static void main(String[] args) {

        // This is test code until graphicalInterface is implemented.
        //
        //
        Random random = new Random();
        Player player = new Player();
        player.setSound(1, 81);         // Channel 1, Synth lead = 81
/*
        int[] notes = {0, 2, 3, 5, 7, 8, 11};   //  C harmonic minor
        int[] notes2 = {5, 7, 8, 11, 12, 14, 15};   // 4th
        int[] notes3 = {7, 8, 11, 12, 14, 15, 17};  // 5th
*/        
        int[] notes = {0, 2, 4, 5, 7, 9, 11};   //  C major
        int[] notes2 = {5, 7, 9, 11, 12, 14, 16};   // 4th
        int[] notes3 = {7, 9, 11, 12, 14, 16, 17};  // 5th
        
        Scale scale = new Scale(50, 90, notes);

        System.out.println(scale);

        int currentNote = 85;

        Pattern linear = new Linear(random);
        Pattern chordUp = new ChordUp(random);
        Pattern chordDown = new ChordDown(random);
        Pattern figure3123 = new Figure3123(random);
        Pattern twoOctavesDown = new TwoOctavesDown(random);
        Pattern bach = new Bach(random);

        ArrayList<Pattern> patterns = new ArrayList<>();
        patterns.add(linear);
        patterns.add(chordUp);
        patterns.add(chordDown);
        patterns.add(figure3123);
        patterns.add(twoOctavesDown);
        patterns.add(bach);

        for (int i = 0; i < 32; i++) {
            int index = random.nextInt(patterns.size());

            scale.setNotes(notes);
            ArrayList<MidiNote> pattern = patterns.get(index).getNotes(scale, currentNote);
            playNoteList(player, pattern);
            printList(pattern);
            currentNote = pattern.get(pattern.size() - 1).getPitch();

            scale.setNotes(notes2);
            pattern = patterns.get(index).getNotes(scale, currentNote);
            playNoteList(player, pattern);
            printList(pattern);
            currentNote = pattern.get(pattern.size() - 1).getPitch();
            
            index = random.nextInt(patterns.size());

            scale.setNotes(notes3);
            pattern = patterns.get(index).getNotes(scale, currentNote);
            playNoteList(player, pattern);
            printList(pattern);
            currentNote = pattern.get(pattern.size() - 1).getPitch();

            scale.setNotes(notes);
            pattern = patterns.get(index).getNotes(scale, currentNote);
            playNoteList(player, pattern);
            printList(pattern);
            currentNote = pattern.get(pattern.size() - 1).getPitch();

        }

    }

    public static void playNoteList(Player player, ArrayList<MidiNote> list) {
        player.setNotes(list);
        player.begin();
        for (int i = 0; i < 16; i++) {
            try {
                Thread.sleep(80);
            } catch (InterruptedException ex) {
            }
            player.forward();
        }
    }

    public static void printList(ArrayList<MidiNote> list) {
        System.out.println("Lista:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).getPitch());
            if (i < (list.size() - 1)) {
                System.out.print(", ");
            }
        }
        System.out.println("");
    }
}
