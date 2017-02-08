package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Random;


public class ChordUp implements Pattern {

    private int currentChordNoteIndex;
    private Random random;

    public ChordUp(Random random) {
        this.random = random;
        this.currentChordNoteIndex = 0;
    }

    @Override
    public ArrayList<Integer> getNotes(Scale scale, int currentPitch) {
        ArrayList<Integer> chordNotes = scale.getChordNotes();
        ArrayList<Integer> notePattern = new ArrayList<>();

        int direction = this.direction(random);
        this.currentChordNoteIndex = scale.findIndexOfClosestChordNote(currentPitch);

        if(this.currentChordNoteIndex > (chordNotes.size() - 4)) {
            this.currentChordNoteIndex = chordNotes.size() - this.random.nextInt(chordNotes.size() - 6) - 4;
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                notePattern.add(chordNotes.get(this.currentChordNoteIndex + j));
            }

            direction = this.direction(chordNotes.size(), direction);
            this.currentChordNoteIndex += direction;

        }

        // Last note = surprise note
        notePattern.set((notePattern.size() - 1), (notePattern.get(notePattern.size() - 1) - 1));
        
        return notePattern;
    }

    private int direction(int arraySize, int direction) {
        if (this.currentChordNoteIndex == 0) {
            return 1;
        } else if (this.currentChordNoteIndex >= (arraySize - 5)) {
            return -1;
        }

        return direction;
    }

}