package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Random;
import midisooloisti.player.MidiNote;

/**
 * Figure3123 toteuttaa Pattern-rajapinnan. Luokka tuottaa tahdin verran
 * nuotteja MidiNote-listana. Nuottikuvio muodostuu nimensä mukaisesti kuviosta,
 * jossa on kolmea vierekkäistä asteikon ääntä neljän nuotin ryhmissä
 * järjestettynä siten, että ylin näistä äänistä tulee ensin, sitten hypätään
 * pari ääntä alemmas ja palataan asteikkoa pitkin lähtöääneen. Viimeinen neljän
 * nuotin ryhmä tehdään variaationa 3212. Jokaisessa neljän nuotin ryhmässä
 * ensimmäinen ääni on kyseisen asteikon ja sointuasteen sointutääni. Neljän
 * nuotin ryhmän seuraava sijainti on yhtä sointuääntä ylempänä tai alempana
 * riippuen tahdin alussa määriteltävästä suuntamuuttujasta. Jos asetetun
 * nuottialueen rajat tulevat vastaan kesken tahdin, vaihdetaan nuottikuvion
 * etenemissuuntaa.
 */
public class Figure3123 implements Pattern {

    private int currentNoteIndex;
    private Random random;
    private int limit;

    public Figure3123(Random random) {
        this.random = random;
        this.currentNoteIndex = 0;
        this.limit = 3;  // Pattern area is three notes
    }

    @Override
    public ArrayList<MidiNote> getNotes(Scale scale, int currentPitch) {
        ArrayList<Integer> notes = scale.getNotes();
        ArrayList<Integer> notePattern = new ArrayList<>();

        int direction = this.direction(random);
        this.currentNoteIndex = scale.findIndexOfClosestChordNote(currentPitch);

        if (this.currentNoteIndex < limit) {
            this.currentNoteIndex = scale.findIndexOfClosestChordNote(currentPitch + limit);
        }

        for (int i = 0; i < 3; i++) {
            notePattern.add(notes.get(this.currentNoteIndex));
            notePattern.add(notes.get(this.currentNoteIndex - 2));
            notePattern.add(notes.get(this.currentNoteIndex - 1));
            notePattern.add(notes.get(this.currentNoteIndex));

            direction = direction(notes.size(), direction);
            currentNoteIndex += direction;
        }

        // Small variation for last four notes
        notePattern.add(notes.get(this.currentNoteIndex));
        notePattern.add(notes.get(this.currentNoteIndex - 2));
        notePattern.add(notes.get(this.currentNoteIndex));
        notePattern.add(notes.get(this.currentNoteIndex - 1));

        return this.integersToMidiNotes(notePattern);
    }

    private int direction(int arraySize, int direction) {
        if (this.currentNoteIndex < limit) {
            return 1;
        } else if (this.currentNoteIndex >= (arraySize - 1)) {
            return -1;
        }

        return direction;
    }

}
