package up.TowerDefense.view.mainComponent;

import javax.swing.*;

public class EndPanel extends JPanel {

    public EndPanel(boolean victoire){
        if (victoire){
            this.add(new JLabel("bravo"));
        }else{
            this.add(new JLabel("pas bravo"));
        }
    }
}
