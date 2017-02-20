package midisooloisti;

import java.util.Scanner;
import javax.swing.SwingUtilities;
import midisooloisti.logic.SoloLogic;
import midisooloisti.ui.GraphicalInterface;

/**
 * Main-luokan tehtävänä on käynnistää sovelluksen logiikka ja käyttöliittymä.
 * Logiikka ei tarvitse toimiakseen käyttöliittymää, joten se voidaan käynnistää
 * ensin.
 */
public class Main {

    public static void main(String[] args) {
        int tempo = 140;
        SoloLogic logic = new SoloLogic(60000 / (4 * tempo));
        logic.useMajorScale();
        logic.setPatterns();
        SwingUtilities.invokeLater(logic);

        GraphicalInterface ui = new GraphicalInterface(logic, tempo);
        SwingUtilities.invokeLater(ui);
    }
}
