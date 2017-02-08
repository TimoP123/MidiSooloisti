package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Random;

public class TwoOctavesDown implements Pattern {

    private int currentNoteIndex;
    private Random random;

    public TwoOctavesDown() {
        this.currentNoteIndex = 0;
    }

    @Override
    public ArrayList<Integer> getNotes(Scale scale, int currentPitch) {
        ArrayList<Integer> notes = scale.getNotes();
        ArrayList<Integer> notePattern = new ArrayList<>();

        currentPitch = scale.closestChordNote(currentPitch);
        //this.currentNoteIndex = this.findIndexOfPitch(scale, currentPitch);
        if (this.currentNoteIndex < 15) {
            this.currentNoteIndex = 15;
        }
        if (notes.size() < 16) {
            this.currentNoteIndex = notes.size() - 1;
        }

        for (int i = 0; i < 16; i++) {

            notePattern.add(notes.get(this.currentNoteIndex));
            this.currentNoteIndex--;
            if (this.currentNoteIndex < 0) {
                this.currentNoteIndex += 12;
            }
        }

        return notePattern;
    }

}
