package up.TowerDefense.view.secondaryComponent;

import javax.swing.*;
import java.awt.*;

public class Slider extends JSlider {
    private Color background = new Color(173,175,192);
    private Color foreground = new Color(30,35,71);

    public Slider(JPanel container, String text, int minValue, int maxValue){
        super();
        JLabel label = new JLabel(text);
        label.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 20));
        label.setForeground(foreground);
        container.add(label);
        this.setBackground(background);
        this.setForeground(foreground);
        this.setMinimum(minValue);
        this.setMaximum(maxValue);
        this.setAlignmentX(JSlider.CENTER);
        this.setMajorTickSpacing(1);
        this.setPaintTicks(true);
        this.setPaintLabels(true);
        this.setPaintTrack(true);

        container.add(this);

        container.add(Box.createVerticalGlue());

    }
}
