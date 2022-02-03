package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel{
    private JLabel titre = new JLabel("Covid Defense (titre provisoire)");
    private JButton lancementPartie = new JButton();
    private JButton quitterPartie = new JButton();

    public HomePanel(GameWindow gameWindow){
        this.setLayout(new GridLayout(3 ,1));
        this.setBackground(new Color(173,175,192));

        titre.setFont(new Font("Bernard MT Condensed",Font.BOLD, 32));
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setForeground(new Color(30,35,71));
        this.add(titre);

        JLabel demarrer = new JLabel("Demarrer");
        demarrer.setForeground(new Color(173,175,192));
        lancementPartie.add(demarrer);
        lancementPartie.setBackground(new Color(30,35,71));
        lancementPartie.addActionListener(event -> {
            GamePanel gamePanel = new GamePanel();
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(gamePanel);
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
        });

        JLabel quitter = new JLabel("Quitter");
        quitter.setForeground(new Color(173,175,192));
        quitterPartie.add(quitter);
        quitterPartie.setBackground(new Color(30,35,71));
        quitterPartie.addActionListener(event -> System.exit(0));

        this.add(lancementPartie);
        this.add(quitterPartie);
    }
}