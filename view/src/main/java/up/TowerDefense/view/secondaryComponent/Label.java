package up.TowerDefense.view.secondaryComponent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Label extends JLabel {
    private static Color background = new Color(173,175,192);
    private static Color foreground = new Color(30,35,71);


    public static void addHeaderLabel(JPanel header, String text){
        JLabel label = new JLabel(text);
        header.add(label);
        label.setBorder(new LineBorder(foreground, 2));
        label.setForeground(foreground);
        label.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 16));
        label.setHorizontalAlignment(JLabel.CENTER);
    }
}
