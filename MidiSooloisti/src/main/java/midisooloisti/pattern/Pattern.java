package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Random;

public interface Pattern {

    public ArrayList<Integer> getNotes(Scale scale, int currentPitch);

    default int direction(Random random) {
        int value = random.nextInt(100);

        if (value >= 50) {
            return 1;
        }

        return -1;
    }

    default int findIndexOfPitch(Scale scale, int pitch) {
        ArrayList<Integer> notes = scale.getNotes();

        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i) == pitch) {
                return i;
            }
        }

        return 0;
    }

    default int findIndexOfPitchInChordNotes(Scale scale, int pitch) {
        ArrayList<Integer> chordNotes = scale.getChordNotes();

        for (int i = 0; i < chordNotes.size(); i++) {
            if (chordNotes.get(i) == pitch) {
                return i;
            }
        }

        return 0;
    }

}
