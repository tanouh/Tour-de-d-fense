package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;

public class OptionPanel extends JPanel {
    private JLabel title = new JLabel("project Covid Defense");
    private JPanel body = new JPanel();
    private Color background = new Color(173,175,192);
    private Color foreground = new Color(30,35,71);
    private Slider numberWaves = null;
    private Slider backgroundMusic = null;
    private Slider gameSound = null;
    private Slider gameSpeed = null;

    public OptionPanel(GameWindow gameWindow, HomePanel homePanel, GamePanel gamePanel, JPanel returnPanel){
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
        if (homePanel != null) {
            applyOptions.applyButtonInHome(gameWindow, this, homePanel);
        }else{
            applyOptions.applyButtonInGame(gameWindow, this, gamePanel);
        }
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.add(Box.createVerticalGlue());
        if (homePanel != null) {
            numberWaves = new Slider(body, "Nombre de vagues", 1, 5);
        }
        backgroundMusic = new Slider(body, "Musique", 0, 10);
        gameSound = new Slider(body, "Son du jeu", 0,10);
        gameSpeed = new Slider(body, "Vitesse de jeu", 1,3);
        body.add(applyOptions);
        if (gamePanel != null){
            Button abandonButton = new Button();
            abandonButton.abandonButton(gameWindow);
            body.add(abandonButton);
        }
        body.add(Box.createVerticalGlue());
    }

    public Slider getNumberWaves(){ return numberWaves; }
    public Slider getBackgroundMusic(){ return backgroundMusic; }
    public Slider getGameSound(){ return gameSound; }
    public Slider getGameSpeed(){ return gameSpeed; }
}