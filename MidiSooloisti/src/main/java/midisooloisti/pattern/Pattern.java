package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Random;
import midisooloisti.player.MidiNote;

public interface Pattern {

    public ArrayList<MidiNote> getNotes(Scale scale, int currentPitch);

    default int direction(Random random) {
        int value = random.nextInt(100);

        if (value >= 50) {
            return 1;
        }

        return -1;
    }

    default ArrayList<MidiNote> integersToMidiNotes(ArrayList<Integer> pitch) {
        ArrayList<MidiNote> notes = new ArrayList<>();

        for (int i = 0; i < pitch.size(); i++) {
            notes.add(new MidiNote(pitch.get(i), 1, 1, 100));
        }

        return notes;
    }

}
