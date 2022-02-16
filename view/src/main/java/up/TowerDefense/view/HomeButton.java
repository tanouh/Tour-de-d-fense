package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;

public class HomeButton extends JButton {
    public HomeButton(){
        super();
    }

    public void startButton(GameWindow gameWindow){
        JLabel demarrer = new JLabel("Demarrer");
        demarrer.setForeground(new Color(173,175,192));
        this.add(demarrer);
        this.setBackground(new Color(30,35,71));
        this.setPreferredSize(new Dimension(200, gameWindow.getHeight()/10));
        this.addActionListener(event -> {
            GamePanel gamePanel = new GamePanel("/map2.png");
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(gamePanel);
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
        });
    }

    public void leaveButton(int gameWindowWidth, int gameWindowHeight){
        JLabel quitter = new JLabel("Quitter");
        quitter.setForeground(new Color(173,175,192));
        this.add(quitter);
        this.setBackground(new Color(30,35,71));
        this.setPreferredSize(new Dimension(gameWindowWidth,gameWindowHeight/10));
        this.addActionListener(event -> System.exit(0));
    }

    public void optionButton(GameWindow gameWindow){
        JLabel options = new JLabel("Options");
        options.setForeground(new Color(173,175,192));
        this.add(options);
        this.setBackground(new Color(30,35,71));
        this.addActionListener(event -> {
            OptionPanel optionPanel = new OptionPanel();
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(optionPanel);
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
        });
    }
}
