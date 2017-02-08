package midisooloisti;

import midisooloisti.player.Player;
import midisooloisti.player.MidiNote;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        player.setSound(1, 81);

        int[] notes = {0, 2, 3, 5, 7, 8, 11};   //  C harmonic minor
        int[] notes2 = {5, 7, 8, 11, 12, 14, 15};
        int[] notes3 = {7, 8, 11, 12, 14, 15, 17};
        Scale scale = new Scale(40, 90, notes);

        System.out.println(scale);

        int currentNote = 85;

        Pattern linear = new Linear(random);
        Pattern chordUp = new ChordUp(random);
        Pattern chordDown = new ChordDown(random);
        Pattern figure3123 = new Figure3123(random);
        Pattern twoOctavesDown = new TwoOctavesDown();
        
        ArrayList<Pattern> patterns = new ArrayList<>();
        patterns.add(linear);
        patterns.add(chordUp);
        /*
        patterns.add(chordDown);
        patterns.add(figure3123);
        patterns.add(twoOctavesDown);
*/

        for (int i = 0; i < 32; i++) {
            int index = random.nextInt(patterns.size());

            scale.setNotes(notes);
            
            ArrayList<Integer> pattern = patterns.get(index).getNotes(scale, currentNote);
            playIntList(player, pattern);
            printList(pattern);
            currentNote = pattern.get(pattern.size() - 1);
            
            index = random.nextInt(patterns.size());
            scale.setNotes(notes2);
            
            pattern = patterns.get(index).getNotes(scale, currentNote);
            playIntList(player, pattern);
            printList(pattern);
            currentNote = pattern.get(pattern.size() - 1);
            
            index = random.nextInt(patterns.size());
            scale.setNotes(notes3);
            
            pattern = patterns.get(index).getNotes(scale, currentNote);
            playIntList(player, pattern);
            printList(pattern);
            currentNote = pattern.get(pattern.size() - 1);
            
            index = random.nextInt(patterns.size());
            scale.setNotes(notes);
            
            pattern = patterns.get(index).getNotes(scale, currentNote);
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
                Thread.sleep(10);
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
            System.out.print(list.get(i));
            if(i < (list.size() - 1)) {
                System.out.print(", ");
            } 
        }
        System.out.println("");
    }
}
