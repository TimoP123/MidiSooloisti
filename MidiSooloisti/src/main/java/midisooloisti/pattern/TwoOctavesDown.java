package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Random;
import midisooloisti.player.MidiNote;

/**
 *    TwoOctavesDown toteuttaa Pattern-rajapinnan. Luokka tuottaa tahdin verran nuotteja MidiNote-listana. Nuotit ovat
 *    peräkkäisiä laskevia nuotteja. Kuvion aloituskohta valitaan satunnaisesti siten, että mahdollisuuksien mukaan
 *    jokainen nuotti voisi olla edeltäjäänsä yhtä alempana. Jos käytettävissä olevien nuottien määrä ei riitä tähän,
 *    nostetaan soitettavaa nuottia tarvittaessa oktaavin verran ylemmäksi.
 */
public class TwoOctavesDown implements Pattern {

    private int currentNoteIndex;
    private Random random;

    public TwoOctavesDown(Random random) {
        this.random = random;
        this.currentNoteIndex = 0;
    }

    @Override
    public ArrayList<MidiNote> getNotes(Scale scale, int currentPitch) {
        ArrayList<Integer> notes = scale.getNotes();
        ArrayList<Integer> notePattern = new ArrayList<>();

        if (notes.size() < 16) {
            this.currentNoteIndex = notes.size() - 1;
        } else {
            this.currentNoteIndex = random.nextInt(notes.size() - 15) + 14;
        }

        for (int i = 0; i < 16; i++) {

            notePattern.add(notes.get(this.currentNoteIndex));
            this.currentNoteIndex--;
            if (this.currentNoteIndex < 0) {
                this.currentNoteIndex += 12;
                if(this.currentNoteIndex > notes.size()) {
                    this.currentNoteIndex = notes.size() - 1;
                }
            }
        }

        return this.integersToMidiNotes(notePattern);
    }

}
