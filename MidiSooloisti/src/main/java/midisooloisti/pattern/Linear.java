package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Random;
import midisooloisti.player.MidiNote;

/**
 * Linear toteuttaa Pattern-rajapinnan. Luokka tuottaa tahdin verran nuotteja
 * MidiNote-listana. Nuottikuvio muodostuu neljästä peräkkäisestä asteikkoon
 * kuuluvasta äänestä. Kuvio voi olla nouseva tai laskeva ja tämä valitaan
 * satunnaisesti tahdin alussa. Kuvioita mahtuu tahtiin kaikkiaan neljä
 * kappaletta ja ne kukin alkavat kyseiseen asteikkoon ja sointuasteeseen
 * liittyvältä sointuääneltä. Nuottikuvion aloituskohta siirtyy sointuääniä
 * pitkin ylöspäin tai alaspäin. Tämä suunta valitaan myös tahdin alussa
 * satunnaisesti ja suunta kääntyy, jos kesken tahtia saavutaan käytettävän
 * nuottialueen rajalle.
 */
public class Linear implements Pattern {

    private int currentNoteIndex;
    private Random random;

    /**
     * Konstruktori.
     *
     * @param random Random.
     */
    public Linear(Random random) {
        this.random = random;
        this.currentNoteIndex = 0;
    }

    /**
     * Metodi muodostaa annetulla asteikolla neljä peräkkäistä neljän nuotin
     * kuviota, jotka muodostuvat ylös- tai alaspäin kulkevista asteikon
     * äänistä.
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

        int direction = this.direction(random);

        for (int i = 0; i < 4; i++) {
            currentNoteIndex = scale.findIndexOfClosestChordNote(currentPitch);
            notePattern.add(notes.get(this.currentNoteIndex));

            for (int j = 0; j < 3; j++) {
                direction = direction(notes.size(), direction);
                currentNoteIndex += direction;
                currentPitch = notes.get(this.currentNoteIndex);
                notePattern.add(currentPitch);
            }
        }

        return this.integersToMidiNotes(notePattern);
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
