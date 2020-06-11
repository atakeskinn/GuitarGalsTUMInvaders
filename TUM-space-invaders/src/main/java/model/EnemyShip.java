package model;

import java.awt.geom.Point2D;

public abstract class EnemyShip {
	private Point2D pos;
	private int health;
	private boolean canShoot;
	private boolean alive;

	public abstract Point2D getPos();

	public abstract void setPos(Point2D pos);
}
