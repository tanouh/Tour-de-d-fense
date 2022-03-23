package up.TowerDefense.view.mainComponent;

import up.TowerDefense.view.mainComponent.HomePanel;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    public static int widthScreen;
    public static int heightScreen;

    /**
     * Cree la fenetre de jeu
     * Fixe sa taille à la taille de l'ecran
     * Affiche un HomePanel
     */
    public GameWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        widthScreen = screenSize.width/20*19;
        heightScreen = screenSize.height/20*19;
        Dimension windowSize = new Dimension(widthScreen, heightScreen);
        this.setMinimumSize(windowSize);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);

        HomePanel homePanel = new HomePanel(this);
        this.getContentPane().add(homePanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
