package up.TowerDefense.cli;

import up.TowerDefense.view.*;
import javax.swing.*;

public class CLILauncher {

    public static void main(String[] args) {

        JFrame gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setLocationRelativeTo(null);

        GamePanel gamePanel = new GamePanel();
        gameWindow.add(gamePanel);

        gameWindow.setVisible(true);
    }

}
