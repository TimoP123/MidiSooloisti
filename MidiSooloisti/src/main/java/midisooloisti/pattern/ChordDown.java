package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Random;
import midisooloisti.player.MidiNote;

public class ChordDown implements Pattern {

    private int currentNoteIndex;
    private int currentChordNoteIndex;
    private Random random;

    public ChordDown(Random random) {
        this.random = random;
        this.currentNoteIndex = 0;
        this.currentChordNoteIndex = 0;
    }

    @Override
    public ArrayList<MidiNote> getNotes(Scale scale, int currentPitch) {
        ArrayList<Integer> chordNotes = scale.getChordNotes();
        ArrayList<Integer> notePattern = new ArrayList<>();

        int direction = this.direction(random);

        currentPitch = scale.closestChordNote(currentPitch);
        //this.currentChordNoteIndex = this.findIndexOfPitchInChordNotes(scale, currentPitch);

        if (currentChordNoteIndex == (chordNotes.size() - 1)) {
            direction = -1;
        } else if (currentChordNoteIndex <= 3) {
            direction = 1;
        }

        for (int i = 0; i < 4; i++) {

            notePattern.add(chordNotes.get(this.currentChordNoteIndex));

            for (int j = 1; j < 4; j++) {
                int index = this.currentChordNoteIndex - j;
                if (index < 0) {
                    index += 3;
                }

                currentPitch = chordNotes.get(index);
                notePattern.add(currentPitch);

            }

            currentChordNoteIndex += direction;
            if (currentChordNoteIndex <= 3) {
                direction = 1;
            } else if (currentChordNoteIndex == (chordNotes.size() - 1)) {
                direction = -1;
            }
        }

        // Last note = surprise note
        notePattern.set((notePattern.size() - 1), (notePattern.get(notePattern.size() - 1) - 1));

        return this.integersToMidiNotes(notePattern);
    }
}
