package up.TowerDefense.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class HomePanel extends JPanel{
    private JPanel body = new JPanel();
    private JPanel footer = new JPanel(new BorderLayout());
    private JLabel titre = new JLabel("project Covid Defense");
    private HomeButton lancementPartie = new HomeButton();
    private HomeButton quitterPartie = new HomeButton();
    private HomeButton options = new HomeButton();

    public HomePanel(GameWindow gameWindow){
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(173,175,192));
        this.setBorder(new LineBorder(new Color(30,35,71), 5));
        body.setBackground(new Color(173,175,192));
        footer.setBackground(new Color(173,175,192));
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        this.add(titre, BorderLayout.NORTH);
        this.add(body, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
        body.add(Box.createVerticalGlue());
        body.add(lancementPartie);
        body.add(quitterPartie);
        body.add(Box.createVerticalGlue());
        footer.add(options, BorderLayout.EAST);

        titre.setPreferredSize(new Dimension(gameWindow.getWidth(), gameWindow.getHeight()/5));
        footer.setPreferredSize(new Dimension(gameWindow.getWidth(), gameWindow.getHeight()/7));

        titre.setFont(new Font("Bernard MT Condensed",Font.BOLD, 48));
        titre.setForeground(new Color(30,35,71));
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setVerticalAlignment(JLabel.CENTER);

        lancementPartie.startButton(gameWindow);
        quitterPartie.leaveButton(gameWindow.getWidth(), gameWindow.getHeight());
        lancementPartie.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitterPartie.setAlignmentX(Component.CENTER_ALIGNMENT);
        options.optionButton(gameWindow);
    }
}