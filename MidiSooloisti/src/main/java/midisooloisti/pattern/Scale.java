package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Scale-luokka huolehtii käytettävän asteikon mukaisten äänenkorkeusarvojen
 * laskemisesta sekä asteikko- ja sointuäänilistojen tarjoamisesta niitä
 * tarvitseville metodeille. Luokan konstruktorissa annetaan parametreina
 * käytettävän asteikkoa
 *
 */
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

    /**
     * Metodi laskee parametrina annetun kokonaislukutaulukon perusteella
     * kaikkien niiden Midi-äänenkorkeuksien arvot, jotka kuuluvat haluttuun
     * asteikkoon sekä ovat soitettavan äänialueen rajojen sisällä. Arvot
     * tallennetaan olion sisäisiin asteikko- ja sointuäänilistoihin.
     *
     * @param baseValues Kokonaislukutaulukko, joka sisältää asettavan asteikon
     * äänenkorkeuksien arvot mahdollisimman pieninä lukuina.
     */
    public void setNotes(int[] baseValues) {
        this.notes.clear();
        this.chordNotes.clear();

        for (int i = 1; i < 10; i++) {
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

    /**
     * Metodi palauttaa parametrina annettua nuottia lähimpänä olevan
     * soitettavan äänialueen sisällä olevan sointuäänen korkeuden.
     *
     * @param pitch Tutkittavan nuotin Midi-äänenkorkeus kokonaislukuna.
     *
     * @return Tutkittavaa nuottia lähimpänä olevan sointuäänen korkeus
     * kokonaislukuna.
     */
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

        if (i > (chordNotes.size() - 1)) {
            i--;
        }

        return this.chordNotes.get(i);
    }

    /**
     * Metodi palauttaa parametrina annettun nuotin sävelkorkeutta vastaavan
     * indeksin, jonka avulla kyseinen nuotti löydetään asteikkoäänilistasta.
     *
     * @param pitch Tutkittavan nuotin Midi-äänenkorkeus kokonaislukuna.
     *
     * @return Tutkittavan nuotin sävelkorkeuden indeksi asteikkoäänilistassa.
     */
    public int findIndexOfPitch(int pitch) {
        for (int i = 0; i < this.notes.size(); i++) {
            if (this.notes.get(i) == pitch) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Metodi palauttaa parametrina annettun nuotin sävelkorkeutta vastaavan
     * indeksin, jonka avulla kyseistä nuottia lähimpänä oleva sointuääni
     * löydetään asteikkoäänilistasta.
     *
     * @param pitch Tutkittavan nuotin Midi-äänenkorkeus kokonaislukuna.
     *
     * @return Tutkittavaa nuottia lähimpänä olevan sointuäänen sävelkorkeuden
     * indeksi asteikkoäänilistassa.
     */
    public int findIndexOfClosestChordNote(int pitch) {
        return this.findIndexOfPitch(this.closestChordNote(pitch));
    }

    /**
     * Metodi palauttaa parametrina annettun nuotin sävelkorkeutta vastaavan
     * indeksin, jonka avulla kyseinen nuotti löydetään sointuäänilistasta.
     *
     * @param pitch Tutkittavan nuotin Midi-äänenkorkeus kokonaislukuna.
     *
     * @return Tutkittavan nuotin sävelkorkeuden indeksi sointuäänilistassa.
     */
    public int findIndexOfPitchInChordNotes(int pitch) {
        for (int i = 0; i < this.chordNotes.size(); i++) {
            if (this.chordNotes.get(i) == pitch) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Metodi palauttaa parametrina annettun nuotin sävelkorkeutta vastaavan
     * indeksin, jonka avulla kyseistä nuottia lähimpänä oleva sointuääni
     * löydetään sointuäänilistasta.
     *
     * @param pitch Tutkittavan nuotin Midi-äänenkorkeus kokonaislukuna.
     *
     * @return Tutkittavaa nuottia lähimpänä olevan sointuäänen sävelkorkeuden
     * indeksi sointuäänilistassa.
     */
    public int findIndexOfClosestChordNoteInChordNotes(int pitch) {
        return this.findIndexOfPitchInChordNotes(this.closestChordNote(pitch));
    }

    @Override
    public String toString() {
        String s = "Asteikon äänet:\n";
        s += printableList(this.notes);
        s += "Sointuäänet:\n";
        s += printableList(this.chordNotes);
        return s;
    }

    private String printableList(ArrayList<Integer> list) {
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            s += list.get(i);
            if (i < (list.size() - 1)) {
                s += ", ";
            }
        }
        s += "\n";
        return s;
    }
}
