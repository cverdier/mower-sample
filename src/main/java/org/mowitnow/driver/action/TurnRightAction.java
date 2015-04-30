package org.mowitnow.driver.action;

import org.mowitnow.driver.driver.Driver;
import org.mowitnow.driver.mower.Mower;

/**
 * Action implementation that turns a mower to its right, clockwise
 */
public class TurnRightAction implements Action {

	@Override
	public void execute(Driver driver, Mower mower) {
		mower.setOrientation(mower.getOrientation().nextClockwise());
		// No collision or boundaries exception
	}
}
