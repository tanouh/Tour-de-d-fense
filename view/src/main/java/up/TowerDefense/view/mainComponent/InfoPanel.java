package up.TowerDefense.view.mainComponent;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class InfoPanel extends JPanel {

    /**
     * Lis le fichier "infos.txt" et affiche la partie des
     * informations correspondant à la page actuelle
     */
    public InfoPanel(int currentInfo){
        this.setBackground(GameWindow.background);
        this.setForeground(GameWindow.foreground);
        JLabel infoDisplay;
        InputStream is = this.getClass().getResourceAsStream("/infos.txt");
        Scanner scanner = new Scanner(is);
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();
        String[] info = text.split("[*]");

        JEditorPane pane = new JEditorPane();
        pane.setContentType("text/html");
        pane.setText(info[currentInfo-1]);
        this.add(pane);

//        infoDisplay = new JLabel(info[currentInfo-1]);
//        infoDisplay.setForeground(GameWindow.foreground);
//        infoDisplay.setFont(new Font(GameWindow.font, Font.PLAIN, GameWindow.widthScreen/50));
//        this.add(infoDisplay);
    }
}
