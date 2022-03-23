package up.TowerDefense.view.mainComponent;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class InfoPanel extends JPanel {
    private Color background = new Color(173,175,192);
    private Color foreground = new Color(30,35,71);

    /**
     * Lis le fichier "infos.txt" et affiche la partie des
     * informations correspondant à la page actuelle
     */
    public InfoPanel(int currentInfo){
        this.setBackground(background);
        this.setForeground(foreground);
        JLabel infoDisplay;
        try{
            Scanner scanner = new Scanner( new File("view/src/main/resources/infos.txt"));
            String text = scanner.useDelimiter("\\A").next();
            scanner.close();
            String[] info = text.split("[*]");
            infoDisplay = new JLabel(info[currentInfo-1]);
        }catch (IOException e) {
            infoDisplay = new JLabel("An error occurred.");
            e.printStackTrace();
        }
        infoDisplay.setForeground(foreground);
        infoDisplay.setFont(new Font("Bernard MT Condensed",Font.PLAIN, GameWindow.widthScreen/50));
        this.add(infoDisplay);
    }
}
