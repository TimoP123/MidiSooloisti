package midisooloisti.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import midisooloisti.logic.SoloLogic;

/**
 * Luokan tehtävänä on kuunnella tap-tempo nappulan painalluksia ja
 * asettaa soolon tempo painallusten mukaan.
 */
public class TapTempoListener implements ActionListener {

    private SoloLogic logic;
    private long latestPress;
    private JLabel tempoValueLabel;
    private JButton tapTempo;
    private GraphicalInterface ui;

    public TapTempoListener(SoloLogic logic, JLabel tempoValueLabel, JButton tapTempo, GraphicalInterface ui) {
        this.logic = logic;
        this.latestPress = System.currentTimeMillis();
        this.tempoValueLabel = tempoValueLabel;
        this.tapTempo = tapTempo;
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long press = System.currentTimeMillis();
        int quarterNoteLength = (int) (press - this.latestPress);
        int tempo = 60000 / quarterNoteLength;
        if (tempo >= 40 && tempo <= 300) {
            this.tempoValueLabel.setText("" + tempo);
            this.logic.setDelay(quarterNoteLength / 4); // 1/4-note divided by four is 1/16-note.
            this.ui.setTempo(tempo);
        }
        this.latestPress = press;
    }

}
