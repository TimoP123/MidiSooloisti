package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Random;
import midisooloisti.player.MidiNote;

public class Figure3123 implements Pattern {

    private int currentNoteIndex;
    private Random random;

    public Figure3123(Random random) {
        this.random = random;
        this.currentNoteIndex = 0;
    }

    @Override
    public ArrayList<MidiNote> getNotes(Scale scale, int currentPitch) {
        ArrayList<Integer> notes = scale.getNotes();
        ArrayList<Integer> notePattern = new ArrayList<>();

        int direction = this.direction(random);
        currentPitch = scale.closestChordNote(currentPitch);
        //this.currentNoteIndex = this.findIndexOfPitch(scale, currentPitch);
        if (this.currentNoteIndex < 3 && direction == -1) {
            this.currentNoteIndex = 4;
        }
        if (this.currentNoteIndex > (notes.size() - 4) && direction == 1) {
            this.currentNoteIndex = notes.size() - 4;
        }

        for (int i = 0; i < 4; i++) {

            notePattern.add(notes.get(this.currentNoteIndex));
            notePattern.add(notes.get(this.currentNoteIndex - 2));
            notePattern.add(notes.get(this.currentNoteIndex - 1));
            notePattern.add(notes.get(this.currentNoteIndex));

            direction = patternDirection(notes.size(), direction);
            currentNoteIndex += direction;
        }

        return this.integersToMidiNotes(notePattern);
    }

    private int patternDirection(int noteListSize, int direction) {
        if (this.currentNoteIndex <= 4) {
            return 1;
        } else if (this.currentNoteIndex == (noteListSize - 4)) {
            return -1;
        }

        return direction;
    }

}
