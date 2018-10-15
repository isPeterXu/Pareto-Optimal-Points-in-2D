
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * in O(nh)
 * 1. Find the right most point as the first staircase we found
 * 2. Find the right most point among those points that are less than x and
 *    higher than y of last found Pareto-optimal point. Finding each point
 *    takes O(n), thus we can find all the Pareto-optimal points in O(nh)
 */
public class solution1 {

    public static List<Point> getPoints(List<Point> points){

        List<Point> set = new ArrayList<>();

        Point rightPoint = new Point(0,0);
        Point paretoPoint;

        // O(n)
        for (int j = 0; j < points.size(); j++)
        {
            if (points.get(j).getX() > rightPoint.getX()
                    || (points.get(j).getX() == rightPoint.getX() & points.get(j).getY() > rightPoint.getY()))
            {
                rightPoint = points.get(j);
            }
        }

        set.add(rightPoint);

        //O(nh), where h is the number of Pareto-optimal points
        outerLoop:
        for (int i = 0; i < points.size(); i++) // O(h)
        {

            paretoPoint = new Point(0, 0);

            for (int j = 0; j < points.size(); j++) // O(n)
            {
                if (points.get(j).getX() < rightPoint.getX())
                // right of last found Pareto-optimal point
                {
                    if (points.get(j).getX() > paretoPoint.getX() ||
                            (points.get(j).getX() == paretoPoint.getX() && points.get(j).getY() > paretoPoint.getY()))
                    // rightmost point
                    {
                        paretoPoint = points.get(j);
                    }
                }

            }

            rightPoint = paretoPoint;
            set.add(rightPoint);

            // Stop the outer loop until it finds all the Pareto-optimal points
            int a = 0;
            for (int k = 0; k < points.size(); k++)
            {
                if (points.get(k).getY() > rightPoint.getY()) a++;
            }
            if (a == 0) break outerLoop;
        }

        return set;
    }
}
