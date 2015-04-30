package org.mowitnow.driver.driver;

import org.mowitnow.driver.action.exception.CollisionException;
import org.mowitnow.driver.action.exception.OutOfBoundariesException;
import org.mowitnow.driver.mower.Mower;

/**
 * Interface to represent the mower controller driver
 */
public interface Driver {

	/**
	 * Setup the driver's constraints
	 * @param maxX the x coordinate of the top-right tile
	 * @param maxY the y coordinate of the top-right tile
	 */
	public void setup(int maxX, int maxY);
	
	/**
	 * Add a mower to be managed by the driver
	 * @param mower the mower to add
	 * @throws CollisionException if the mower's coordinates collide with a mower managed by the driver
	 * @throws OutOfBoundariesException if the mower's coordinates are out of the boundaries defined by the driver's constraints
	 */
	public void addManagedMower(Mower mower) throws CollisionException, OutOfBoundariesException;
	
	/**
	 * Checks a set of x,y coordinates against the driver's constraints and managed mowers
	 * @param xPosition the x coordinate
	 * @param yPosition the y coordinate
	 * @throws CollisionException if the coordinates collide with a mower managed by the driver
	 * @throws OutOfBoundariesException if the coordinates are out of the boundaries defined by the driver's constraints
	 */
	public void checkPosition(int xPosition, int yPosition) throws CollisionException, OutOfBoundariesException;
	
	/**
	 * Runs the driver's controlled mowers
	 */
	public void run();
}
