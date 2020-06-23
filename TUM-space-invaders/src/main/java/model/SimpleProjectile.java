package model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class SimpleProjectile extends Projectile {
    public static Dimension2D DEFAULT_SIZE = new Dimension2D(16, 16);
    public static String IMAGE_NAME = "projectile";

    public SimpleProjectile(Point2D position, double speedY) {
        super();
        this.dimension = DEFAULT_SIZE;
        image = IMAGE_NAME;
        this.pos = position;
        this.speedY = speedY;
        this.alive = true;
    }

    @Override
    public void update() {
        this.pos = pos.add(0, speedY);
    }
}
