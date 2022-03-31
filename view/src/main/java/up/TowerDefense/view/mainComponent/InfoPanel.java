package up.TowerDefense.view.mainComponent;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
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
        InputStream is = this.getClass().getResourceAsStream("/infos.txt");
        Scanner scanner = new Scanner(is);
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();
        String[] info = text.split("[*]");

        JEditorPane pane = new JEditorPane();
        pane.setContentType("text/html");
        pane.setEditable(false);
        HTMLEditorKit kit = new HTMLEditorKit();
        pane.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("body {background-color:" +  String.format("#%02X%02X%02X", GameWindow.background.getRed(),
                GameWindow.background.getGreen(), GameWindow.background.getBlue()) + "; " +
                "font-family:" + GameWindow.font + "; " +
                "color : " +  String.format("#%02X%02X%02X", GameWindow.foreground.getRed(),
                GameWindow.foreground.getGreen(), GameWindow.foreground.getBlue()) +
                "}");
        pane.setText(info[currentInfo-1]);
        this.add(pane);
    }
}
