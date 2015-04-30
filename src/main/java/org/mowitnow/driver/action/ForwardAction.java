package org.mowitnow.driver.action;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.mowitnow.driver.action.exception.CollisionException;
import org.mowitnow.driver.action.exception.OutOfBoundariesException;
import org.mowitnow.driver.driver.Driver;
import org.mowitnow.driver.mower.Mower;

/**
 * Action implementation that moves a mower forward one tile, along its orientation
 * If calls the driver's checkPosition method to assert the movement is possible
 * If it is not, the action has no effect 
 */
public class ForwardAction implements Action {

	private static final Logger LOGGER = Logger.getLogger(ForwardAction.class.getName());
	
	@Override
	public void execute(Driver driver, Mower mower) {
		int nextX = mower.getxPosition() + mower.getOrientation().getxFactor();
		int nextY = mower.getyPosition() + mower.getOrientation().getyFactor();
		try {
			driver.checkPosition(nextX, nextY);
			mower.setxPosition(nextX);
			mower.setyPosition(nextY);
		}
		catch (CollisionException ce) {
			// No movement
			LOGGER.log(Level.FINE, "Collision at " + nextX + "," + nextY, ce);
		}
		catch (OutOfBoundariesException oe) {
			// No movement
			LOGGER.log(Level.FINE, "Out of boundaries at " + nextX + "," + nextY, oe);
		}
	}
}
