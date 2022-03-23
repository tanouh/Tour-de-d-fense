package up.TowerDefense.model.map;
import up.TowerDefense.model.object.Position;

public class Path {
    //Cercle qui permet de lisser le chemin entre 3 points, en utilisant un cercle
    class SmoothingCircle{
        private Position a;
        private Position b;
        private Position c;
        private Position ab;
        private Position bc;
        public Position center;
        public double radius;
        public double angle;
        private double startDis ;
        private double endDis;
        private double startAngle;
        private double endAngle;

        public double Distance(){
            return  startDis + radius * angle + endDis;
        }
        public Position GetPos(double dis){
            //On clamp dis entre 0 et Distance()
            dis = Math.max(0, Math.min(Distance(), dis));

            //Si avant le début du cercle
            if (dis < startDis) return Position.Lerp(a,ab,dis/startDis);
            //Si après le début du cercle
            else if (dis > startDis + radius*angle) return Position.Lerp(bc,c,(dis - startDis - radius*angle) /endDis);
            //Si dans le cercle
            else
            {
                double t = (dis - startDis)/radius * angle;
                double alpha = startAngle * (1.0 - t) + endAngle * t;
                return new Position(center.x + Math.cos(alpha) * radius,center.y + Math.sin(alpha)*radius);
            }
        }

        public SmoothingCircle(Position _a, Position _b, Position _c){
            a = _a;
            b = _b;
            c = _c;
            double abDis = a.Distance(b);
            double bcDis = c.Distance(b);
            //La distance entre : "l'intersection du cercle avec ab ou bc" et "le point b"
            float disToB = (float)Math.min(abDis, bcDis)/2f;
            //Les points d'intersection du cercle avec ab et bc
            ab = Position.Lerp(b,a,disToB/abDis);
            bc = Position.Lerp(b,c,disToB/bcDis);

            //On calcule le rayon du cercle
            double alpha = b.AngleRad(a,c);
            radius = Math.tan(alpha/2f)* disToB;

            //Direction orthogonale au segment ab
            Position dir = new Position(a.y - b.y,b.x - a.x);
            dir.Normalize();
            double sign = Math.signum(Position.Dot(dir,new Position(bc.x - b.x, bc.y - b.y)));

            //Position finale du cercle
            center = new Position(ab.x + dir.x * sign * radius, ab.y + dir.y * sign * radius);

            //l'angle de départ et d'arrivée
            startAngle = Math.atan2(ab.y - center.y,ab.x - center.x);
            endAngle = Math.atan2(bc.y - center.y,bc.x - center.x);
            angle = Math.PI - alpha;

            //Les distances entre a et le début du cercle, ainsi que c et le début du cercle
            startDis = a.Distance(ab);
            endDis = c.Distance(bc);
        }
    }
    public Tile[] path;
    private SmoothingCircle[] circles;
    public double progression;
    public double length;

    public Path(Tile[] _path){
        path = _path;
        progression = 0;

        //On utilise la boucle pour créer les cercles mais aussi pour calculer la longueur du parcours
        circles = new SmoothingCircle[path.length- 2];
        length = 0f;
        for (int i = 0; i < path.length - 2; i++){
            circles[i] = new SmoothingCircle(path[i-1].pos,path[i].pos,path[i+1].pos);
            length += circles[i].Distance();
        }
    }
    //Récupère la position à une distance dis du début du chemin
    public Position GetPos(double dis){
        //On retourne simplement la position du cercle concerné à la distance relative dis
        for (SmoothingCircle circle : circles){
            if (dis <= circle.Distance()) return circle.GetPos(dis);
            else dis -= circle.Distance();
        }
        return path[path.length - 1].pos;
    }
    //On peut aussi utiliser la même fonction avec un temps et une vitesse
    public Position GetPos(double t, double spd){
        return GetPos(t*spd);
    }
}
