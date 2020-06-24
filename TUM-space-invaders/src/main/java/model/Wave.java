package model;

import javafx.geometry.Dimension2D;

import java.util.*;

public class Wave {
    //TODO: Wave Stuff
	private static int spaceBetweenEnemies = 10, screenBorder = 5;
    private int width, height, speedX, speedY, difficulty;
    private Dimension2D screenSize;
    List<EnemyShip> listOfEnemies;

    public Wave(int width, int height, int speedX, int speedY, int difficulty, Dimension2D screenSize) {
        this.width = width;
        this.height = height;
        this.speedX = speedX;
        this.speedY = speedY;
        this.difficulty = difficulty;
        this.screenSize = screenSize;

		listOfEnemies = new ArrayList<>();

		/*
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				listOfEnemies.add(new SimpleEnemy(
						(i * (SimpleEnemy.DEFAULT_SIZE.getWidth() + spaceBetweenEnemies)),
						(j * (SimpleEnemy.DEFAULT_SIZE.getHeight() + spaceBetweenEnemies))
				));
			}
		}
		 */
    }

	/**
	 * Constructs a wave object from a formatted string and screen size.
	 * @param s format: height, width, speedX, speedY, difficulty, grid of enemies
	 * @param screenSize a Dimension2D object containing screenSize information
	 * @return the constructed wave object
	 */
    public static Wave parseWaveInfo(String s, Dimension2D screenSize) {
		String[] args = s.replaceAll("\\s","").split(",");
		if(args.length != 6) throw new IllegalArgumentException("String format is wrong!");
		int width = Integer.parseInt(args[1]), height = Integer.parseInt(args[0]);
		if(args[5].length() != width * height) throw new IllegalArgumentException("Enemy Ship grid dimensions are wrong!");
		PrimitiveIterator.OfInt enemyClassInfo = args[5].chars().iterator();


		Wave wave = new Wave(width,
				height,
				Integer.parseInt(args[2]),
				Integer.parseInt(args[3]),
				Integer.parseInt(args[4]),
				screenSize);


		ArrayList<EnemyShip> listOfEnemies = new ArrayList<>();
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				switch(enemyClassInfo.next()) {
					case (int) 'S':
						listOfEnemies.add(new SimpleEnemy(
								(i * (SimpleEnemy.DEFAULT_SIZE.getWidth() + spaceBetweenEnemies)),
								(j * (SimpleEnemy.DEFAULT_SIZE.getHeight() + spaceBetweenEnemies))
						));
						break;
					default:
						break;
				}
			}
		}
		wave.listOfEnemies = listOfEnemies;

		return wave;
	}

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

	public int getDifficulty() {
		return difficulty;
	}

	public List<EnemyShip> getListOfEnemies() {
        return listOfEnemies;
    }

    /**
     * @param index index of the EnemyShip
     * @return grid coordinate pair (x, y)
     */
    private int[] indexToGridCoordinate(int index) {
        int x = index % width;
        int y = index / width;

        return new int[]{x, y};
    }

    private int getX(int index) {
        return index % width;
    }

    private int getY(int index) {
        return index / width;
    }

    private int getRightMostEnemyIndex() {

        int rightMost = -1;
		for (int i = 0; i < listOfEnemies.size(); i++) {
			EnemyShip current = listOfEnemies.get(i);

			if(current.isAlive()) {
				if(rightMost < 0) {
					rightMost = i;
				} else if(getX(i) > getX(rightMost)) {
					rightMost = i;
				}
			}
		}
		return rightMost;
    }

    private Optional<EnemyShip> getRightMostEnemy() {
    	int i = getRightMostEnemyIndex();
    	if(i < 0)
    		return Optional.empty();
    	else
    		return Optional.of(listOfEnemies.get(i));
	}

	private int getLeftMostEnemyIndex() {

		int leftMost = -1;
		for (int i = 0; i < listOfEnemies.size(); i++) {
			EnemyShip current = listOfEnemies.get(i);

			if(current.isAlive()) {
				if(leftMost < 0) {
					leftMost = i;
				} else if(getX(i) < getX(leftMost)) {
					leftMost = i;
				}
			}
		}
		return leftMost;
	}

	private Optional<EnemyShip> getLeftMostEnemy() {
		int i = getLeftMostEnemyIndex();
		if(i < 0)
			return Optional.empty();
		else
			return Optional.of(listOfEnemies.get(i));
	}

	public void update() {
    	//TODO shooting
    	if(waveFinished())
    		return;

    	int tempSpeedY = 0;
		//if moving right
    	if(speedX > 0) {
			EnemyShip rightMost = getRightMostEnemy().orElseThrow(() -> new IllegalStateException("No rightmost enemy present!"));
			if(rightMost.getPos().getX() + rightMost.dimension.getWidth() + screenBorder > screenSize.getWidth()) {
				speedX = -speedX;
				tempSpeedY = speedY;
			}
    	} else {
			EnemyShip leftMost = getLeftMostEnemy().orElseThrow(() -> new IllegalStateException("No leftmost enemy present!"));
			if(leftMost.getPos().getX() < screenBorder) {
				speedX = -speedX;
				tempSpeedY = speedY;
			}
		}

		//Move all enemies
		for (EnemyShip enemyShip : listOfEnemies) {
			if(enemyShip.isAlive()) {
				enemyShip.move(speedX, tempSpeedY);
			}
		}
	}

	public boolean waveFinished() {
    	for(EnemyShip e : listOfEnemies) {
    		if(e.isAlive())
    			return false;
		}
    	return true;
	}

}
