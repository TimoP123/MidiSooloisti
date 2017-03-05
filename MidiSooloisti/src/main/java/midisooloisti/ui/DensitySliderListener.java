package midisooloisti.ui;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import midisooloisti.logic.SoloLogic;

/**
 * Luokan tehtävänä on kuunnella nuottitiheys-sliderin muutoksia ja
 * välittää tieto niistä soolologiikalle.
 */
public class DensitySliderListener implements ChangeListener {

    private SoloLogic logic;
    private JLabel valueLabel;
    private JSlider slider;

    public DensitySliderListener(SoloLogic logic, JLabel valueLabel, JSlider slider) {
        this.logic = logic;
        this.valueLabel = valueLabel;
        this.slider = slider;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (!slider.getValueIsAdjusting()) {
            this.logic.setPLongNotes(100 - slider.getValue());
        }
        this.valueLabel.setText("" + slider.getValue());
    }

}
