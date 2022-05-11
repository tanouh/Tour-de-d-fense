package up.TowerDefense.view.mainComponent;

import up.TowerDefense.view.secondaryComponent.Button;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.util.Scanner;

public class WaitingPanel extends JPanel{
    private JPanel infoPanel = new JPanel();
    private Button startGame = new Button();
    private Button lastInfo = new Button();
    private Button nextInfo = new Button();

    private GameWindow gameWindow;

    private int currentInfo = 1;
    private int totalInfo;

    private int nbLevel;
    private int numberWaves;
    private int backgroundMusic;
    private int gameSound;
    private int gameSpeed;

    public WaitingPanel(GameWindow gameWindow, int nbLevel, int numberWaves,
                         int backgroundMusic, int gameSound, int gameSpeed){

        this.setLayout(new BorderLayout());
        this.gameWindow = gameWindow;
        this.nbLevel = nbLevel;
        this.numberWaves = numberWaves;
        this.backgroundMusic = backgroundMusic;
        this.gameSound = gameSound;
        this.gameSpeed = gameSpeed;

        startGame.startButton(gameWindow, this);
        this.setTotalInfo();
        this.refreshInfo();
        this.add(startGame, BorderLayout.SOUTH);
    }

    /**
     * Affiche un bouton "precedent" si des infos ont deja ete lues
     * Affiche un bouton "suivant" si il reste des infos a lire
     * Cree et affiche l'InfoPanel correpondant à la page actuelle
     */
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
            nextInfo.nextInfoButton(this, currentInfo);
            this.add(nextInfo, BorderLayout.EAST);
        }

        this.infoPanel.removeAll();
        this.remove(infoPanel);
        infoPanel = new InfoPanel(currentInfo);
        this.add(infoPanel, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
    }

    /**
     * Actualise le numero de la page actuelle
     */
    public void setCurrentInfo(int currentInfo){
        this.currentInfo = currentInfo;;
    }

    public void setTotalInfo(){
        InputStream is = this.getClass().getResourceAsStream("/infos.txt");
        Scanner scanner = new Scanner(is);
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();
        String[] info = text.split("[*]");
        this.totalInfo = info.length;
    }
}
