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
    //private String name; 
    public ArrayList<EnemySpawn> enemies;
    private long timeSinceLastSpawn;
    public static int count =0;
    private Timer enemyTimer;

    public Subwave() {
        enemies = new ArrayList<>();
        enemies.add(EnemySpawn.getBacterium());
        enemyTimer = new Timer();
    }

    public long getInterval(){
        return this.spawnInterval;
    }

    public void spawn(){
        enemies.add(EnemySpawn.getBacterium());
    }


    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        /*for (int i =0 ; i < enemies.get(0).quantity; i++){
            MapGenerator.charactersList.add(new Enemy(enemies.get(0).enemy, enemies.get(0).spawnPoint));
        }*/
        MapGenerator.charactersList.add(new Enemy(PresetEnemy.Bacterium(), new Position(0,30)));
        count++;

        // todo : Pour respecter l'intervalles de repos entre deux vagues
        // mais cette solution n'est pas vraiment adaptée donc c'est mieux de
        // de chercher autre chose
        if(count > 2){
            try{
                long a= System.currentTimeMillis();
                Thread.sleep(10000);
                System.out.println(System.currentTimeMillis()-a);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            count =0;
        }
    }
}
