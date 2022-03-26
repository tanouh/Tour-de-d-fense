package up.TowerDefense.model.game;


import up.TowerDefense.model.character.Enemy;
import up.TowerDefense.model.character.PresetEnemy;
import up.TowerDefense.model.map.Board;
import up.TowerDefense.model.object.Position;
import up.TowerDefense.view.componentHandler.MapGenerator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * cette classe génère les sous vagues du jeu
 */
public class Subwave  extends TimerTask{
    private long spawnInterval; // temps entre l'apparition des enemy
    public ArrayList<EnemySpawn> enemies;
    public static int count = 0;

    public Subwave() {
        enemies = new ArrayList<>();
        enemies.add(EnemySpawn.getBacterium());
    }

    public long getInterval(){
        return this.spawnInterval;
    }


    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        MapGenerator.charactersList.add(new Enemy(PresetEnemy.Bacterium(), new Position(0,30)));
        count++;

        /* fixme : Pour respecter l'intervalles de repos entre deux vagues
            mais cette solution n'est pas vraiment adaptée
            donc c'est mieux de chercher autre chose
         */
        if(count > 2){
            try{
                Thread.sleep(10000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            count =0;
        }

        /* todo: le principe est que :
            on lance d'abord une vague de 10 ennemis de même type,
            on lance la vague suivante que lorsque tous les ennemis de cette vague sont morts ou ont atteint la cible, etc...
         */

        /*
        * fixme : je pense qu'on peut nous contenter de la classe Waves à ce niveau là (idée à voir un peu plus) */
    }
}
