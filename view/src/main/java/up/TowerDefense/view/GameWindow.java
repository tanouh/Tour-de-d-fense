package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = new Dimension(screenSize.width*2/3, screenSize.height*2/3);
        this.setSize(windowSize);
        //this.setPreferredSize(windowSize);
        this.setMinimumSize(windowSize);
        this.setLocationRelativeTo(null);

        HomePanel homePanel = new HomePanel(this);
        this.getContentPane().add(homePanel);
        this.pack();

        this.setVisible(true);
    }
}
