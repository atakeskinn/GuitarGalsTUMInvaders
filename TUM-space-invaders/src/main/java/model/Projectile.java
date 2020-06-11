package model;

import java.awt.geom.Point2D;

public abstract class Projectile {
	private Point2D pos;
	private double speedY;

	public Point2D getPos() {
		return pos;
	}

	public void setPos(Point2D pos) {
		this.pos = pos;
	}

}
