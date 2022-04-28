package up.TowerDefense.view.mainComponent;

import up.TowerDefense.view.secondaryComponent.Button;
import up.TowerDefense.view.secondaryComponent.Slider;

import javax.swing.*;
import java.awt.*;

public class OptionPanel extends JPanel {
    private JLabel title = new JLabel("project Covid Defense");
    private JPanel body = new JPanel();
    private JPanel footer = new JPanel(new GridLayout(1,2));
    private Slider numberWaves = null;
    private Slider backgroundMusic = null;
    private Slider gameSound = null;
    private Slider gameSpeed = null;

    /**
     * Cree un Panel avec des sliders permettant de seletionner des options de jeu
     * Ajoute un bouton pour abandonner la partie si ce Panel est appele depuis un GamePanel
     */
    public OptionPanel(GameWindow gameWindow, HomePanel homePanel, GamePanel gamePanel){
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        this.add(body, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
        this.setBackground(GameWindow.background);
        body.setBackground(GameWindow.background);
        footer.setBackground(GameWindow.background);

        title.setPreferredSize(new Dimension(gameWindow.getWidth(), gameWindow.getHeight()/5));
        title.setFont(new Font(GameWindow.font,Font.BOLD, GameWindow.widthScreen/30));
        title.setForeground(GameWindow.foreground);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        footer.setPreferredSize(new Dimension(GameWindow.widthScreen, GameWindow.heightScreen/10));

        Button applyOptions = new Button();
        if (homePanel != null) {
            applyOptions.applyButtonInHome(gameWindow, this, homePanel);
        }else{
            gamePanel.getScreenPanel().setPaused(true);
            applyOptions.applyButtonInGame(gameWindow, this, gamePanel);
        }
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.add(Box.createVerticalGlue());
        if (homePanel != null) {
            numberWaves = new Slider(body, "Nombre de vagues", 1, 5);
            numberWaves.setValue(HomePanel.getOptions()[0]);
        }
        backgroundMusic = new Slider(body, "Musique", 0, 10);
        gameSound = new Slider(body, "Son du jeu", 0,10);
        gameSpeed = new Slider(body, "Vitesse de jeu", 1,3);
        backgroundMusic.setValue(HomePanel.getOptions()[1]);
        gameSound.setValue(HomePanel.getOptions()[2]);
        gameSpeed.setValue(HomePanel.getOptions()[3]);
        footer.add(applyOptions);
        if (gamePanel != null){
            Button abandonButton = new Button();
            abandonButton.abandonButton(gameWindow);
            footer.add(abandonButton);
        }
        body.add(Box.createVerticalGlue());
    }

    public Slider getNumberWaves(){ return numberWaves; }
    public Slider getBackgroundMusic(){ return backgroundMusic; }
    public Slider getGameSound(){ return gameSound; }
    public Slider getGameSpeed(){ return gameSpeed; }
}