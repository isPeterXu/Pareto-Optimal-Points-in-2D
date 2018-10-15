import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * in O(n)
 * Given a set of points sorted by x coordinate, we iterate those points from right to left
 * Include every point whose y-value is higher than previous yMax, which takes O(n)
 */

public class solution4 {

    public static java.util.List<Point> getPoints(java.util.List<Point> points){

        List<Point> set = new ArrayList<>();
        double yMax = -1;
        for(int i = 0; i < points.size(); i++)
        {
            if(points.get(i).getY() >= yMax){
                set.add(points.get(i));
                yMax = points.get(i).getY();
            }
        }
        return set;
    }
}
