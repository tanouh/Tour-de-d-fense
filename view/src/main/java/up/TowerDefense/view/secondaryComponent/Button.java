package up.TowerDefense.view.secondaryComponent;

import up.TowerDefense.model.game.Game;
import up.TowerDefense.model.object.TowerTest;
import up.TowerDefense.view.mainComponent.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.xml.transform.dom.DOMLocator;
import java.awt.*;
import java.util.Optional;

public class Button extends JButton {

    public Button(){
        super();
    }

    /**
     * Cree un bouton Demarrer qui :
     * -affiche un WaitingScreen s'il est appele depuis un HomePanel
     * -affiche un GamePanel s'il est appele depuis un WaitingScreen
     */
    public void startButton(GameWindow gameWindow, JPanel callPanel, int numberWaves, int backgroundMusic,
                            int gameSound, int gameSpeed){
        JLabel start = new JLabel("Demarrer", JLabel.CENTER);
        start.setForeground(GameWindow.background);
        start.setFont(new Font(GameWindow.font,Font.PLAIN, GameWindow.widthScreen/70));
        this.centerText(start);
        this.setBackground(GameWindow.foreground);
        this.setPreferredSize(new Dimension(200, gameWindow.getHeight()/10));
        this.addActionListener(event -> {
            JPanel nextScreen;
            if (callPanel instanceof HomePanel){
                nextScreen = new WaitingPanel(gameWindow, numberWaves,
                        backgroundMusic, gameSound, gameSpeed);
            }
            else {
                nextScreen = new GamePanel(gameWindow, numberWaves, backgroundMusic, gameSound, gameSpeed, 1);
            }
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(nextScreen);
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
        });
    }

    /**
     * Ajoute un bouton Quitter qui arrete le programme et ferme la fenetre
     */
    public void leaveButton(int gameWindowWidth, int gameWindowHeight){
        JLabel leave = new JLabel("Quitter", JLabel.CENTER);
        leave.setFont(new Font(GameWindow.font,Font.PLAIN, GameWindow.widthScreen/70));
        leave.setForeground(GameWindow.background);
        this.centerText(leave);
        this.setBackground(GameWindow.foreground);
        this.setPreferredSize(new Dimension(gameWindowWidth,gameWindowHeight/10));
        this.addActionListener(event -> System.exit(0));
    }

    /**
     * Cree un bouton Options qui envoie vers un OptionPanel
     */
    public void optionButton(GameWindow gameWindow, HomePanel homePanel, GamePanel gamePanel, JPanel returnPanel){
        JLabel options = new JLabel("Options", JLabel.CENTER);
        options.setForeground(GameWindow.background);
        options.setFont(new Font(GameWindow.font,Font.PLAIN, GameWindow.widthScreen/70));
        this.centerText(options);
        this.setBackground(GameWindow.foreground);
        this.addActionListener(event -> {
            OptionPanel optionPanel = new OptionPanel(gameWindow, homePanel, gamePanel, returnPanel);
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(optionPanel);
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
        });
    }

    /**
     * Cree un bouton qui renvoie a la derniere info lue (supposee existante)
     */
    public void lastInfoButton(WaitingPanel waitingScreen, int currentInfo){
        JLabel lastInfo = new JLabel("Precedent");
        lastInfo.setForeground(GameWindow.background);
        lastInfo.setFont(new Font(GameWindow.font,Font.PLAIN, GameWindow.widthScreen/70));
        this.centerText(lastInfo);
        this.setBackground(GameWindow.foreground);
        this.addActionListener(event -> {
            waitingScreen.setCurrentInfo(currentInfo-1);
            waitingScreen.refreshInfo();
        });
    }

    /**
     * Cree un bouton qui renvoie a la prochaine info a lire (supposee existante)
     */
    public void nextInfoButton(WaitingPanel waitingScreen, int currentInfo){
        JLabel nextInfo = new JLabel("Suivant");
        nextInfo.setForeground(GameWindow.background);
        nextInfo.setFont(new Font(GameWindow.font,Font.PLAIN, GameWindow.widthScreen/70));
        this.centerText(nextInfo);
        this.setBackground(GameWindow.foreground);
        this.addActionListener(event -> {
            waitingScreen.setCurrentInfo(currentInfo+1);
            waitingScreen.refreshInfo();
        });
    }

    /**
     * Cree un bouton Valider qui applique les choix faits dans le OptionPanel et
     * affiche le HomePanel
     */
    public void applyButtonInHome(GameWindow gameWindow, OptionPanel optionPanel, HomePanel homePanel){
        JLabel apply = new JLabel("Valider");
        this.setBackground(GameWindow.foreground);
        apply.setForeground(GameWindow.background);
        apply.setFont(new Font(GameWindow.font, Font.PLAIN, GameWindow.widthScreen/70));
        this.centerText(apply);
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

    /**
     * Cree un bouton Valider qui applique les choix faits dans le OptionPanel et
     * affiche le GamePanel
     */
    public void applyButtonInGame(GameWindow gameWindow, OptionPanel optionPanel, GamePanel gamePanel){
        JLabel apply = new JLabel("Valider");
        this.setBackground(GameWindow.foreground);
        apply.setForeground(GameWindow.background);
        apply.setFont(new Font(GameWindow.font, Font.PLAIN, GameWindow.widthScreen/70));
        this.centerText(apply);
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

    /**
     * Cree un bouton Abandonner qui renvoie le joueur a un HomePanel
     */
    public void abandonButton(GameWindow gameWindow){
        Game.reset();
        JLabel abandon = new JLabel("Abandonner");
        this.setBackground(GameWindow.foreground);
        abandon.setForeground(GameWindow.background);
        abandon.setFont(new Font(GameWindow.font, Font.PLAIN, GameWindow.widthScreen/70));
        this.centerText(abandon);
        this.addActionListener(event -> {
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(new HomePanel(gameWindow));
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
        });
    }

    /**
     * Cree un bouton pour un certain type d'obstacle qui
     * permet de placer un obstacle de cette sorte
     */
    public void sideMenuButton(int typeObstacle){
        JLabel typeTower = new JLabel(Game.getListTowerTypes()[typeObstacle]);
        typeTower.setForeground(GameWindow.foreground);
        typeTower.setFont(new Font(GameWindow.font, Font.PLAIN, GameWindow.widthScreen/70));
        typeTower.setHorizontalAlignment(JLabel.CENTER);
        this.setBorder(new LineBorder(GameWindow.foreground, 2));
        this.setHorizontalAlignment(JButton.CENTER);
        this.centerText(typeTower);
        this.setBackground(GameWindow.background);
        this.setForeground(GameWindow.foreground);
        this.addActionListener(event -> {
            Game.setCurrentlyPlacing(typeObstacle);
        });
    }

    public void tryAgainButton(boolean hasWon, GameWindow gameWindow){
        JLabel tryAgain;
        if (hasWon){
            tryAgain = new JLabel("Passer au niveau " + (Game.getLevel()+1));
        }else{
            tryAgain = new JLabel("Reessayer");
        }
        tryAgain.setForeground(GameWindow.background);
        tryAgain.setFont(new Font(GameWindow.font, Font.PLAIN, GameWindow.widthScreen/70));
        tryAgain.setHorizontalAlignment(JLabel.CENTER);
        this.setHorizontalAlignment(JButton.CENTER);
        this.centerText(tryAgain);
        this.setBackground(GameWindow.foreground);
        this.setForeground(GameWindow.background);
        this.addActionListener(event -> {
            JPanel nextPanel;
            if (hasWon){
                nextPanel = new GamePanel(gameWindow,
                        HomePanel.getOptions()[0],
                        HomePanel.getOptions()[1],
                        HomePanel.getOptions()[2],
                        HomePanel.getOptions()[3],
                        Game.getLevel()+1);
            }else{
                nextPanel = new GamePanel(gameWindow,
                        HomePanel.getOptions()[0],
                        HomePanel.getOptions()[1],
                        HomePanel.getOptions()[2],
                        HomePanel.getOptions()[3],
                        Game.getLevel());
            }
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(nextPanel);
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
        });
    }

    private void centerText(JLabel label){
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(Box.createHorizontalGlue());
        this.add(label);
        this.add(Box.createHorizontalGlue());
    }
}
