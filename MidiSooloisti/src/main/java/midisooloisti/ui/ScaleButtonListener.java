package midisooloisti.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;
import midisooloisti.logic.SoloLogic;

public class ScaleButtonListener implements ActionListener {

    private SoloLogic logic;
    private JRadioButton major;
    private JRadioButton minor;

    public ScaleButtonListener(SoloLogic logic, JRadioButton major, JRadioButton minor) {
        this.logic = logic;
        this.major = major;
        this.minor = minor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == major) {
            this.logic.useMajorScale();
        } else {
            this.logic.useMinorScale();
        }
    }

}
