package up.TowerDefense.model.game;

import java.util.ArrayList;

import static up.TowerDefense.model.game.Wave.TIME_SINCE_LAST_SPAWN;

/**
 * cette classe génère les sous vagues du jeu
 */
public class Subwave {
    public static final long SPAWN_INTERVAL = 500; // temps entre l'apparition des enemy
    public static final long BREAK_TIME = 2000; // pause entre deux sous-vagues d'enemySpawn


    private int index;
    private EnemySpawn currentSpawn;

    public ArrayList<EnemySpawn> enemies;
    public  int count;
    private boolean finishedSubwave = false;


    public Subwave(ArrayList<EnemySpawn> enemies) {
        this.enemies = enemies;
        count = 0;
        index = 0 ;
        this.currentSpawn= enemies.get(index);
    }

    /**
     * Création des vagues préfabriquées de différents type d'ennemis
     */

    public static Subwave subwaves_in_order(){
        ArrayList<EnemySpawn> enemySpawned = new ArrayList<>();
        switch (Wave.waveOrder){
            case 1:
                EnemySpawn e1 = EnemySpawn.getBacterium();
                enemySpawned.add(e1);
                break;
            case 2:
                EnemySpawn e2 = EnemySpawn.getVirus();
                enemySpawned.add(e2);
                break;
            case 3:
                EnemySpawn e3 = EnemySpawn.getBacterium();
                enemySpawned.add(e3);
                break;
            case 4:
                EnemySpawn e4 = EnemySpawn.getVirus();
                enemySpawned.add(e4);
                break;
            case 5:
                EnemySpawn e5 = EnemySpawn.getVirus();
                enemySpawned.add(e5);
                break;

            default:
                break;
        }
        Wave.waveOrder++;
        return new Subwave(enemySpawned);
    }


    /**
     * The action to be performed by this timer task.
     */
    public void run() {
        if(!currentSpawn.isFinished()){
            if (System.currentTimeMillis() - TIME_SINCE_LAST_SPAWN > SPAWN_INTERVAL){
                currentSpawn.spawnEnemies();
                TIME_SINCE_LAST_SPAWN = System.currentTimeMillis();
            }
        }else{
            index++;
            finishedSubwave = (count++ == enemies.size());
            if(index < enemies.size()){
                currentSpawn = enemies.get(index);
                currentSpawn.spawnEnemies();
            }
        }
    }

    public boolean isFinished (){
        return this.finishedSubwave;
    }


}
