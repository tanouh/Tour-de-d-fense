package up.TowerDefense.view;

import up.TowerDefense.model.game.Player;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GamePanel extends JPanel {
    private GameWindow gameWindow;

    //Mettre les contenus de label dans une classe "partie"
    private JPanel header = new JPanel(new GridLayout(1,4));
    private JPanel sideMenu = new JPanel(new GridLayout(5,1));
    private JPanel body = new JPanel();
    private Color background = new Color(173,175,192);
    private Color foreground = new Color(30,35,71);



    public GamePanel(GameWindow gameWindow){
        this.gameWindow = gameWindow;
        this.setLayout(new BorderLayout());

        this.setHeader();
        this.setSideMenu();
        this.add(header, BorderLayout.NORTH);
        this.add(body, BorderLayout.CENTER);
        this.add(sideMenu, BorderLayout.EAST);

    }

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

        Label.addHeaderLabel(header,"Vague numero 0");
        Label.addHeaderLabel(header, "Ennemis restants : ");
        Label.addHeaderLabel(header,"Argent : " + Player.getCredits());
        Label.addHeaderLabel(header, "Vies : " + Player.getLives());
        body.add(new ScreenPanel());
    }

    public void setSideMenu(){
        sideMenu.setBackground(background);
        sideMenu.setBorder(new LineBorder(foreground, 5));
        sideMenu.setPreferredSize(new Dimension(gameWindow.getWidth()/5, gameWindow.getHeight()/5));

        JLabel title = new JLabel("Construction Tour");
        title.setFont(new Font("Bernard MT Condensed",Font.BOLD, 20));
        title.setForeground(foreground);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        sideMenu.add(title);

        Button towerType1 = new Button();
        Button towerType2 = new Button();
        Button towerType3 = new Button();
        Button towerType4 = new Button();
        towerType1.sideMenuButton("TourTest");
        towerType2.sideMenuButton("Tour anti-champi");
        towerType3.sideMenuButton("Leucocyte T");
        towerType4.sideMenuButton("Anticorps");
        sideMenu.add(towerType1);
        sideMenu.add(towerType2);
        sideMenu.add(towerType3);
        sideMenu.add(towerType4);
    }
}
