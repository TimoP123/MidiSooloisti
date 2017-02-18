package midisooloisti.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import midisooloisti.logic.SoloLogic;

public class GraphicalInterface implements Runnable {
    
    private JFrame frame;
    private Container container;
    private SoloLogic logic;
    
    public GraphicalInterface(SoloLogic logic) {
        this.logic = logic;
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
        name.setFont(new Font("Consolas", Font.BOLD, 22));
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
        transposeLabel.setBackground(Color.BLUE);
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
        
        
        
        container.add(name);
        container.add(empty);
        container.add(onOff);
        
        container.add(transposeArea);
        container.add(new JButton("Moi"));
        container.add(new JButton("Moi"));
        
        container.add(new JButton("Moi"));
        container.add(new JButton("Moi"));
        container.add(exit);
    }
    
    public JFrame getFrame() {
        return frame;
    }
}