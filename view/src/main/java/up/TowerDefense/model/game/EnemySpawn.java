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
        int i = rand.nextInt(board.getSpawnablePoint().size());
        return board.getSpawnablePoint().get(i);
    }

    /**
     * Lance une sous-vagues d'ennemis de même type
     */
    public void spawnEnemies(){
        charactersList.add(new Enemy(this.enemy, new Position(spawnX, spawnY)));
        count++;
        if(count == quantity){
            finishedSpawn = true;
        }

        //fixme: à un certain moment certains ennemis se chevauchent sur l'interface graphique , problème à travailler plus tard
    }
    public boolean isFinished(){
        return finishedSpawn;
    }

    public void setFinishedSpawn(boolean finishedSpawn) {
        this.finishedSpawn = finishedSpawn;
    }

    /*
    La quantité augmente en fonction de l'ordre de la vague
     */
    public static EnemySpawn getBacterium(){

        return new EnemySpawn(PresetEnemy.Bacterium(), 1,getRandomSpawnPosition(Game.getBoard()));
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
