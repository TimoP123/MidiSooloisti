
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
    
    private Random random;
    private Timer timer;
    private Player player;
    private ActionListener timerListener;
    private int tick;
    private ArrayList<MidiNote> notes;
    private Scale scale;
    private ArrayList<Pattern> patterns;
    private int currentNote;

    public SoloLogic(int delay) {
        this.random = new Random();
        this.player = new Player();
        this.tick = 0;
        this.currentNote = 64;
        this.patterns = new ArrayList<>();
        
        this.timerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tick == 16) {
                    player.forward();
                    tick = 0;
                }
                
                if(tick == 0) {
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
        player.setSound(1, 81);
        this.timer.start();
    }
    
    public void setScale(int lower, int upper, int[] notes) {
        this.scale = new Scale(lower, upper, notes);
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
    
    public void stop() {
        this.tick = 0;
        timer.stop();
        player.stop();
    }
    
    private ArrayList<MidiNote> getNotes() {
        int index = random.nextInt(patterns.size());
        return patterns.get(index).getNotes(scale, currentNote);
    }
    
}
