package midisooloisti.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import midisooloisti.pattern.Bach;
import midisooloisti.pattern.ChordDown;
import midisooloisti.pattern.ChordUp;
import midisooloisti.pattern.Figure3123;
import midisooloisti.pattern.Linear;
import midisooloisti.pattern.Pattern;
import midisooloisti.pattern.Scale;
import midisooloisti.pattern.TwoOctavesDown;
import midisooloisti.player.MidiNote;
import midisooloisti.player.Player;

public class SoloLogic implements Runnable {

    private static final int[] minor = {0, 2, 3, 5, 7, 8, 11, 12, 14, 15, 17};
    private static final int[] major = {0, 2, 4, 5, 7, 9, 11, 12, 14, 16, 17};

    private Random random;
    private Timer timer;
    private Player player;
    private ActionListener timerListener;
    private int tick;
    private ArrayList<MidiNote> notes;
    private Scale scale;
    private ArrayList<Pattern> patterns;
    private int lowerLimit;
    private int upperLimit;
    private int currentNote;
    private int notesI[];
    private int notesIV[];
    private int notesV[];
    private int transpose;
    private int degree;
    private boolean inMajor;
    private int pLongNotes;

    public SoloLogic(int delay) {
        this.random = new Random();
        this.player = new Player();
        this.tick = 0;
        this.lowerLimit = 50;   // default value
        this.upperLimit = 90;   // default value
        this.currentNote = 64;  // default value
        this.transpose = 0;     // default value
        this.degree = 1;        // default value
        this.inMajor = true;    // default value
        this.pLongNotes = 0;   // default value
        this.patterns = new ArrayList<>();
        this.notesI = new int[8];
        this.notesIV = new int[8];
        this.notesV = new int[8];

        this.timerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tick == 16) {
                    player.forward();
                    tick = 0;
                }

                if (tick == 0) {
                    notes = getNotes();
                    currentNote = notes.get(notes.size() - 1).getPitch();
                    player.setNotes(notes);
                    player.begin();
                } else {
                    player.forward();
                }
                tick++;
            }
        };

        this.timer = new Timer(delay, this.timerListener);
    }

    @Override
    public void run() {
        player.setSound(1, 81); // Default (Saw Lead)
        this.timer.start();
    }

    public void setLowerLimit(int value) {
        this.lowerLimit = value;
    }

    public void setUpperLimit(int value) {
        this.upperLimit = value;
    }

    public void useMajorScale() {
        this.inMajor = true;
        for (int i = 0; i < 7; i++) {
            this.notesI[i] = major[i] + this.transpose;
            this.notesIV[i] = major[i + 3] + this.transpose;
            this.notesV[i] = major[i + 4] + this.transpose;
        }
        this.setScaleDegree(this.degree);
    }

    public void useMinorScale() {
        this.inMajor = false;
        for (int i = 0; i < 7; i++) {
            this.notesI[i] = minor[i] + this.transpose;
            this.notesIV[i] = minor[i + 3] + this.transpose;
            this.notesV[i] = minor[i + 4] + this.transpose;
        }
        this.setScaleDegree(this.degree);
    }

    public void transpose(int transpose) {
        this.transpose = transpose;
        if (inMajor) {
            this.useMajorScale();
        } else {
            this.useMinorScale();
        }
    }

    public void setScaleDegree(int degree) {
        this.degree = degree;
        switch (degree) {
            case 1:
                this.scale = new Scale(this.lowerLimit, this.upperLimit, this.notesI);
                break;
            case 4:
                this.scale = new Scale(this.lowerLimit, this.upperLimit, this.notesIV);
                break;
            case 5:
                this.scale = new Scale(this.lowerLimit, this.upperLimit, this.notesV);
                break;
            default:
                this.scale = new Scale(this.lowerLimit, this.upperLimit, this.notesI);
        }
    }

    public void setPatterns() {
        Pattern pattern = new Linear(this.random);
        Pattern chordUp = new ChordUp(random);
        Pattern chordDown = new ChordDown(random);
        Pattern figure3123 = new Figure3123(random);
        Pattern twoOctavesDown = new TwoOctavesDown(random);
        Pattern bach = new Bach(random);
        this.patterns.add(pattern);
        this.patterns.add(chordUp);
        this.patterns.add(chordDown);
        this.patterns.add(figure3123);
        this.patterns.add(twoOctavesDown);
        this.patterns.add(bach);
    }

    public void setDelay(int delay) {
        this.timer.setDelay(delay);
    }

    public void setpLongNotes(int pLongNotes) {
        this.pLongNotes = pLongNotes;
    }

    public void stop() {
        this.tick = 0;
        timer.stop();
        player.stop();
    }

    private ArrayList<MidiNote> getNotes() {
        int index = random.nextInt(patterns.size());
        ArrayList<MidiNote> noteList = patterns.get(index).getNotes(this.scale, currentNote);
        int longNotes = 0;
        for (int i = 0; i < (noteList.size() - 1); i++) {
            if (((i + longNotes) % 2 == 0) && this.random.nextInt(99) > (100 - this.pLongNotes)) {
                noteList.get(i).setLength(2);
                noteList.remove(i + 1);
                longNotes++;
            }
        }
        return noteList;
    }

}
