package org.mowitnow.driver.tests.driver;

import org.mowitnow.driver.action.exception.CollisionException;
import org.mowitnow.driver.action.exception.OutOfBoundariesException;
import org.mowitnow.driver.driver.Driver;
import org.mowitnow.driver.mower.Mower;

/**
 * Base dummy Driver implementation that does nothing 
 */
public class DummyDriver implements Driver {

	@Override
	public void setup(int maxX, int maxY) {
		
	}
	
	@Override
	public void addManagedMower(Mower mower) throws CollisionException,
			OutOfBoundariesException {
	}

	@Override
	public void checkPosition(int xPosition, int yPosition)
			throws CollisionException, OutOfBoundariesException {
	}

	@Override
	public void run() {
	}
}
