package up.TowerDefense.view.secondaryComponent;

import up.TowerDefense.view.mainComponent.GameWindow;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Label extends JLabel {

    /**
     * Affiche une certaine statistique dans une case dans le header
     */
    public static JLabel addHeaderLabel(JPanel header, String text){
        JLabel label = new JLabel(text);
        header.add(label);
        label.setBorder(new LineBorder(GameWindow.foreground, 2));
        label.setForeground(GameWindow.foreground);
        label.setFont(new Font(GameWindow.font, Font.PLAIN, GameWindow.widthScreen/70));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }
}
