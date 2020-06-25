package model;

import javafx.geometry.Dimension2D;

import javafx.geometry.Point2D;

public abstract class EnemyShip {
	protected Point2D pos;
	protected Dimension2D dimension;
	protected int health;
	protected boolean canShoot;
	protected boolean alive;
	protected String image;
	protected int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Point2D getPos() {return pos;};

	public void setPos(Point2D pos) {this.pos = pos;};

	public Dimension2D getDimension() {return dimension;}

	public String getImage() {return image;}

	public void setImage(String image) {
		this.image = image;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean canShoot() {
		return canShoot;
	}

	public abstract void update();

	public void move(int x, int y) {pos = pos.add(x, y);}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
