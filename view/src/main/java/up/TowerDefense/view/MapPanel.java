package up.TowerDefense.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MapPanel extends JPanel {
    BufferedImage base ;
    public MapPanel(String image){
        loadImage(image);

        this.setLayout(new GridBagLayout());
    }

    private void loadImage(String img){
        try{
            base  = ImageIO.read(getClass().getResourceAsStream(img));

            int imgWidth = base.getWidth();
            int imgHeight = base.getHeight();

            Color color ;
             for (int col = 0 ; col < imgWidth ;col++ ){
                 for (int row = 0 ; row < imgHeight; row++)
                     color = new Color(base.getRGB(col, row), false);


             }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
