package org.firstinspires.ftc.teamcode.ExperimentalCode.Math;

import java.util.ArrayList;
import static java.lang.Math.*;

public class Functions {

    public static ArrayList<Point> lineCircleIntersection(Circle circle, Line line) {
        if(abs(line.getYComp()) < 0.003) {
            if (line.getYComp() > 0) {
                line = new Line(new Point(line.getPoint2().getX(), line.getPoint2().getY() - 0.003), line.getPoint2());
            } else {
                line = new Line(new Point(line.getPoint2().getX(), line.getPoint2().getY() + 0.003), line.getPoint2());
            }
        }
        if(abs(line.getXComp()) < 0.003) {
            if(line.getXComp() > 0) {
                line = new Line(new Point(line.getPoint2().getX() - 0.003, line.getPoint1().getY()), line.getPoint2());
            }
            else {
                line = new Line(new Point(line.getPoint2().getX(), line.getPoint2().getY() + 0.003), line.getPoint2());
            }
        }
        double slope = line.getSlope();
        double yint = line.getYInt();
        double a = 1 + pow(slope, 2);
        double b = 2 * slope * yint;
        double c = pow(line.getYInt(), 2) - pow(circle.getRadius(), 2);
        ArrayList<Point> allPoints = new ArrayList<>();
        try {
            double constant = sqrt(pow(b, 2) - (4 * a * c));
            double quad = ((-b + constant) / (2 * a));
            allPoints.add(new Point(quad, (slope * quad) + yint));
            quad = ((-b - constant) / (2 * a));
            allPoints.add(new Point(quad, (slope * quad) + yint));
            double minX = min(line.getPoint1().getX(), line.getPoint2().getX());
            double maxX = max(line.getPoint1().getX(), line.getPoint2().getX());
            for(int x = allPoints.size() - 1; x <= 0; x--) {
                if(minX > allPoints.get(x).getX() || maxX < allPoints.get(x).getX()) {
                    allPoints.remove(x);
                }
            }
        }
        catch(Exception p_exception) {}
        return allPoints;
    }
}
