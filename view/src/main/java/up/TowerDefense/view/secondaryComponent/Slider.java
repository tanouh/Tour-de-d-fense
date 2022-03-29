package up.TowerDefense.view.secondaryComponent;

import up.TowerDefense.view.mainComponent.GameWindow;

import javax.swing.*;
import java.awt.*;

public class Slider extends JSlider {

    /**
     * Cree un slider text allant de minValue a maxValue et l'affiche dans container
     */
    public Slider(JPanel container, String text, int minValue, int maxValue){
        super();
        JLabel label = new JLabel(text);
        label.setFont(new Font(GameWindow.font, Font.PLAIN, GameWindow.widthScreen/60));
        label.setForeground(GameWindow.foreground);
        container.add(label);
        this.setBackground(GameWindow.background);
        this.setForeground(GameWindow.foreground);
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
