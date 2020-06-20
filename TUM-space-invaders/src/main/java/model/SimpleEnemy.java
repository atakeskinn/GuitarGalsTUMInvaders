package model;

import javafx.geometry.Dimension2D;

import java.awt.geom.Point2D;

public class SimpleEnemy extends EnemyShip {
    public static Dimension2D DEFAULT_SIZE = new Dimension2D(16 /2 * 3, 20 /2 * 3);

    public SimpleEnemy(double x, double y) {
        pos = new Point2D.Double(x, y);
        dimension = DEFAULT_SIZE;
        image = "simple";
        alive = true;
    }

    @Override
    public void update() {

    }
}
