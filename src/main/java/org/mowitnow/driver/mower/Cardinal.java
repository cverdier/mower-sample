package org.mowitnow.driver.mower;

/**
 * Cardinal in 'NEWS' representation, with factors for movement along x and y axis
 */
public enum Cardinal {
	NORTH (0, 1),
	EAST (1, 0),
	SOUTH (0, -1),
	WEST (-1, 0);
	
	private int xFactor;
	private int yFactor;
	
	Cardinal(int xFactor, int yFactor) {
		this.xFactor = xFactor;
		this.yFactor = yFactor;
	}
	
	public int getxFactor() {
		return xFactor;
	}
	
	public int getyFactor() {
		return yFactor;
	}
	
	public Cardinal nextClockwise() {
		switch(this) {
		case NORTH: return EAST;
		case EAST: return SOUTH;
		case SOUTH: return WEST;
		case WEST: return NORTH;
		}
		return null; // TODO error management
	}
	
	public Cardinal nextCounterClockwise() {
		switch(this) {
		case NORTH: return WEST;
		case EAST: return NORTH;
		case SOUTH: return EAST;
		case WEST: return SOUTH;
		}
		return null; // TODO error management
	}
}
