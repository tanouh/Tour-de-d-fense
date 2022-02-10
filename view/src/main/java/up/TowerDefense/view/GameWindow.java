package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(new Dimension(screenSize.width, screenSize.height));
        this.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        this.setLocationRelativeTo(null);

        /*HomePanel homePanel = new HomePanel(this);
        this.getContentPane().add(homePanel);
        this.pack();
        this.setVisible(true);*/

        GamePanel gamePanel= new GamePanel();
        this.add(gamePanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        gamePanel.startThread();
    }
}
