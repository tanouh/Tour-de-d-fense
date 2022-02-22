package up.TowerDefense.model.map;
import up.TowerDefense.model.object.Position;
import java.util.*;

public class Pathfinding {

    Tile[] FindPath(Position startPos, Position targetPos) {
        Node startNode = new Node(Map.map.getTile(startPos));
        Node targetNode = new Node(Map.map.getTile(targetPos));

        List<Node> openSet = new ArrayList<Node>();
        HashSet<Node> closedSet = new HashSet<Node>();
        openSet.add(startNode);

        while (openSet.size() > 0) {
            Node node = Node.BestNode(openSet);
            openSet.remove(node);
            closedSet.add(node);

            if (node == targetNode) return RetracePath(startNode,targetNode);

            Node[] neighbours = node.Neighbours();
            for (int i = 0; i < neighbours.length; i++) {
                Node neighbour = neighbours[i];
                if (!neighbour.tile.isEmpty() || closedSet.contains(neighbour)) {
                    continue;
                }

                int newCostToNeighbour = node.gCost + GetDistance(node, neighbour);
                if (newCostToNeighbour < neighbour.gCost || !openSet.contains(neighbour)) {
                    neighbour.gCost = newCostToNeighbour;
                    neighbour.hCost = GetDistance(neighbour, targetNode);
                    neighbour.parent = node;

                    if (!openSet.contains(neighbour))
                        openSet.add(neighbour);
                }
            }
        }
        return null;
    }

    Tile[] RetracePath(Node startNode, Node endNode) {
        List<Node> path = new ArrayList<Node>();
        Node currentNode = endNode;

        while (currentNode != startNode) {
            path.add(currentNode);
            currentNode = currentNode.parent;
        }
        Tile[] tilePath = new Tile[path.size()];
        for(int i = 0; i < tilePath.length; i++){
            tilePath[0] = path.get(tilePath.length - i - 1).tile;
        }
        return tilePath;
    }

    int GetDistance(Node nodeA, Node nodeB) {
        int dstX = (int)Math.abs(nodeA.tile.getPos().x - nodeB.tile.getPos().x);
        int dstY = (int)Math.abs(nodeA.tile.getPos().y - nodeB.tile.getPos().y);

        if (dstX > dstY)
            return 14*dstY + 10* (dstX-dstY);
        return 14*dstX + 10 * (dstY-dstX);
    }
}
