package up.TowerDefense.view.secondaryComponent;

import up.TowerDefense.model.game.Game;
import up.TowerDefense.model.object.TowerTest;
import up.TowerDefense.view.mainComponent.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Optional;

public class Button extends JButton {
    private Color background = new Color(173,175,192);
    private Color foreground = new Color(30,35,71);

    public Button(){
        super();
    }

    public void startButton(GameWindow gameWindow, JPanel callPanel, int numberWaves, int backgroundMusic,
                            int gameSound, int gameSpeed){
        JLabel start = new JLabel("Demarrer", JLabel.CENTER);
        start.setForeground(background);
        start.setFont(new Font("Bernard MT Condensed",Font.PLAIN, 20));
        this.add(start);
        JPanel nextScreen;
        if (callPanel instanceof HomePanel){
            this.setBackground(foreground);
            nextScreen = new WaitingScreen(gameWindow, numberWaves,
                    backgroundMusic, gameSound, gameSpeed);
        }
        else {
            this.setBackground(Color.GRAY);
            this.setEnabled(false);
            nextScreen = new GamePanel(gameWindow, numberWaves, backgroundMusic, gameSound, gameSpeed);
        }
        this.setPreferredSize(new Dimension(200, gameWindow.getHeight()/10));
        this.addActionListener(event -> {
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(nextScreen);
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

    public void lastInfoButton(WaitingScreen waitingScreen, int currentInfo){
        JLabel lastInfo = new JLabel("Precedent");
        lastInfo.setForeground(background);
        lastInfo.setFont(new Font("Bernard MT Condensed",Font.PLAIN, 20));
        this.add(lastInfo);
        this.setBackground(foreground);
        this.addActionListener(event -> {
            waitingScreen.setCurrentInfo(currentInfo-1);
            waitingScreen.refreshInfo();
        });
    }

    public void nextInfoButton(WaitingScreen waitingScreen, int currentInfo, boolean lastInfoReached){
        JLabel nextInfo = new JLabel("Suivant");
        nextInfo.setForeground(background);
        nextInfo.setFont(new Font("Bernard MT Condensed",Font.PLAIN, 20));
        this.add(nextInfo);
        this.setBackground(foreground);
        this.addActionListener(event -> {
            if (currentInfo == 4 && !lastInfoReached) {
                waitingScreen.lastInfoReached();
            }
            waitingScreen.setCurrentInfo(currentInfo+1);
            waitingScreen.refreshInfo();
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
            gamePanel.getGame().applyOptions(backgroundMusic, gameSound, gameSpeed);
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(gamePanel);
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
        });
    }

    public void abandonButton(GameWindow gameWindow){
        Game.reset();
        JLabel abandon = new JLabel("Abandonner");
        this.setBackground(foreground);
        abandon.setForeground(background);
        this.add(abandon);
        this.addActionListener(event -> {
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(new HomePanel(gameWindow));
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
        });
    }

    public void sideMenuButton(int typeObstacle){
        JLabel typeTower = new JLabel(Game.getListTowerTypes()[typeObstacle]);
        typeTower.setForeground(foreground);
        typeTower.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 16));
        typeTower.setHorizontalAlignment(JLabel.CENTER);
        this.setBorder(new LineBorder(foreground, 2));
        this.setHorizontalAlignment(JButton.CENTER);
        this.add(typeTower);
        this.setBackground(background);
        this.setForeground(foreground);
        this.addActionListener(event -> {
            Game.setCurrentlyPlacing(typeObstacle);
        });
    }
}
