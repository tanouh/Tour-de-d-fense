package up.TowerDefense.view.componentHandler;

import up.TowerDefense.view.mainComponent.ScreenPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static up.TowerDefense.view.mainComponent.ScreenPanel.*;

public class MouseHandler implements MouseListener {

    public int mouseX;
    public int mouseY;
    public boolean mousePressed;
    private ScreenPanel screenPanel;

    public MouseHandler(ScreenPanel screenPanel){
        this.screenPanel = screenPanel;

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        /*mouseX = e.getX()/sizeCase;
        mouseY = e.getY()/sizeCase ;*/
        mousePressed = true;

        /*try {
            mapGen.addObstacle(mouseX, mouseY);
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/

        /*à revoir
         */
        int x  = e.getX();
        int y = e.getY();
        if(x < windowWidth/2) {
            mouseX = (screenPanel.camera.worldX - x/tileSize)/tileSize;
        }
        else{
            mouseX = (screenPanel.camera.worldX + x/tileSize)/tileSize;
        }
        if(y < windowHeight/2) {
            mouseY = (screenPanel.camera.worldY - y/tileSize)/tileSize;
        }
        else{
            mouseY = (screenPanel.camera.worldY + y/tileSize)/tileSize;
        }
        System.out.println(mouseX+ " "+mouseY);
    }


    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed =false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mousePressed = false;
    }

}
