package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;

public class OptionPanel extends JPanel {
    private JLabel title = new JLabel("project Covid Defense");
    private JPanel body = new JPanel();
    private Color background = new Color(173,175,192);
    private Color foreground = new Color(30,35,71);

    public OptionPanel(GameWindow gameWindow){
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        this.add(body, BorderLayout.CENTER);
        this.setBackground(background);
        body.setBackground(background);

        title.setPreferredSize(new Dimension(gameWindow.getWidth(), gameWindow.getHeight()/5));
        title.setFont(new Font("Bernard MT Condensed",Font.BOLD, 48));
        title.setForeground(foreground);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

        Button applyOptions = new Button();
        JLabel apply = new JLabel("Valider");
        applyOptions.setBackground(foreground);
        apply.setForeground(background);
        applyOptions.add(apply);

        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.add(Box.createVerticalGlue());
        new Slider(body, "Nombre de vagues", 5);
        new Slider(body, "Musique", 10);
        new Slider(body, "Son du jeu", 10);
        new Slider(body, "Vitesse de jeu", 3);
        body.add(applyOptions);
        body.add(Box.createVerticalGlue());


    }
}