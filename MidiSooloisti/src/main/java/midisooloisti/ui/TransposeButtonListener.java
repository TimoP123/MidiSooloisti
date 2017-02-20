package midisooloisti.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import midisooloisti.logic.SoloLogic;

public class TransposeButtonListener implements ActionListener {

    private SoloLogic logic;
    private JButton transposeDown;
    private JButton transposeUp;
    private JLabel key;

    public TransposeButtonListener(SoloLogic logic, JButton transposeDown, JButton transposeUp, JLabel key) {
        this.logic = logic;
        this.transposeDown = transposeDown;
        this.transposeUp = transposeUp;
        this.key = key;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int transpose = this.logic.getTranspose();
        if (e.getSource() == transposeDown) {
            transpose--;
            if (transpose < 0) {
                transpose = 11;
            }
        } else {
            transpose++;
            if (transpose > 11) {
                transpose = 0;
            }
        }
        this.logic.transpose(transpose);
        String keyText = this.key(transpose);

        if (!this.logic.isInMajor()) {
            keyText += "m";
        }

        this.key.setText(keyText);
    }

    private String key(int transpose) {
        String key = "";
        switch (transpose) {
            case 0:
                key = "C";
                break;
            case 1:
                key = "Db";
                break;
            case 2:
                key = "D";
                break;
            case 3:
                key = "Eb";
                break;
            case 4:
                key = "E";
                break;
            case 5:
                key = "F";
                break;
            case 6:
                key = "F#";
                break;
            case 7:
                key = "G";
                break;
            case 8:
                key = "Ab";
                break;
            case 9:
                key = "A";
                break;
            case 10:
                key = "Bb";
                break;
            case 11:
                key = "B";
                break;
            default:
                key = "C";
        }
        return key;
    }

}
