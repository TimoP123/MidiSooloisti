package midisooloisti.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import midisooloisti.logic.SoloLogic;

public class OnOffButtonListener implements ActionListener {

    private SoloLogic logic;
    private JButton onOff;

    public OnOffButtonListener(SoloLogic logic, JButton onOff) {
        this.logic = logic;
        this.onOff = onOff;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.logic.toggleOnOff()) {
            this.onOff.setText("PYSÄYTÄ");
        } else {
            this.onOff.setText("SOITA");
        }
    }

}
