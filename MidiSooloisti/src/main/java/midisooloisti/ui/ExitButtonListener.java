package midisooloisti.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Luokan tehtävänä on kuunnella exit-nappulan painallusta ja sen tapahtuessa
 * sammuttaa ohjelma.
 */
public class ExitButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    }

}
