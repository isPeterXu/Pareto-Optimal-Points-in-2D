
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * T(n) = T(a * n) + o(n) -> O(n logn) since 0<a<1
 *
 *
 */

public class solution2 {

    public static java.util.List<Point> getPoints(List<Point> points){

        List<Point> set = new ArrayList<>();
        Point paretoPoint;

        if(points.size() < 1){
            return set;
        }
        if(points.size() == 1){
            set.addAll(points);
            return set;
        }

        // Find the Highest point which must be a Pareto-optimal point in O(n)
        paretoPoint = new Point(0, 0);
        for(int i = 0; i < points.size(); i++)
        {
            double yMax;
            yMax = paretoPoint.getY();
            if(points.get(i).getY() >= yMax){
                if(points.get(i).getY() > yMax){
                    paretoPoint = points.get(i);
                }
                else{
                    if(points.get(i).getX() < paretoPoint.getX())
                        paretoPoint = points.get(i);
                }
            }
        }

        set.add(paretoPoint);

        // Divided into two parts in O(n)
        List<Point> p1 = new ArrayList<>();
        for(int j = 0; j < points.size(); j++)
        {
            if(points.get(j).getX() > paretoPoint.getX())
                p1.add(points.get(j));
        }

        //Conquer
        set.addAll(solution2.getPoints(p1));

        //Merge
        return set;
    }
}
