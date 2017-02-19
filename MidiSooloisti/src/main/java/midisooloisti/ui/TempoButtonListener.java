
package midisooloisti.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import midisooloisti.logic.SoloLogic;

public class TempoButtonListener implements ActionListener {
    
    private SoloLogic logic;
    private JLabel tempoValueLabel;
    private JButton tempoDown;
    private JButton tempoUp;
    private int tempo;

    public TempoButtonListener(SoloLogic logic, JLabel tempoValueLabel, JButton tempoDown, JButton tempoUp, int tempo) {
        this.logic = logic;
        this.tempoValueLabel = tempoValueLabel;
        this.tempoDown = tempoDown;
        this.tempoUp = tempoUp;
        this.tempo = tempo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tempoDown) {
            tempo--;
        } else {
            tempo++;
        }
        if(tempo < 40) {
            tempo = 40;
        } else if (tempo > 300) {
            tempo = 300;
        }
        int delay = 60000 / (4 * tempo);
        this.logic.setDelay(delay);
        this.tempoValueLabel.setText("" + tempo);
        
    }
    
    
    
    
}
