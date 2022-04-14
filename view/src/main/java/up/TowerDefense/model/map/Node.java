package up.TowerDefense.model.map;
import up.TowerDefense.model.object.Position;
import java.util.*;

public class Node {
    Tile tile;
    public int gCost;
    public int hCost;
    public Node parent;

    public Node(Tile _tile){
        tile = _tile;
    }

    public int fCost(){
        return gCost + hCost;
    }

    public Node[] Neighbours(){
        List<Node> neighbours = new ArrayList<Node>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0)
                    continue;
                Position neighbourPos = new Position( (int)tile.getPos().x + x ,(int)tile.getPos().y + y);
                if (neighbourPos.Legal()) {
                    //System.out.println("                "+Board.map.getTile(neighbourPos).getOccupier());
                    neighbours.add(new Node(Board.map.getTile(neighbourPos)));
                }
            }
        }
        return neighbours.toArray(new Node[0]);
    }

    public String toString(){
        return tile.getPos().x + "-" + tile.getPos().y;
    }

    public boolean isTarget(Position targetPos){
        return (this.tile.pos.x == targetPos.x && this.tile.pos.y == targetPos.y);
    }

    public static Node BestNode(List<Node> nodes){
        Node node = nodes.get(0);
        for (int i = 1; i < nodes.size(); i ++) {
            if (nodes.get(i).fCost() <= node.fCost()) {
                if (nodes.get(i).hCost < node.hCost)
                    node = nodes.get(i);
            }
        }
        return node;
    }
}
