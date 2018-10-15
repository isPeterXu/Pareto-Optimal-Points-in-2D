
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Divide & Conquer in O(n logh)
 * T(n) = T(a * n) + 2 * O(n) -> O(n logh)
 */

public class solution3 {
    public static java.util.List<Point> getPoints(List<Point> points){

        List<Point> set = new ArrayList<>();
        Point paretoPoint;
        double xMax = -1;
        double xMin = 10000;
        double xMid;

        if(points.size() < 1){
            return set;
        }
        if(points.size() == 1){
            set.addAll(points);
            return set;
        }

        /**
         * Find the median
         * O(n)
         */
        for(int i = 0; i < points.size(); i++)
        {
            if(points.get(i).getX() >= xMax) xMax = points.get(i).getX();
            if(points.get(i).getX() <= xMin) xMin = points.get(i).getX();
        }
        xMid = (xMax + xMin)/2;

        paretoPoint = new Point(-1, -1);

        /**
         * Find out the Pareto-optimal point right of median
         * O(n)
         */
        for(int i = 0; i < points.size(); i++)
        {
            double yMax = paretoPoint.getY();
            if(points.get(i).getX() == xMid){
                if(points.get(i).getY() >= yMax) paretoPoint = points.get(i);
            }

            if(points.get(i).getX() > xMid && points.get(i).getY() >= yMax) {
                if(points.get(i).getY() > yMax) {
                    paretoPoint = points.get(i);
                }
                else if(points.get(i).getY() == yMax) {
                    if (points.get(i).getX() < paretoPoint.getX()) paretoPoint = points.get(i);
                }
            }
        }
        set.add(paretoPoint); // add this pareto-optimal point into set

        /**
         * Divide
         * but only keep lef-top and right-bottom zones
         * O(n)
         */
        List<Point> p1 = new ArrayList<>();
        List<Point> p2 = new ArrayList<>();
        for(int j = 0; j < points.size(); j++)
        {
            if(points.get(j).getX() < paretoPoint.getX() && points.get(j).getY() > paretoPoint.getY())
                p1.add(points.get(j));
            else if(points.get(j).getX() > paretoPoint.getX() && points.get(j).getY() < paretoPoint.getY())
                p2.add(points.get(j));
        }

        //Conquer
            set.addAll(solution3.getPoints(p1)); // left-top part
            set.addAll(solution3.getPoints(p2)); // right-bottom part

        //Merge
        return set;
    }
}
