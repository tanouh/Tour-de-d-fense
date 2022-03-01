package up.TowerDefense.view;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Cette classe traite la réception des actions effectuées par l'utilisateur à partir du clavier
 */

public class KeyAction extends AbstractAction {

    public static String UP = "MOVE UP";
    public static String DOWN = "MOVE DOWN";
    public static String LEFT = "MOVE LEFT";
    public static String RIGHT = "MOVE RIGHT";
    public static String STOP = "STAY STILL";

    enum Action {
        MOVE_UP, MOVE_DOWN, MOVE_LEFT,MOVE_RIGHT,STAY_STILL
    }

    Action action;

    public KeyAction(Action action){
        this.action=action;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (action){
            case MOVE_UP:
                Camera.upPressed = true;

                            break;
            case MOVE_DOWN:
                Camera.downPressed = true;
                            break;
            case MOVE_LEFT:
                Camera.leftPressed =true;
            case MOVE_RIGHT:
                Camera.rightPressed =true;
                break;
            case STAY_STILL:
                release();
                break;
        }
    }

    private void release() {
        Camera.downPressed = false;
        Camera.leftPressed = false;
        Camera.upPressed = false;
        Camera.rightPressed = false;
    }
}

