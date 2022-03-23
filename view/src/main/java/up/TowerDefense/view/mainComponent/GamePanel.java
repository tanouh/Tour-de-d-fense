package up.TowerDefense.view.mainComponent;

import up.TowerDefense.model.game.Game;
import up.TowerDefense.view.componentHandler.MapGenerator;
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
    private Color background = new Color(173,175,192);
    private Color foreground = new Color(30,35,71);

    //JLabel du header :
    private JLabel wavesLeft;
    private JLabel enemyLeft;
    private JLabel creditsLeft;
    private JLabel lifesLeft;

    public GamePanel(GameWindow gameWindow, int numberWaves, int backgroundMusic,
                     int gameSound, int gameSpeed){
        this.gameWindow = gameWindow;
        this.game = new Game(numberWaves, backgroundMusic, gameSound, gameSpeed);

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
        header.setBackground(background);
        header.setBorder(new LineBorder(foreground, 5));

        JLabel title = new JLabel("project\nCovid Defense");
        title.setPreferredSize(new Dimension(gameWindow.getWidth()/5, gameWindow.getHeight()/5));
        title.setFont(new Font("Bernard MT Condensed",Font.BOLD, 20));
        title.setForeground(foreground);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setBorder(new LineBorder(foreground, 2));
        header.add(title);

        wavesLeft = Label.addHeaderLabel(header,"Vague 0/" + Game.getWavesLeft());
        enemyLeft = Label.addHeaderLabel(header, "Ennemis restants : ");
        creditsLeft = Label.addHeaderLabel(header,"Argent : " + Game.getCredits());
        lifesLeft = Label.addHeaderLabel(header, "Vies : " + Game.getLives());
    }

    /**
     * Cree un menu qui prend 1/5 de la largeur de l'ecran
     * Ajoute des boutons correspondants aux tours que le joueur peut creer
     */
    public void setSideMenu(){
        sideMenu.setBackground(background);
        sideMenu.setBorder(new LineBorder(foreground, 5));
        sideMenu.setPreferredSize(new Dimension(gameWindow.getWidth()/5, gameWindow.getHeight()/5));

        JLabel title = new JLabel("Construction Tour");
        title.setFont(new Font("Bernard MT Condensed",Font.BOLD, 20));
        title.setBorder(new LineBorder(foreground, 2));
        title.setForeground(foreground);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        sideMenu.add(title, BorderLayout.NORTH);

        Button towerType1 = new Button();
        Button towerType2 = new Button();
        Button towerType3 = new Button();
        Button towerType4 = new Button();
        Button wall = new Button();
        towerType1.sideMenuButton(0); //TourTest
        towerType2.sideMenuButton(1); //Tour anti-champi
        towerType3.sideMenuButton(2); //Leucocyte T"
        towerType4.sideMenuButton(3); //Anticorps
        wall.sideMenuButton(4); //Mur
        listTower.add(towerType1);
        listTower.add(towerType2);
        listTower.add(towerType3);
        listTower.add(towerType4);
        listTower.add(wall);
        sideMenu.add(listTower, BorderLayout.CENTER);

        Button optionMenu = new Button();
        optionMenu.optionButton(gameWindow, null, this, this);
        JPanel footerSideMenu = new JPanel(new BorderLayout());
        footerSideMenu.setBackground(background);
        footerSideMenu.setBorder(new LineBorder(foreground, 2));
        footerSideMenu.add(optionMenu, BorderLayout.EAST);
        sideMenu.add(footerSideMenu, BorderLayout.SOUTH);
    }

    /**
     * Met à jour les statistiques de la partie
     */
    public void updateHeader(){
        wavesLeft.setText("Vague 0/" + Game.getWavesLeft());
        enemyLeft.setText("Ennemis restants : ");
        creditsLeft.setText("Argent : " + Game.getCredits());
        lifesLeft.setText("Vies : " + Game.getLives());
    }

    public Game getGame(){ return this.game; }
}
