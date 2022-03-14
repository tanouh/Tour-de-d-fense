package up.TowerDefense.view.mainComponent;

import up.TowerDefense.view.secondaryComponent.Button;

import javax.swing.*;
import java.awt.*;

public class WaitingScreen extends JPanel{
    private JPanel infoPanel = new JPanel();
    private Button startGame = new Button();
    private Button lastInfo = new Button();
    private Button nextInfo = new Button();
    private Color background = new Color(173,175,192);
    private Color foreground = new Color(30,35,71);

    private GameWindow gameWindow;

    private int currentInfo = 1;
    private int totalInfo = 5;
    private boolean lastInfoReached = false;

    private int numberWaves;
    private int backgroundMusic;
    private int gameSound;
    private int gameSpeed;

    public WaitingScreen(GameWindow gameWindow, int numberWaves,
                         int backgroundMusic, int gameSound, int gameSpeed){

        this.setLayout(new BorderLayout());
        this.gameWindow = gameWindow;
        this.numberWaves = numberWaves;
        this.backgroundMusic = backgroundMusic;
        this.gameSound = gameSound;
        this.gameSpeed = gameSpeed;

        JLabel start = new JLabel("Demarrer", JLabel.CENTER);
        start.setForeground(background);
        start.setFont(new Font("Bernard MT Condensed",Font.PLAIN, 20));
        startGame.add(start);
        startGame.setBackground(Color.GRAY);
        startGame.setPreferredSize(new Dimension(200, gameWindow.getHeight()/10));
        startGame.setEnabled(false);
        startGame.setBackground(Color.GRAY);
        this.refreshInfo();
        this.add(startGame, BorderLayout.SOUTH);
    }

    public void refreshInfo(){
        if (lastInfo.getActionListeners().length != 0){
            lastInfo.removeActionListener(lastInfo.getActionListeners()[0]);
        }
        if (nextInfo.getActionListeners().length != 0){
            nextInfo.removeActionListener(nextInfo.getActionListeners()[0]);
        }
        lastInfo.removeAll();
        nextInfo.removeAll();
        this.remove(lastInfo);
        this.remove(nextInfo);
        if (currentInfo > 1) {
            lastInfo.lastInfoButton(this, currentInfo);
            this.add(lastInfo, BorderLayout.WEST);
        }
        if (currentInfo < totalInfo){
            nextInfo.nextInfoButton(this, currentInfo, lastInfoReached);
            this.add(nextInfo, BorderLayout.EAST);
        }

//        this.infoPanel.removeAll();
//        this.remove(infoPanel);
//        this.add(new InfoPanel(currentInfo), BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
    }

    public void setCurrentInfo(int currentInfo){
        this.currentInfo = currentInfo;;
    }

    public void lastInfoReached(){
        this.remove(startGame);
        startGame.startButton(gameWindow, this, numberWaves, backgroundMusic, gameSound, gameSpeed);
        startGame.setBackground(foreground);
        startGame.setEnabled(true);
        this.add(startGame, BorderLayout.SOUTH);
        lastInfoReached = true;
    }
}
