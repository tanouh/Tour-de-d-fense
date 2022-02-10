package up.TowerDefense.view.shift;

import up.TowerDefense.view.GamePanel;
import up.TowerDefense.view.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class UserShift extends Shift{
    GamePanel gp;
    KeyHandler kHandler;
    Shift.direction dir;

    public final int screenX;
    public final int screenY;

    BufferedImage image;

    public UserShift(GamePanel gp , KeyHandler kh){
        this.gp= gp;
        this.kHandler = kh;

        screenX = gp.windowWidth/2;
        screenY = gp.windowHeight/2;

        loadImage();
        setDefaultValues();
    }

    public void loadImage(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/cursor.png"));
        }catch(IOException e ){
            e.printStackTrace();
        }
    }

    public void setDefaultValues(){
        worldX=gp.tileSize*23;
        worldY=gp.tileSize*23;
        speed = 4;
        dir = direction.UP;
    }

    public void update(){
        if(kHandler.upPressed){
            worldY-=speed;
            dir=direction.UP;
        }

        else if(kHandler.downPressed){
            worldY+=speed;
            dir=direction.DOWN;
        }

        else if(kHandler.leftPressed){
            worldX-=speed;
            dir=direction.LEFT;
        }

        else if(kHandler.rightPressed){
            worldX+=speed;
            dir = direction.RIGHT;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.drawImage(image,screenX, screenY, gp.tileSize, gp.tileSize,null);
    }


}

