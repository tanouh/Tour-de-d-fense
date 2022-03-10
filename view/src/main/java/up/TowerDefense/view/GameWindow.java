package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = new Dimension(screenSize.width, screenSize.height);
        this.setMinimumSize(windowSize);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);

        HomePanel homePanel = new HomePanel(this);
        this.getContentPane().add(homePanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
