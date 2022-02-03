package up.TowerDefense.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean up, down,left,right,selected;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch(code){
            case KeyEvent.VK_UP: up = true ; break;
            case KeyEvent.VK_DOWN: down =true; break;
            case KeyEvent.VK_LEFT: left = true ; break;
            case KeyEvent.VK_RIGHT: right = true ; break;
            case KeyEvent.VK_ENTER: selected = true ;break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch(code){
            case KeyEvent.VK_UP: up = false ; break;
            case KeyEvent.VK_DOWN: down =false; break;
            case KeyEvent.VK_LEFT: left = false ; break;
            case KeyEvent.VK_RIGHT: right = false ; break;
            case KeyEvent.VK_ENTER: selected = false;break;
        }
    }
}
