package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Random;
import midisooloisti.player.MidiNote;

/**
 * Bach toteuttaa Pattern-rajapinnan. Luokka tuottaa tahdin verran nuotteja
 * MidiNote-listana. Nuottikuvio muodostuu kahdesta kahdeksan
 * kuudestoistaosanuotin mittaisesta melodiasta. Tämä melodia on sama, jolla
 * Bachin Preludi (cm, Das Wohltemperierte Klavier I) alkaa. Tässä tapauksessa
 * tämä melodia voi alkaa miltä sointuääneltä tahansa ja tahdin puolivälissä se
 * siirretään satunnaisesti yhden sointuäänen verran ylemmäksi tai alemmaksi.
 */
public class Bach implements Pattern {

    private int currentChordNoteIndex;
    private Random random;
    private int limit;

    public Bach(Random random) {
        this.random = random;
        this.currentChordNoteIndex = 0;
        this.limit = 3;     // Pattern area is three chord notes
    }

    /**
     * Metodi muodostaa annetulla asteikolla kaksi peräkkäistä kahdeksan nuotin
     * kuviota, joiden esikuvana toimii Bachin Cm-preludin kahdeksan ensimmäistä
     * nuottia.
     *
     * @param scale Käytettävä asteikko Scale-oliona.
     * @param currentPitch Viimeisin soitettu nuotti. Annetaan
     * Midi-äänenkorkeusarvona.
     *
     * @return ArrayList, joka sisältää 16 kappaletta MidiNote-olioita.
     */
    @Override
    public ArrayList<MidiNote> getNotes(Scale scale, int currentPitch) {
        ArrayList<Integer> chordNotes = scale.getChordNotes();
        ArrayList<Integer> notePattern = new ArrayList<>();

        int direction = this.direction(random);
        this.currentChordNoteIndex = scale.findIndexOfClosestChordNoteInChordNotes(currentPitch);

        if (this.currentChordNoteIndex < limit) {
            this.currentChordNoteIndex = 2 + this.random.nextInt(limit);
        }

        for (int i = 0; i < 2; i++) {    // Two times eight sixteenth notes
            notePattern.add(chordNotes.get(this.currentChordNoteIndex));
            notePattern.add(chordNotes.get(this.currentChordNoteIndex - 1));
            notePattern.add(chordNotes.get(this.currentChordNoteIndex - 1) - 1);
            notePattern.add(chordNotes.get(this.currentChordNoteIndex - 1));
            notePattern.add(chordNotes.get(this.currentChordNoteIndex - 2));
            notePattern.add(chordNotes.get(this.currentChordNoteIndex - 1));
            notePattern.add(chordNotes.get(this.currentChordNoteIndex - 1) - 1);
            notePattern.add(chordNotes.get(this.currentChordNoteIndex - 1));

            direction = this.direction(chordNotes.size(), direction);
            this.currentChordNoteIndex += direction;
        }

        return this.integersToMidiNotes(notePattern);
    }

    private int direction(int arraySize, int direction) {
        if (this.currentChordNoteIndex == 2) {
            return 1;
        } else if (this.currentChordNoteIndex >= (arraySize - limit)) {
            return -1;
        }

        return direction;
    }
}
