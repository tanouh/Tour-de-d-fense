package up.TowerDefense.view.mainComponent;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private Color background = new Color(173,175,192);
    private Color foreground = new Color(30,35,71);

    public InfoPanel(int currentInfo){
        this.setBackground(background);
        this.setForeground(foreground);
        JLabel infoDisplay;
        switch (currentInfo){
            case 1 :
                infoDisplay = new JLabel("Information sur les TowerTest");
                break;
            case 2 :
                infoDisplay = new JLabel("Information sur les Tours anti-champis");
                break;
            case 3 :
                infoDisplay = new JLabel("Information sur les Leucocyte T");
                break;
            case 4 :
                infoDisplay = new JLabel("Information sur les Anticorps");
                break;
            case 5 :
                infoDisplay = new JLabel("Information sur les Murs");
                break;
            default :
                infoDisplay = new JLabel("erreur");
                break;
        }
        infoDisplay.setForeground(foreground);
        infoDisplay.setFont(new Font("Bernard MT Condensed",Font.PLAIN, 20));
        this.add(infoDisplay);
    }
}
