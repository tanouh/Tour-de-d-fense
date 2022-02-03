package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(new Dimension(screenSize.width*2/3, screenSize.height*2/3));
        this.setPreferredSize(new Dimension(screenSize.width*2/3, screenSize.height*2/3));
        this.setLocationRelativeTo(null);

        HomePanel homePanel = new HomePanel(this);
        this.getContentPane().add(homePanel);
        this.pack();

        this.setVisible(true);
    }
}
