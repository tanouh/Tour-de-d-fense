package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private JPanel header = new JPanel(new GridLayout(1,4));
    private JLabel title = new JLabel("project\nCovid Defense");
    private JLabel numWave = new JLabel("vague numero 0");
    private JLabel ennemyLeft = new JLabel("ennemis restant");
    private JLabel money = new JLabel("you're poor");
    private JPanel body = new JPanel();
    private Color background = new Color(173,175,192);
    private Color foreground = new Color(30,35,71);

    public GamePanel(GameWindow gameWindow){
        this.setLayout(new BorderLayout());

        title.setPreferredSize(new Dimension(gameWindow.getWidth()/5, gameWindow.getHeight()/5));
        title.setFont(new Font("Bernard MT Condensed",Font.BOLD, 20));
        title.setForeground(foreground);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

        header.add(title);
        header.add(numWave);
        header.add(ennemyLeft);
        header.add(money);
        body.add(new ScreenPanel());

        this.add(header, BorderLayout.NORTH);
        this.add(body, BorderLayout.CENTER);

    }
}
