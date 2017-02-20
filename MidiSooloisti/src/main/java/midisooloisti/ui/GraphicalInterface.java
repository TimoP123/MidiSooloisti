package midisooloisti.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import midisooloisti.logic.SoloLogic;

public class GraphicalInterface implements Runnable {

    private JFrame frame;
    private Container container;
    private SoloLogic logic;
    private int tempo;

    public GraphicalInterface(SoloLogic logic, int tempo) {
        this.logic = logic;
        this.tempo = tempo;
    }

    @Override
    public void run() {
        frame = new JFrame("MidiSooloisti");
        frame.setPreferredSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        container = frame.getContentPane();

        createComponents(container);

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        GridLayout layout = new GridLayout(3, 3);
        layout.setHgap(4);
        layout.setVgap(4);
        container.setLayout(layout);

        // Name
        JLabel name = new JLabel("MidiSooloisti");
        name.setFont(new Font("Consolas", Font.BOLD, 24));
        name.setForeground(Color.BLUE);
        name.setHorizontalAlignment(SwingConstants.CENTER);

        // Empty label
        JLabel empty = new JLabel("");

        // ExitButton
        JButton exit = new JButton("l o p e t a");
        exit.setFont(new Font("Consolas", Font.BOLD, 18));
        exit.setForeground(Color.white);
        exit.setBackground(Color.darkGray);
        ExitButtonListener exitListener = new ExitButtonListener();
        exit.addActionListener(exitListener);

        // Solo on/off button
        JButton onOff = new JButton();
        onOff.setText("PYSÄYTÄ");
        onOff.setFont(new Font("Consolas", Font.BOLD, 20));
        OnOffButtonListener onOffListener = new OnOffButtonListener(this.logic, onOff);
        onOff.addActionListener(onOffListener);

        // Transpose panel
        // Label for the area
        JLabel transposeLabel = new JLabel("Sävellaji");
        transposeLabel.setFont(new Font("Consolas", Font.BOLD, 18));
        transposeLabel.setAlignmentX(0.5f);

        // Label for the key.
        JLabel key = new JLabel("C");
        key.setFont(new Font("Consolas", Font.BOLD, 30));
        key.setAlignmentX(0.5f);

        // Panel for transpose buttons
        JPanel transpose = new JPanel(new GridLayout(1, 2));
        JButton transposeDown = new JButton("-");
        transposeDown.setFont(new Font("Consolas", Font.BOLD, 26));
        JButton transposeUp = new JButton("+");
        transposeUp.setFont(new Font("Consolas", Font.BOLD, 26));
        transpose.add(transposeDown);
        transpose.add(transposeUp);
        // ActionListener for transpose buttons
        TransposeButtonListener transposeListener = new TransposeButtonListener(this.logic, transposeDown, transposeUp, key);
        transposeDown.addActionListener(transposeListener);
        transposeUp.addActionListener(transposeListener);

        JPanel transposeArea = new JPanel();
        transposeArea.setLayout(new BoxLayout(transposeArea, BoxLayout.Y_AXIS));
        transposeArea.add(transposeLabel);
        transposeArea.add(key);
        transposeArea.add(transpose);

        // Scale selection area
        // Label
        JLabel scaleLabel = new JLabel("Asteikko");
        scaleLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        scaleLabel.setAlignmentX(0.5f);
        // Button group
        JRadioButton major = new JRadioButton("Duuri");
        major.setFont(new Font("Consolas", Font.BOLD, 16));
        major.setAlignmentX(0.5f);
        JRadioButton minor = new JRadioButton("Molli");
        minor.setFont(new Font("Consolas", Font.BOLD, 16));
        minor.setAlignmentX(0.58f);
        ButtonGroup scaleButtons = new ButtonGroup();
        scaleButtons.add(major);
        scaleButtons.add(minor);
        major.setSelected(true);
        // Action Listener
        ScaleButtonListener scaleListener = new ScaleButtonListener(this.logic, major, minor, key);
        major.addActionListener(scaleListener);
        minor.addActionListener(scaleListener);
        // Panel
        JPanel scaleArea = new JPanel();
        scaleArea.setLayout(new BoxLayout(scaleArea, BoxLayout.Y_AXIS));
        scaleArea.add(scaleLabel);
        scaleArea.add(major);
        scaleArea.add(minor);

        // Scale Degree selection area
        // Label
        JLabel degreeAreaLabel = new JLabel("Sointuaste");
        degreeAreaLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        degreeAreaLabel.setAlignmentX(0.5f);
        // Label for the degree
        JLabel degreeLabel = new JLabel("I");
        degreeLabel.setFont(new Font("Caladea", Font.BOLD, 30));
        degreeLabel.setAlignmentX(0.5f);
        // Panel for transpose buttons
        JPanel degree = new JPanel(new GridLayout(1, 3));
        JButton degreeI = new JButton("I");
        degreeI.setFont(new Font("Caladea", Font.BOLD, 26));
        JButton degreeIV = new JButton("IV");
        degreeIV.setFont(new Font("Caladea", Font.BOLD, 26));
        JButton degreeV = new JButton("V");
        degreeV.setFont(new Font("Caladea", Font.BOLD, 26));
        degree.add(degreeI);
        degree.add(degreeIV);
        degree.add(degreeV);
        // Action Listener
        ScaleDegreeListener degreeListener = new ScaleDegreeListener(logic, degreeLabel, degreeI, degreeIV, degreeV);
        degreeI.addActionListener(degreeListener);
        degreeIV.addActionListener(degreeListener);
        degreeV.addActionListener(degreeListener);
        // Panel
        JPanel degreeArea = new JPanel();
        degreeArea.setLayout(new BoxLayout(degreeArea, BoxLayout.Y_AXIS));
        degreeArea.add(degreeAreaLabel);
        degreeArea.add(degreeLabel);
        degreeArea.add(degree);

        // Tempo Area
        // TempoAreaLabel
        JLabel tempoAreaLabel = new JLabel("Tempo");
        tempoAreaLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        tempoAreaLabel.setAlignmentX(0.5f);
        // TempoAreaLabel
        JLabel tempoValueLabel = new JLabel("" + tempo);
        tempoValueLabel.setFont(new Font("Consolas", Font.BOLD, 26));
        tempoValueLabel.setAlignmentX(0.5f);
        // Panel for tempo up and down buttons
        JPanel tempoSet = new JPanel(new GridLayout(1, 2));
        JButton tempoDown = new JButton("-");
        tempoDown.setFont(new Font("Caladea", Font.BOLD, 26));
        JButton tempoUp = new JButton("+");
        tempoUp.setFont(new Font("Caladea", Font.BOLD, 26));
        tempoSet.add(tempoDown);
        tempoSet.add(tempoUp);
        // ActionListener
        TempoButtonListener tempoListener = new TempoButtonListener(this.logic, tempoValueLabel, tempoDown, tempoUp, this);
        tempoDown.addActionListener(tempoListener);
        tempoUp.addActionListener(tempoListener);

        // Panel
        JPanel tempoArea = new JPanel();
        tempoArea.setLayout(new BoxLayout(tempoArea, BoxLayout.Y_AXIS));
        tempoArea.add(tempoAreaLabel);
        tempoArea.add(tempoValueLabel);
        tempoArea.add(tempoSet);

        // TapTempo Button
        JButton tapTempo = new JButton("Tap tempo");
        tapTempo.setFont(new Font("Caladea", Font.BOLD, 22));
        // ActionListener
        TapTempoListener tapListener = new TapTempoListener(logic, tempoValueLabel, tapTempo, this);
        tapTempo.addActionListener(tapListener);

        // Note density
        // Label
        JLabel densityLabel = new JLabel("Nuottitiheys");
        densityLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        densityLabel.setAlignmentX(0.5f);
        // Value label
        JLabel densityValue = new JLabel("100");
        densityValue.setFont(new Font("Consolas", Font.BOLD, 16));
        densityValue.setAlignmentX(0.5f);
        // Slider
        JSlider densitySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        // Change Listener
        DensitySliderListener densityListener = new DensitySliderListener(logic, densityValue, densitySlider);
        densitySlider.addChangeListener(densityListener);

        JPanel densityArea = new JPanel();
        densityArea.setLayout(new BoxLayout(densityArea, BoxLayout.Y_AXIS));
        densityArea.add(densityLabel);
        densityArea.add(densityValue);
        densityArea.add(densitySlider);

        // Add all elements to GridLayout
        container.add(name);
        container.add(scaleArea);
        container.add(densityArea);

        container.add(tempoArea);
        container.add(transposeArea);
        container.add(degreeArea);

        container.add(tapTempo);
        container.add(onOff);
        container.add(exit);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getTempo() {
        return tempo;
    }

}
