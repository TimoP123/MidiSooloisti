package midisooloisti.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import midisooloisti.logic.SoloLogic;

/**
 * Luokan tehtävänä on kuunnella duuri- ja mollinappuloiden painalluksia
 * sekä välittää tieto niistä soolologiikalle.
 */
public class ScaleButtonListener implements ActionListener {

    private SoloLogic logic;
    private JRadioButton major;
    private JRadioButton minor;
    private JLabel key;

    public ScaleButtonListener(SoloLogic logic, JRadioButton major, JRadioButton minor, JLabel key) {
        this.logic = logic;
        this.major = major;
        this.minor = minor;
        this.key = key;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String keyText = key.getText();
        if (keyText.charAt(keyText.length() - 1) == 'm') {
            keyText = keyText.substring(0, (keyText.length() - 1));
        }
        if (e.getSource() == major) {
            this.logic.useMajorScale();
        } else {
            this.logic.useMinorScale();
            keyText += "m";
        }
        this.key.setText(keyText);
    }

}
