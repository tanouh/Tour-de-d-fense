package up.TowerDefense.model.game;

import up.TowerDefense.model.character.Enemy;
import up.TowerDefense.model.character.PresetEnemy;
import up.TowerDefense.model.map.Board;
import up.TowerDefense.model.object.Position;

import java.util.Random;

/**
 * le contenu des sous vagues du jeu
 */
public class EnemySpawn {
    public final Position spawnPoint; // position à laquelle va apparaitre les enemy
    public Enemy enemy;
    private static long INTERVAL = 500;
    public int quantity;
    /*La quantité de temps entre le spawn de deux enemis successifs*/
    private long spawnTime;

    public EnemySpawn(Enemy enemy, int quantity, Position spawnPoint) {
        this.enemy = enemy;
        this.quantity = quantity;
        this.spawnPoint = spawnPoint;
    }

    public Enemy getEnemy(){
        return this.enemy;
    }

    public long getInterval(){
        return this.INTERVAL;
    }
    public int getQuantity(){
        return this.quantity;
    }

    /**
     * Retourne un point de frai aléatoire
     */
    private static Position getRandomSpawnPosition(Board board){
        Random rand = new Random();
        int i = rand.nextInt(board.getSpawnablePoint().size());
        return board.getSpawnablePoint().get(i);
    }

    public static EnemySpawn getBacterium(){
         Position spawnPoint = getRandomSpawnPosition(Game.getBoard());
         return new EnemySpawn(new Enemy(PresetEnemy.Bacterium(), spawnPoint), 10,spawnPoint );
    }

    public void spawnEnemy() {
        //Mettre les ennemis dans mapGen un à un

        //fixme : voir si on n'a pas besoin de nouveau threads pour gérer les vagues
    }
}
