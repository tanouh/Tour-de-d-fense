package up.TowerDefense.view.secondaryComponent;

import up.TowerDefense.model.game.Game;
import up.TowerDefense.model.object.PresetTower;
import up.TowerDefense.model.object.TowerTest;
import up.TowerDefense.model.object.Wall;
import up.TowerDefense.view.mainComponent.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.xml.transform.dom.DOMLocator;
import java.awt.*;
import java.util.Optional;

public class Button extends JButton {
    private JLabel label;

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
        label = new JLabel("Demarrer", JLabel.CENTER);
        label.setForeground(GameWindow.background);
        label.setFont(new Font(GameWindow.font,Font.PLAIN, GameWindow.widthScreen/70));
        this.centerText(label);
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
        label = new JLabel("Quitter", JLabel.CENTER);
        label.setFont(new Font(GameWindow.font,Font.PLAIN, GameWindow.widthScreen/70));
        label.setForeground(GameWindow.background);
        this.centerText(label);
        this.setBackground(GameWindow.foreground);
        this.setPreferredSize(new Dimension(gameWindowWidth,gameWindowHeight/10));
        this.addActionListener(event -> System.exit(0));
    }

    /**
     * Cree un bouton Options qui envoie vers un OptionPanel
     */
    public void optionButton(GameWindow gameWindow, HomePanel homePanel, GamePanel gamePanel){
        label = new JLabel("Options", JLabel.CENTER);
        label.setForeground(GameWindow.background);
        label.setFont(new Font(GameWindow.font,Font.PLAIN, GameWindow.widthScreen/70));
        this.centerText(label);
        this.setBackground(GameWindow.foreground);
        this.addActionListener(event -> {
            OptionPanel optionPanel = new OptionPanel(gameWindow, homePanel, gamePanel);
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
        label = new JLabel("Precedent");
        label.setForeground(GameWindow.background);
        label.setFont(new Font(GameWindow.font,Font.PLAIN, GameWindow.widthScreen/70));
        this.centerText(label);
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
        label = new JLabel("Suivant");
        label.setForeground(GameWindow.background);
        label.setFont(new Font(GameWindow.font,Font.PLAIN, GameWindow.widthScreen/70));
        this.centerText(label);
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
        label = new JLabel("Valider");
        this.setBackground(GameWindow.foreground);
        label.setForeground(GameWindow.background);
        label.setFont(new Font(GameWindow.font, Font.PLAIN, GameWindow.widthScreen/70));
        this.centerText(label);
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
        label = new JLabel("Valider");
        this.setBackground(GameWindow.foreground);
        label.setForeground(GameWindow.background);
        label.setFont(new Font(GameWindow.font, Font.PLAIN, GameWindow.widthScreen/70));
        this.centerText(label);
        this.addActionListener(event -> {
            int backgroundMusic = optionPanel.getBackgroundMusic().getValue();
            int gameSound = optionPanel.getGameSound().getValue();
            int gameSpeed = optionPanel.getGameSpeed().getValue();
            gamePanel.getGame().applyOptions(backgroundMusic, gameSound, gameSpeed);
            gamePanel.getScreenPanel().setPaused(false);
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
        label = new JLabel("Abandonner");
        this.setBackground(GameWindow.foreground);
        label.setForeground(GameWindow.background);
        label.setFont(new Font(GameWindow.font, Font.PLAIN, GameWindow.widthScreen/70));
        this.centerText(label);
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
    public void sideMenuButton(int typeObstacle, GamePanel gamePanel){
        label = new JLabel(Game.getListOptions()[typeObstacle]);
        label.setFont(new Font(GameWindow.font, Font.PLAIN, GameWindow.widthScreen/70));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(GameWindow.background);
        this.setBackground(GameWindow.foreground);
        this.setBorder(new LineBorder(GameWindow.background, 2));
        this.setHorizontalAlignment(JButton.CENTER);
        this.centerText(label);
        this.addActionListener(event -> {
            if (typeObstacle == 1) {
                Game.setCurrentlyUpdating(true);
                gamePanel.setSideMenu();
            }
            Game.setCurrentlyPlacing(typeObstacle);
        });
    }

    public void updateSideMenuButton(int typeObstacle){
        double price;
        switch (typeObstacle) {
            case 0:
                price = Game.getBoard().getDirectAttackPrice();
                break;
            case 2:
                price = PresetTower.TowerTest().getPrice();
                break;
            case 3:
                price = PresetTower.Anti_champis().getPrice();
                break;
            case 4:
                price = PresetTower.Leucocyte_T().getPrice();
                break;
            case 5:
                price = PresetTower.Anticorps().getPrice();
                break;
            case 6:
                price = Wall.getPrice();
                break;
            default :
                price = 0;
                break;
        }
        if  (Game.getCredits() < price) {
            this.label.setForeground(Color.LIGHT_GRAY);
            this.setBackground(Color.GRAY);
            this.setEnabled(false);
        } else {
            if (typeObstacle == Game.getCurrentlyPlacing()) {
                this.label.setForeground(GameWindow.foreground);
                this.setBackground(GameWindow.background);
                this.setEnabled(false);
            } else {
                this.label.setForeground(GameWindow.background);
                this.setBackground(GameWindow.foreground);
                this.setEnabled(true);
            }
        }
    }

    public void returnButton(JPanel sideMenu, JPanel choiceUpdate, JPanel listTower){
        label = new JLabel("Retour");
        label.setFont(new Font(GameWindow.font, Font.PLAIN, GameWindow.widthScreen/70));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(GameWindow.background);
        this.setBackground(GameWindow.foreground);
        this.setBorder(new LineBorder(GameWindow.background, 2));
        this.setHorizontalAlignment(JButton.CENTER);
        this.centerText(label);
        addActionListener(event -> {
            Game.setCurrentlyUpdating(false);
            sideMenu.remove(choiceUpdate);
            sideMenu.add(listTower);
            sideMenu.repaint();
            sideMenu.revalidate();
        });
    }

    public void tryAgainButton(boolean hasWon, GameWindow gameWindow){
        if (hasWon){
            label = new JLabel("Passer au niveau " + (Game.getLevel()+1));
        }else{
            label = new JLabel("Reessayer");
        }
        label.setForeground(GameWindow.background);
        label.setFont(new Font(GameWindow.font, Font.PLAIN, GameWindow.widthScreen/70));
        label.setHorizontalAlignment(JLabel.CENTER);
        this.setHorizontalAlignment(JButton.CENTER);
        this.centerText(label);
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
