
package midisooloisti.ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;


public class TempoLight extends JComponent {
    
    private Color color;
    private boolean on;
    
    public TempoLight() {
        this.color = Color.RED;
        this.on = true;
    }
    
    public void toggle() {
        if(this.on) {
            this.color = Color.BLACK;
            this.on = false;
        } else {
            this.color = Color.RED;
            this.on = true;
        }
        this.repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillOval(this.getWidth() / 4, this.getHeight() / 4, this.getWidth() / 2, this.getHeight() / 2);
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(this.getWidth() / 4 + 2, this.getHeight() / 4 + 2, this.getWidth() / 2 - 4, this.getHeight() / 2 - 4);
        g.setColor(this.color);
        g.fillOval(this.getWidth() / 4 + 4, this.getHeight() / 4 + 4, this.getWidth() / 2 - 8, this.getHeight() / 2 - 8);    
    }
    
    
    
}
