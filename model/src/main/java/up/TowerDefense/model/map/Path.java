package up.TowerDefense.model.map;
import up.TowerDefense.model.object.Position;

public class Path {
    //Cercle qui permet de lisser le chemin entre 3 points, en utilisant un cercle
    abstract class SubPath{
        protected Position start;
        protected Position end;

        public SubPath(Position _a, Position _b, Position _c){
            start = Position.Lerp(_a,_b,0.5);
            end = Position.Lerp(_b,_c,0.5);
        }
        public double Distance(){
            return 1f;
        }
        public Position GetPos(double dis){
            return null;
        }
    }
    class SubStraight extends SubPath{
        public SubStraight(Position _a, Position _b, Position _c){
            super(_a,_b,_c);
        }
        public double Distance(){
            return start.Distance(end);
        }
        public Position GetPos(double dis){
            return Position.Lerp(start,end,dis/Distance());
        }
    }
    class SubArc extends SubPath{
        private Position arcStart;
        private Position arcEnd;
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
            if (dis < startDis) return Position.Lerp(start,arcStart,dis/startDis);
            //Si après le début du cercle
            else if (dis > startDis + radius*angle) return Position.Lerp(arcEnd,end,(dis - startDis - radius*angle) /endDis);
            //Si dans le cercle
            else
            {
                double t = (dis - startDis)/radius * angle;
                double alpha = startAngle * (1.0 - t) + endAngle * t;
                return new Position(center.x + Math.cos(alpha) * radius,center.y + Math.sin(alpha)*radius);
            }
        }

        public SubArc(Position a, Position b, Position c){
            super(a,b,c);
            //La distance entre : "l'intersection du cercle avec ab ou bc" et "le point b"
            float disToB = (float)Math.min(start.Distance(b), end.Distance(b));
            //Les points de départ et d'arrivée de l'arc
            arcStart = Position.Lerp(b,start,disToB/start.Distance(b));
            arcEnd = Position.Lerp(b,end,disToB/end.Distance(b));

            //On calcule le rayon du cercle
            double alpha = b.AngleRad(arcStart,arcEnd);
            radius = Math.tan(alpha/2f)* disToB;

            //Direction orthogonale au segment ab
            Position dir = new Position(start.y - b.y,b.x - start.x);
            dir.Normalize();
            double sign = Math.signum(Position.Dot(dir,new Position(end.x - b.x, end.y - b.y)));

            //Position finale du centre du cercle
            center = new Position(arcStart.x + dir.x * sign * radius, arcStart.y + dir.y * sign * radius);

            //l'angle de départ et d'arrivée
            startAngle = Math.atan2(arcStart.y - center.y,arcStart.x - center.x);
            endAngle = Math.atan2(arcEnd.y - center.y,arcEnd.x - center.x);
            angle = Math.PI - alpha;

            //Les distances entre a et le début du cercle, ainsi que c et le début du cercle
            startDis = start.Distance(arcStart);
            endDis = end.Distance(arcEnd);
        }
    }
    public Tile[] path;
    private SubPath[] subs;
    public double progression;
    public double length;

    public Path(Tile[] _path){
        path = _path;
        progression = 0;

        //On utilise la boucle pour créer les sous path et la longueur du parcours
        subs = new SubPath[path.length- 2];
        length = 0f;
        for (int i = 0; i < path.length - 2; i++){
            double angle = path[i+1].pos.AngleRad(path[i].pos,path[i+2].pos);
            if (Math.abs(angle - Math.PI/2f) < 0.1f)    //Si les 3 points sont alignés, créer un chemin droit
                subs[i] = new SubStraight(path[i].pos,path[i+1].pos,path[i+2].pos);
            else                                        //Sinon créer un arc
                subs[i] = new SubArc(path[i].pos,path[i+1].pos,path[i+2].pos);
            length += subs[i].Distance();
        }
    }
    //Récupère la position à une distance dis du début du chemin
    public Position GetPos(double dis){
        //On retourne simplement la position du cercle concerné à la distance relative dis
        for (SubPath sub : subs){
            if (dis <= sub.Distance()) return sub.GetPos(dis);
            else dis -= sub.Distance();
        }
        return path[path.length - 1].pos;
    }
    //On peut aussi utiliser la même fonction avec un temps et une vitesse
    public Position GetPos(double t, double spd){
        return GetPos(t*spd);
    }
}
