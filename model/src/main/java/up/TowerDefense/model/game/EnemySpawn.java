package up.TowerDefense.model.game;

import up.TowerDefense.model.character.Enemy;

/**
 * le contenu des sous vagues du jeu
 */
public class EnemySpawn {
    //public final Position spawnPoint; // position à laquelle va apparaitre les enemy
    public Enemy enemy;
    private long interval;
    public int quantity;

    public EnemySpawn(long interval, Enemy enemy, int quantity) {
        this.enemy = enemy;
        this.quantity = quantity;
        this.interval = interval; // temps entre l'apparition de 2 enemy
    }

    public Enemy getEnemy(){
        return this.enemy;
    }

    public long getInterval(){
        return this.interval;
    }
    public int getQuantity(){
        return this.quantity;
    }
    
}
