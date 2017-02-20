package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Random;
import midisooloisti.player.MidiNote;

/**
 * TwoOctavesDown toteuttaa Pattern-rajapinnan. Luokka tuottaa tahdin verran
 * nuotteja MidiNote-listana. Nuotit ovat peräkkäisiä laskevia nuotteja. Kuvion
 * aloituskohta valitaan satunnaisesti siten, että mahdollisuuksien mukaan
 * jokainen nuotti voisi olla edeltäjäänsä yhtä alempana. Jos käytettävissä
 * olevien nuottien määrä ei riitä tähän, nostetaan soitettavaa nuottia
 * tarvittaessa oktaavin verran ylemmäksi.
 */
public class TwoOctavesDown implements Pattern {

    private int currentNoteIndex;
    private Random random;

    /**
     * Konstruktori.
     * 
     * @param random Random.
     */
    public TwoOctavesDown(Random random) {
        this.random = random;
        this.currentNoteIndex = 0;
    }

    /**
     * Metodi muodostaa annetulla asteikolla kulkevan laskevan nuottilinjan.
     *
     * @param scale Käytettävä asteikko Scale-oliona.
     * @param currentPitch Viimeisin soitettu nuotti. Annetaan
     * Midi-äänenkorkeusarvona.
     *
     * @return ArrayList, joka sisältää 16 kappaletta MidiNote-olioita.
     */
    @Override
    public ArrayList<MidiNote> getNotes(Scale scale, int currentPitch) {
        ArrayList<Integer> notes = scale.getNotes();
        ArrayList<Integer> notePattern = new ArrayList<>();

        this.currentNoteIndex = random.nextInt(Math.abs(notes.size() - 15)) + 14;

        for (int i = 0; i < 16; i++) {

            notePattern.add(notes.get(this.currentNoteIndex));
            this.currentNoteIndex--;
            if (this.currentNoteIndex < 0) {
                this.currentNoteIndex += 12;
                if (this.currentNoteIndex > notes.size()) {
                    this.currentNoteIndex = notes.size() - 1;
                }
            }
        }

        return this.integersToMidiNotes(notePattern);
    }

}
