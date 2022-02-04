package up.TowerDefense.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.awt.GridBagConstraints.BOTH;


public class MapPanel extends JPanel {
    BufferedImage base ;
    int x ;
    int y;
    int size=16;
    Color color;

    public MapPanel(){
        loadImage("/pixil-frame-0.png");

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill=BOTH;
        paintImage(gbc);
    }

    private void loadImage(String img){
        try{
            base  = ImageIO.read(getClass().getResourceAsStream(img));
            if(base!=null) System.out.println("image ok");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintImage(GridBagConstraints gbc) {
        int imgWidth = base.getWidth();
        int imgHeight = base.getHeight();


        for (int col = 0; col < imgWidth; col++) {
            gbc.gridy = y = col;
            for (int row = 0; row < imgHeight; row++){
                gbc.gridx = x = row;
                color = new Color(base.getRGB(col, row), false);
                repaint();

            }

        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fillRect(x,y ,size,size);

    }

    public static void main(String[] args) {
        JFrame window= new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Test");
        window.add(new MapPanel());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}
