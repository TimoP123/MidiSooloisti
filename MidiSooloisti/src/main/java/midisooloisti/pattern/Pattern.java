package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Random;
import midisooloisti.player.MidiNote;

/**
 * Pattern-rajapinta kokoaa alleen joukon luokkia, joiden yhteisenä tehtävänä on
 * tuottaa tahdin verran kuudestoistaosanuotteja. Kaikkien rajapinnan
 * toteuttavien luokkien on toteutettava getNotes-metodi, joka palauttaa nuotit
 * MidiNote-listana. Pattern-rajapinta sisältää myös oletustoteutukset kahdesta
 * apumetodista kaikkien rajapinnan toteuttavien luokkien käyttöön.
 */
public interface Pattern {

    /**
     * Metodi muodostaa annetun asteikon äänistä nuottilinjan, jonka tarkempi
     * toteutus on rajapinnan toteuttavan luokan metodin vastuulla.
     *
     * @param scale Käytettävä asteikko Scale-oliona.
     * @param currontPitch Viimeisin soitettu nuotti. Annetaan
     * Midi-äänenkorkeusarvona.
     *
     * @return ArrayList, joka sisältää 16 kappaletta MidiNote-olioita.
     */
    public ArrayList<MidiNote> getNotes(Scale scale, int currentPitch);

    default int direction(Random random) {
        int value = random.nextInt(100);

        if (value >= 50) {
            return 1;
        }

        return -1;
    }

    default ArrayList<MidiNote> integersToMidiNotes(ArrayList<Integer> pitch) {
        ArrayList<MidiNote> notes = new ArrayList<>();

        for (int i = 0; i < pitch.size(); i++) {
            notes.add(new MidiNote(pitch.get(i), 1, 1, 100));
        }

        return notes;
    }

}
