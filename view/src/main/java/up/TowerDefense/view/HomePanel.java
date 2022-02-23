package up.TowerDefense.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class HomePanel extends JPanel{
    private JLabel title = new JLabel("project Covid Defense");
    private JPanel body = new JPanel();
    private JPanel buttons = new JPanel(new GridLayout(2,1, 0, 10));
    private JPanel footer = new JPanel(new BorderLayout());
    private Button startGame = new Button();
    private Button leaveGame = new Button();
    private Button options = new Button();

    private Color background = new Color(173,175,192);
    private Color foreground = new Color(30,35,71);

    public HomePanel(GameWindow gameWindow){
        gameWindow.setTitle("project Covid Defense");

        startGame.startButton(gameWindow);
        leaveGame.leaveButton(gameWindow.getWidth(), gameWindow.getHeight());
        options.optionButton(gameWindow);

        this.setLayout(new BorderLayout());
        this.setBackground(background);
        this.setBorder(new LineBorder(foreground, 5));
        body.setBackground(background);
        buttons.setBackground(background);
        footer.setBackground(background);

        this.add(title, BorderLayout.NORTH);
        this.add(body, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);

        title.setPreferredSize(new Dimension(gameWindow.getWidth(), gameWindow.getHeight()/5));
        footer.setPreferredSize(new Dimension(gameWindow.getWidth(), gameWindow.getHeight()/7));
        title.setFont(new Font("Bernard MT Condensed",Font.BOLD, 50));
        title.setForeground(foreground);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

        buttons.setPreferredSize(new Dimension(100, 50));
        startGame.setHorizontalAlignment(JButton.CENTER);
        buttons.add(startGame);
        buttons.add(leaveGame);
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.add(Box.createVerticalGlue());
        body.add(buttons);
        body.add(Box.createVerticalGlue());

        footer.add(options, BorderLayout.EAST);
    }
}