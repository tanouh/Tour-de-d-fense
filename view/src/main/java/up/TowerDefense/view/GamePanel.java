package up.TowerDefense.view;

import up.TowerDefense.model.game.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GamePanel extends JPanel {

    //Mettre les contenus de label dans une classe "partie"
    private JPanel header = new JPanel(new GridLayout(1,4));
    private JPanel body = new JPanel();
    private Color background = new Color(173,175,192);
    private Color foreground = new Color(30,35,71);



    public GamePanel(GameWindow gameWindow){
        this.setLayout(new BorderLayout());
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


//        Label numWave = new JLabel();
//        Label ennemyLeft = new JLabel();
//        Label money = new JLabel();
        Label.addHeaderLabel(header,"Vague numero 0");
        Label.addHeaderLabel(header, "Ennemis restant : ");
        Label.addHeaderLabel(header,"Argent : " + Player.getCredits());
        Label.addHeaderLabel(header, "Vies : " + Player.getLives());
        body.add(new ScreenPanel());

        this.add(header, BorderLayout.NORTH);
        this.add(body, BorderLayout.CENTER);

    }

}
