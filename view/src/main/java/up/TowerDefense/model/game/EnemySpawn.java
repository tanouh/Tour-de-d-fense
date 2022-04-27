package up.TowerDefense.model.game;

import up.TowerDefense.model.character.Enemy;
import up.TowerDefense.model.character.PresetEnemy;
import up.TowerDefense.model.map.Board;
import up.TowerDefense.model.object.Position;

import java.util.Random;

import static up.TowerDefense.model.game.Wave.waveOrder;
import static up.TowerDefense.view.componentHandler.MapGenerator.charactersList;

/**
 * le contenu des sous vagues du jeu
 */
public class EnemySpawn {
    public PresetEnemy enemy;
    public int quantity;
    private boolean finishedSpawn = false;

    private int count;
    private double spawnX;
    private double spawnY;

    public EnemySpawn(PresetEnemy enemy, int quantity, Position spawnPoint) {
        this.enemy = enemy;
        this.quantity = quantity;
        this.count=0;
        this.spawnX = spawnPoint.x;
        this.spawnY =  spawnPoint.y;
    }

    /**
     * Retourne un point de frai aléatoire
     */
    public static Position getRandomSpawnPosition(Board board){
        Random rand = new Random();
        int i = rand.nextInt(board.spawnPoint.size());
        Position spawnPos = board.spawnPoint.get(i);
        return spawnPos;
    }

    /**
     * Lance une sous-vagues d'ennemis de même type
     */
    public void spawnEnemies(){
        Enemy e = new Enemy(this.enemy, new Position(spawnX, spawnY));
        e.live();
        e.upgrade(Game.getLevel());
        System.out.println("\t\t\t\tStats :"+e);
        charactersList.add(e);
        count++;
        if(count == quantity){
            finishedSpawn = true;
        }
    }

    public boolean isFinished(){
        return finishedSpawn;
    }



    /*
    La quantité augmente en fonction de l'ordre de la vague
     */
    public static EnemySpawn getBacterium(){
        return new EnemySpawn(PresetEnemy.Bacterium(), 5*waveOrder,getRandomSpawnPosition(Game.getBoard()));
    }

    public static EnemySpawn getVirus(){

        return new EnemySpawn(PresetEnemy.Virus(), 5*waveOrder,getRandomSpawnPosition(Game.getBoard()));
    }
    public static EnemySpawn getCovid(){
        return new EnemySpawn(PresetEnemy.Covid(), 5*waveOrder,getRandomSpawnPosition(Game.getBoard()));
    }
    public static EnemySpawn getFungus(){
        return new EnemySpawn(PresetEnemy.Fungus(), 5*waveOrder,getRandomSpawnPosition(Game.getBoard()));
    }
    public static EnemySpawn getParasite(){
        return new EnemySpawn(PresetEnemy.Parasite(), 5*waveOrder,getRandomSpawnPosition(Game.getBoard()));
    }



}
