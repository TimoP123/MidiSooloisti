package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Random;

public class Linear implements Pattern {

    private int currentNoteIndex;
    private Random random;

    public Linear(Random random) {
        this.random = random;
        this.currentNoteIndex = 0;
    }

    @Override
    public ArrayList<Integer> getNotes(Scale scale, int currentPitch) {
        ArrayList<Integer> notes = scale.getNotes();
        ArrayList<Integer> notePattern = new ArrayList<>();

        int direction = this.direction(random);

        for (int i = 0; i < 4; i++) {
            currentPitch = scale.closestChordNote(currentPitch);
            this.currentNoteIndex = this.findIndexOfPitch(scale, currentPitch);

            notePattern.add(notes.get(this.currentNoteIndex));
            for (int j = 0; j < 3; j++) {
                direction = direction(notes.size(), direction);
                currentNoteIndex += direction;
                currentPitch = notes.get(this.currentNoteIndex);
                notePattern.add(currentPitch);
            }
        }

        return notePattern;
    }

    private int direction(int noteListSize, int direction) {
        if (this.currentNoteIndex == 0) {
            return 1;
        } else if (this.currentNoteIndex == (noteListSize - 1)) {
            return -1;
        }

        return direction;
    }
}
