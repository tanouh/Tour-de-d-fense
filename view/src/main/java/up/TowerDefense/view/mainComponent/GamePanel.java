package up.TowerDefense.view.mainComponent;

import up.TowerDefense.model.game.Game;
import up.TowerDefense.view.secondaryComponent.Button;
import up.TowerDefense.view.secondaryComponent.Label;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GamePanel extends JPanel {
    private GameWindow gameWindow;
    private Game game;

    //Mettre les contenus de label dans une classe "partie"
    private JPanel header = new JPanel(new GridLayout(1,4));
    private JPanel sideMenu = new JPanel(new BorderLayout());
    private JPanel listTower = new JPanel(new GridLayout(5,1));
    private JPanel body = new JPanel();

    //JLabel du header :
    private JLabel level;
    private JLabel wavesLeft;
    private JLabel enemyLeft;
    private JLabel creditsLeft;
    private JLabel lifesLeft;

    //Button du sideMenu :
    private Button[] towerTypes = new Button[5];
    //0 : Attaque directe
    //1 : Tour Anti_Champi
    //2 : Leucocyte T
    //3 : Anticorps
    //4 : Mur

    public GamePanel(GameWindow gameWindow, int numberWaves, int backgroundMusic,
                     int gameSound, int gameSpeed, int level){
        this.gameWindow = gameWindow;
        this.game = new Game(numberWaves, backgroundMusic, gameSound, gameSpeed, level);

        this.setLayout(new BorderLayout());

        this.setHeader();
        this.setSideMenu();
        this.add(header, BorderLayout.NORTH);
        body.add(new ScreenPanel(gameWindow, this));
        this.add(body, BorderLayout.CENTER);
        this.add(sideMenu, BorderLayout.EAST);


    }

    /**
     * Cree un header qui prend 1/4 de la hauteur de l'ecran
     * Affiche les statistiques de la partie en cours dans le header
     */
    public void setHeader(){
        header.setBackground(GameWindow.background);
        header.setBorder(new LineBorder(GameWindow.foreground, 6));

        JLabel title = new JLabel("project\nCovid Defense");
        title.setPreferredSize(new Dimension(gameWindow.getWidth()/6, gameWindow.getHeight()/10));
        title.setFont(new Font(GameWindow.font,Font.BOLD, GameWindow.widthScreen/60));
        title.setForeground(GameWindow.foreground);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setBorder(new LineBorder(GameWindow.foreground, 2));
        header.add(title);

        level = Label.addHeaderLabel(header, "Niveau " + Game.getLevel());
        wavesLeft = Label.addHeaderLabel(header,"Vague "+ (Game.getNbWavesTotal()-Game.getWavesLeft())
                + "/" + Game.getNbWavesTotal());
        enemyLeft = Label.addHeaderLabel(header, "Ennemis restants : " + Game.getBoard().getListEnemy().size());
        creditsLeft = Label.addHeaderLabel(header,"Argent : " + Game.getCredits());
        lifesLeft = Label.addHeaderLabel(header, "Vies : " + Game.getLives());
    }

    /**
     * Cree un menu qui prend 1/5 de la largeur de l'ecran
     * Ajoute des boutons correspondants aux tours que le joueur peut creer
     */
    public void setSideMenu(){
        sideMenu.setBackground(GameWindow.background);
        sideMenu.setBorder(new LineBorder(GameWindow.foreground, 5));
        sideMenu.setPreferredSize(new Dimension(gameWindow.getWidth()/6, gameWindow.getHeight()/5));

        JLabel title = new JLabel("Construction\\Attaque");
        title.setFont(new Font(GameWindow.font ,Font.BOLD, 20));
        title.setBorder(new LineBorder(GameWindow.foreground, 2));
        title.setForeground(GameWindow.foreground);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        sideMenu.add(title, BorderLayout.NORTH);

        for (int i = 0; i < towerTypes.length; i++){
            towerTypes[i] = new Button();
            towerTypes[i].sideMenuButton(i);
            listTower.add(towerTypes[i]);
        }
        sideMenu.add(listTower, BorderLayout.CENTER);

        Button optionMenu = new Button();
        optionMenu.optionButton(gameWindow, null, this, this);
        JPanel footerSideMenu = new JPanel(new BorderLayout());
        footerSideMenu.setBackground(GameWindow.background);
        footerSideMenu.setBorder(new LineBorder(GameWindow.foreground, 2));
        footerSideMenu.add(optionMenu, BorderLayout.EAST);
        sideMenu.add(footerSideMenu, BorderLayout.SOUTH);
    }

    /**
     * Met à jour les statistiques de la partie
     */
    public void updateHeader(){
        wavesLeft.setText("Vague " + (Game.getNbWavesTotal()-Game.getWavesLeft())
                + "/" + Game.getNbWavesTotal());
        enemyLeft.setText("Ennemis restants : " + Game.getBoard().getListEnemy().size());
        creditsLeft.setText("Argent : " + Game.getCredits());
        lifesLeft.setText("Vies : " + Game.getLives());
    }

    public void updateSideMenu(){
        for (int i = 0; i < towerTypes.length; i++){
            towerTypes[i].updateSideMenuButton(i);
        }
    }

    public Game getGame(){ return this.game; }
}
