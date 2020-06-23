package model;

import javafx.geometry.Dimension2D;

import javafx.geometry.Point2D;

public class SimpleEnemy extends EnemyShip {
    public static Dimension2D DEFAULT_SIZE = new Dimension2D(16 /2 * 3, 20 /2 * 3);
    public static String IMAGE_NAME = "simple";

    public SimpleEnemy(double x, double y) {
        pos = new Point2D(x, y);
        dimension = DEFAULT_SIZE;
        image = IMAGE_NAME;
        alive = true;
    }

    @Override
    public void update() {

    }
}
