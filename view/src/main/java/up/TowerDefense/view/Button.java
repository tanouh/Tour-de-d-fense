package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    private Color background = new Color(173,175,192);
    private Color foreground = new Color(30,35,71);

    public Button(){
        super();
    }

    public void startButton(GameWindow gameWindow){
        JLabel start = new JLabel("Demarrer", JLabel.CENTER);
        start.setForeground(background);
        start.setFont(new Font("Bernard MT Condensed",Font.PLAIN, 20));
        this.add(start);
        this.setBackground(foreground);
        this.setPreferredSize(new Dimension(200, gameWindow.getHeight()/10));
        this.addActionListener(event -> {
            GamePanel gamePanel = new GamePanel(gameWindow);
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(gamePanel);
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
        });
    }

    public void leaveButton(int gameWindowWidth, int gameWindowHeight){
        JLabel leave = new JLabel("Quitter", JLabel.CENTER);
        leave.setFont(new Font("Bernard MT Condensed",Font.PLAIN, 20));
        leave.setForeground(background);
        this.add(leave);
        this.setBackground(foreground);
        this.setPreferredSize(new Dimension(gameWindowWidth,gameWindowHeight/10));
        this.addActionListener(event -> System.exit(0));
    }

    public void optionButton(GameWindow gameWindow){
        JLabel options = new JLabel("Options", JLabel.CENTER);
        options.setForeground(background);
        options.setFont(new Font("Bernard MT Condensed",Font.PLAIN, 20));
        this.add(options);
        this.setBackground(foreground);
        this.addActionListener(event -> {
            OptionPanel optionPanel = new OptionPanel(gameWindow);
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(optionPanel);
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
        });
    }

    public void sideMenuButton(String type){
        JLabel typeTower = new JLabel(type);
        typeTower.setForeground(foreground);
        typeTower.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 16));
        typeTower.setHorizontalAlignment(JLabel.CENTER);
        this.setHorizontalAlignment(JButton.CENTER);
        this.add(typeTower);
        this.setBackground(background);
        this.setForeground(foreground);
    }
}
