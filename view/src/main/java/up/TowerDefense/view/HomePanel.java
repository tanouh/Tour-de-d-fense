package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    public HomePanel(GameWindow gameWindow){
        this.setLayout(new GridLayout());
        JButton lancementPartie = new JButton();
        JLabel demarrer = new JLabel("Demarrer");
        lancementPartie.add(demarrer);
        this.add(lancementPartie);
        lancementPartie.addActionListener(event -> {
            GamePanel gamePanel = new GamePanel();
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(gamePanel);
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
        });
    }
}
