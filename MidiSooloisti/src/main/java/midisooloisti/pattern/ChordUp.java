package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Random;
import midisooloisti.player.MidiNote;

/**
 * ChordUp toteuttaa Pattern-rajapinnan. Luokka tuottaa tahdin verran nuotteja
 * MidiNote-listana. Nuottikuvio muodostuu neljästä ylöspäin kulkevasta
 * sointuäänestä. Neljän nuotin ryhmän seuraava sijainti on yhtä sointuääntä
 * ylempänä tai alempana riippuen tahdin alussa määriteltävästä
 * suuntamuuttujasta. Jos asetetun nuottialueen rajat tulevat vastaan kesken
 * tahdin, vaihdetaan nuottikuvion etenemissuuntaa.
 */
public class ChordUp implements Pattern {

    private int currentChordNoteIndex;
    private Random random;
    private int limit;

    public ChordUp(Random random) {
        this.random = random;
        this.currentChordNoteIndex = 0;
        this.limit = 4;     // Notes are in groups of four descending notes.
    }

    /**
     * Metodi muodostaa annetulla asteikolla neljä peräkkäistä neljän nuotin
     * kuviota, jotka muodostuvat ylöspäin kulkevista sointuäänistä.
     *
     * @param scale Käytettävä asteikko Scale-oliona.
     * @param currentPitch  Viimeisin soitettu nuotti. Annetaan Midi-äänenkorkeusarvona.
     *
     * @return ArrayList, joka sisältää 16 kappaletta MidiNote-olioita.
     */
    @Override
    public ArrayList<MidiNote> getNotes(Scale scale, int currentPitch) {
        ArrayList<Integer> chordNotes = scale.getChordNotes();
        ArrayList<Integer> notePattern = new ArrayList<>();

        int direction = this.direction(random);
        this.currentChordNoteIndex = scale.findIndexOfClosestChordNoteInChordNotes(currentPitch);

        if (this.currentChordNoteIndex > (chordNotes.size() - limit)) {
            this.currentChordNoteIndex = chordNotes.size() - this.random.nextInt(limit) - limit;
        }

        for (int i = 0; i < 4; i++) {       // Four quarter notes
            for (int j = 0; j < 4; j++) {   // Groups of four sixteenth notes
                notePattern.add(chordNotes.get(this.currentChordNoteIndex + j));
            }

            direction = this.direction(chordNotes.size(), direction);
            this.currentChordNoteIndex += direction;

        }

        // Last note = surprise note
        notePattern.set((notePattern.size() - 1), (notePattern.get(notePattern.size() - 1) - 1));

        return this.integersToMidiNotes(notePattern);
    }

    private int direction(int arraySize, int direction) {
        if (this.currentChordNoteIndex == 0) {
            return 1;
        } else if (this.currentChordNoteIndex >= (arraySize - limit)) {
            return -1;
        }

        return direction;
    }

}
