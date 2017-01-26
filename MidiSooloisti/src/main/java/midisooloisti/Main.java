package midisooloisti;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        
        Player player = new Player();
        player.setSound(1, 81);
        
        int[] pitch = {72, 71, 68, 67, 65, 63, 62, 60, 59, 56, 55, 53, 51, 50, 48, 47};
        int[] length = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        
        int[] pitch2 = {51, 48, 50, 51, 53, 51, 53, 55, 59, 60, 53, 55, 67};
        int[] length2 = {1, 1, 1, 1, 2, 1, 1, 3, 1, 1, 1, 1, 1};
        
        for(int i = 0; i < 4; i++) {
            play(player, pitch, length);
            play(player, pitch2, length2);
        }
    }

    public static void play(Player player, int[] pitch, int[] length) {
        ArrayList<MidiNote> notes = notes(pitch, length);
        
        player.setNotes(notes);
        player.begin();
        for(int i = 0; i < 16; i++) {
            try {
                Thread.sleep(80);
            } catch (InterruptedException ex) {
            }
            player.forward();
        }
    }
    
    public static ArrayList<MidiNote> notes(int[] pitch, int[] length) {
        ArrayList<MidiNote> notes = new ArrayList<>();
        
        for(int i = 0; i < pitch.length; i++) {
            notes.add(new MidiNote(pitch[i], length[i], 1, 100));
        }
        
        return notes;
    }
}
