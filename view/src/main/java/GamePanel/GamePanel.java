package GamePanel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    final int sizeCase = 48;
    final int nbCol = 10;
    final int nbRow = 8;
    final int windowWidth = sizeCase*nbCol;
    final int windowHeight = sizeCase*nbRow;

    public GamePanel(){

        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setBackground(Color.GRAY);
    }
}