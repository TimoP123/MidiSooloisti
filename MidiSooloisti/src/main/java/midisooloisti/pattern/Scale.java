package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Collections;

public class Scale {

    private int lowerLimit;
    private int upperLimit;
    private ArrayList<Integer> notes;
    private ArrayList<Integer> chordNotes;

    public Scale(int lowerLimit, int upperLimit, int[] baseValues) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.notes = new ArrayList<>();
        this.chordNotes = new ArrayList<>();
        this.setNotes(baseValues);
    }

    public void setNotes(int[] baseValues) {

        this.notes.clear();
        this.chordNotes.clear();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < baseValues.length; j++) {
                int pitch = i * 12 + baseValues[j];
                if (pitch < this.lowerLimit) {
                    continue;
                } else if (pitch > this.upperLimit) {
                    break;
                } else {
                    this.notes.add(pitch);
                    if (j == 0 || j == 2 || j == 4) {
                        this.chordNotes.add(pitch);
                    }
                }
            }
        }
    }

    public ArrayList<Integer> getNotes() {
        return this.notes;
    }

    public ArrayList<Integer> getChordNotes() {
        return this.chordNotes;
    }

    public int closestChordNote(int pitch) {
        if (pitch > this.chordNotes.get(this.chordNotes.size() - 1)) {
            return this.chordNotes.get(this.chordNotes.size() - 1);
        }

        int distance = 999;
        int i = 0;
        while (i < this.chordNotes.size()) {
            int newDistance = Math.abs(pitch - this.chordNotes.get(i));
            if (newDistance > distance) {
                i--;
                if (i < 0) {
                    i = 0;
                }
                break;
            }
            distance = newDistance;
            i++;
        }

        if (i > (this.chordNotes.size() - 1)) {
            i--;
        }
        return this.chordNotes.get(i);
    }

    @Override
    public String toString() {
        String s = "Scale notes:\n";
        for (int i = 0; i < this.notes.size(); i++) {
            s += this.notes.get(i);
            s += ", ";
        }

        s += "\nChord notes:\n";
        for (int i = 0; i < this.chordNotes.size(); i++) {
            s += this.chordNotes.get(i);
            if (i < (this.notes.size() - 1)) {
                s += ", ";
            }
        }

        return s;
    }
}
