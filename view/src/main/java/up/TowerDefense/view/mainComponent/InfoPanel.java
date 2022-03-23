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

    public InfoPanel(int currentInfo){
        this.setBackground(background);
        this.setForeground(foreground);
        JLabel infoDisplay;
        try{
            Scanner scanner = new Scanner( new File("view/src/main/resources/infos.txt"));
            String text = scanner.useDelimiter("\\A").next();
            scanner.close();
            String[] info = text.split("[*]");
            for (int i = 0; i < info.length; i++) System.out.println(info[i]);
            infoDisplay = new JLabel(info[currentInfo-1]);
        }catch (IOException e) {
            infoDisplay = new JLabel("An error occurred.");
            e.printStackTrace();
        }
        infoDisplay.setForeground(foreground);
        infoDisplay.setFont(new Font("Bernard MT Condensed",Font.PLAIN, 20));
        this.add(infoDisplay);
    }
}
