package up.TowerDefense.view;

import up.TowerDefense.model.game.Game;

import javax.swing.*;
import javax.swing.border.LineBorder;
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

    public void optionButton(GameWindow gameWindow, HomePanel homePanel, GamePanel gamePanel, JPanel returnPanel){
        JLabel options = new JLabel("Options", JLabel.CENTER);
        options.setForeground(background);
        options.setFont(new Font("Bernard MT Condensed",Font.PLAIN, 20));
        this.add(options);
        this.setBackground(foreground);
        this.addActionListener(event -> {
            OptionPanel optionPanel = new OptionPanel(gameWindow, homePanel, gamePanel, returnPanel);
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(optionPanel);
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
        });
    }

    public void applyButtonInHome(GameWindow gameWindow, OptionPanel optionPanel, HomePanel homePanel){
        JLabel apply = new JLabel("Valider");
        this.setBackground(foreground);
        apply.setForeground(background);
        this.add(apply);
        this.addActionListener(event -> {
            int numberWaves = optionPanel.getNumberWaves().getValue();
            int backgroundMusic = optionPanel.getBackgroundMusic().getValue();
            int gameSound = optionPanel.getGameSound().getValue();
            int gameSpeed = optionPanel.getGameSpeed().getValue();
            homePanel.applyOptions(numberWaves, backgroundMusic, gameSound, gameSpeed);
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(homePanel);
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
        });
    }

    public void applyButtonInGame(GameWindow gameWindow, OptionPanel optionPanel, GamePanel gamePanel){
        JLabel apply = new JLabel("Valider");
        this.setBackground(foreground);
        apply.setForeground(background);
        this.add(apply);
        this.addActionListener(event -> {
            int backgroundMusic = optionPanel.getBackgroundMusic().getValue();
            int gameSound = optionPanel.getGameSound().getValue();
            int gameSpeed = optionPanel.getGameSpeed().getValue();
            gamePanel.applyOptions(backgroundMusic, gameSound, gameSpeed);
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(gamePanel);
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
        });
    }

    public void sideMenuButton(String type){
        JLabel typeTower = new JLabel(type);
        typeTower.setForeground(foreground);
        typeTower.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 16));
        typeTower.setHorizontalAlignment(JLabel.CENTER);
        this.setBorder(new LineBorder(foreground, 2));
        this.setHorizontalAlignment(JButton.CENTER);
        this.add(typeTower);
        this.setBackground(background);
        this.setForeground(foreground);
        this.addActionListener(event -> {

        });
    }
}
