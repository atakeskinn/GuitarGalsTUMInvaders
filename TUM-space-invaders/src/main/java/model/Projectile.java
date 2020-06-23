package model;

import javafx.geometry.Dimension2D;

import javafx.geometry.Point2D;

public abstract class Projectile {
	protected Point2D pos;
	protected Dimension2D dimension;
	protected double speedY;
	protected boolean alive;
	protected String image;

	public Point2D getPos() {
		return pos;
	}

	public void setPos(Point2D pos) {
		this.pos = pos;
	}

	public Dimension2D getDimension() {
		return dimension;
	}

	public double getSpeedY() {
		return speedY;
	}

	public String getImage() {return image;}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public abstract void update();
}
