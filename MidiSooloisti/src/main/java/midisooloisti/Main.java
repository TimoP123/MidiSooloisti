package midisooloisti;

import java.util.Scanner;
import javax.swing.SwingUtilities;
import midisooloisti.logic.SoloLogic;

/**
 * Main-luokan tehtävänä on käynnistää sovelluksen käyttöliittymä.
 * Kehitysvaiheessa Main-luokka sisältää kuitenkin vaihtelevan määrän
 * testikoodia.
 */
public class Main {

    public static void main(String[] args) {

        /*
         *  Test code here until the graphical interface is implemented.
         */
        Scanner scanner = new Scanner(System.in);
        SoloLogic logic = new SoloLogic(90);
        logic.useMajorScale();
        logic.setPatterns();
        SwingUtilities.invokeLater(logic);

        System.out.println("Welcome to the MidiSooloisti!");
        System.out.println("This is a temporary text interface.");
        System.out.println("stop/run commands control solo on/off.");
        System.out.println("x exits the program.");
        System.out.println("minor and major change the scale used.");
        System.out.println("With numbers 0 - 11 you can transpose the solo.");
        System.out.println("I, IV and V change the scale degree.");
        System.out.println("With numbers 12 - 500 you can change the duration of a note in ms.");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("x")) {
                logic.stop();
                break;
            } else if (input.equals("stop")) {
                logic.stop();
            } else if (input.equals("run")) {
                logic.run();
            } else if (input.equals("I")) {
                logic.setScaleDegree(1);
            } else if (input.equals("IV")) {
                logic.setScaleDegree(4);
            } else if (input.equals("V")) {
                logic.setScaleDegree(5);
            } else if (input.equals("major")) {
                logic.useMajorScale();
            } else if (input.equals("minor")) {
                logic.useMinorScale();
            } else {
                try {
                    int value = Integer.parseInt(input);

                    if (value < 0) {
                        System.out.println("No, kidding..");
                    } else if (value < 12) {
                        logic.transpose(value);
                    } else if (value <= 500) {
                        logic.setDelay(value);
                    } else {
                        System.out.println("I'd rather sleep than play so slowly.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Unknown command, try again. 'x' to exit from program.");
                }

            }
        }
    }

}
