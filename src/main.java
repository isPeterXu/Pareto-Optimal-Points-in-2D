import java.util.*;
import java.awt.Point;
import static java.lang.Math.pow;
import static java.lang.Math.random;

public class main {
    public static void main(String[] args){
        /*
            1000 | ..
            800  | .   .
            600  |  .  . ..
            400  | . .  .   .
            200  | .    .    ..
            100  |  ..   . . . .
            0    '-----------------
                 0       600  1000

        　This instance is give under n = 22 and boundLength = 1000;
        */
        List<Point> points = new ArrayList();
        int n = 10; // The number of points
        int boundLength = 20; // The bound is set to be 10^5 in our experiments
        //Draw a set of n points
        for(int i = 0; i < n; ){
            int xValue = (int)((random()*boundLength));
            int yValue = (int)((random()*boundLength));
            //To show a graph with more pareto-optimal points
            //Let the point(x,y) falls in a quarter circle inside the first quadrant
            if (pow(xValue,2) + pow(yValue,2) <= pow(boundLength,2)
                    & (xValue > 0 && yValue > 0))
            {
                Point a = new Point(xValue,yValue);
                points.add(a);
                i++;
            }
        }

        for(Point e : points){
            System.out.println(e);
        }
        /**
         * O(n logh)
         */
        long startTime = System.currentTimeMillis();
        List<Point> ParetoPoints3 = solution3.getPoints(points);
        System.out.println("\nPareto-optimal points: ");
        long endTime = System.currentTimeMillis();
        for(Point e : ParetoPoints3) {
            System.out.println(e);
        }
        System.out.println("\nThe number of Pareto-optimal points is : " + ParetoPoints3.size());
        System.out.println("Elapsed time: " + (endTime - startTime) + "ms");
    }

}
