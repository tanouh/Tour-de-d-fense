package up.TowerDefense.model.game;

import java.util.ArrayList;

import static up.TowerDefense.model.game.EnemySpawn.*;
import static up.TowerDefense.model.game.Wave.*;

/**
 * cette classe génère les sous vagues du jeu
 */
public class Subwave {
    public static final long SPAWN_INTERVAL = 1000; // temps entre l'apparition des enemy
    public static final long BREAK_TIME = 10000; // pause entre deux sous-vagues d'enemySpawn


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

    public static Subwave subwavesInOrder(){
        ArrayList<EnemySpawn> enemySpawned = new ArrayList<>();
        switch (Wave.waveOrder){
            case 1: case 3:
                enemySpawned.add(getVirus());
                //enemySpawned.add(getFungus());
                //enemySpawned.add(getFungus());
                break;
            case 2:
                enemySpawned.add(getBacterium());
                //enemySpawned.add(getParasite());
                break;
            case 4:
                enemySpawned.add(getVirus());
                //enemySpawned.add(getParasite());
                break;
            case 5:
                //enemySpawned.add(getCovid());
                enemySpawned.add(getBacterium());
                break;

            default:
                break;
        }
        Game.setWavesLeft(Game.getWavesLeft()-1);
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
                Wave.resetTimeSinceLastSpawn();
            }
        }else{
            if(System.currentTimeMillis() - TIME_SINCE_LAST_SPAWN > BREAK_TIME) {
                finishedSubwave = (count++ == enemies.size());
                if(index+1 < enemies.size()){
                    currentSpawn = enemies.get(++index);
                    Wave.resetTimeSinceLastSpawn();
                }
            }
        }
    }

    public boolean isFinished (){
        return this.finishedSubwave;
    }

}
