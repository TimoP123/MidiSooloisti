package midisooloisti.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import midisooloisti.logic.SoloLogic;

/**
 * Luokan tehtävänä on kuunnella sointuastetta vaihtavien nappuloiden 
 * painalluksia ja välittää tieto niistä soologiikalle.
 */
public class ScaleDegreeListener implements ActionListener {

    private SoloLogic logic;
    private JLabel degreeLabel;
    private JButton degreeI;
    private JButton degreeIV;
    private JButton degreeV;

    public ScaleDegreeListener(SoloLogic logic, JLabel degreeLabel, JButton degreeI, JButton degreeIV, JButton degreeV) {
        this.logic = logic;
        this.degreeLabel = degreeLabel;
        this.degreeI = degreeI;
        this.degreeIV = degreeIV;
        this.degreeV = degreeV;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == degreeI) {
            logic.setScaleDegree(1);
            degreeLabel.setText("I");
        } else if (e.getSource() == degreeIV) {
            logic.setScaleDegree(4);
            degreeLabel.setText("IV");
        } else {
            logic.setScaleDegree(5);
            degreeLabel.setText("V");
        }
    }

}
