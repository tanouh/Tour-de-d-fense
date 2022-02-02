package up.TowerDefense.model.object;

public class DestructibleObstacle extends Obstacle{
        private int lifePoint_max;
        private int lifePoint_current;

        public DestructibleObstacle(int x, int y, int size, int lifeMax, int lifeCurrent){
                super(x,y,size);
                this.lifePoint_max = lifeMax;
                this.lifePoint_current = lifeCurrent;
        }
}
