package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = new Dimension(screenSize.width, screenSize.height*24/25);
        this.setMinimumSize(windowSize);
        this.setLocationRelativeTo(null);

        HomePanel homePanel = new HomePanel(this);
        this.getContentPane().add(homePanel);
        this.pack();

        this.setVisible(true);
    }
}
