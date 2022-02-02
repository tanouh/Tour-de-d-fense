package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(screenSize.width/2, screenSize.height/2));

        HomePanel homePanel = new HomePanel(this);
        this.getContentPane().add(homePanel);
        this.pack();

        this.setVisible(true);
    }
}
