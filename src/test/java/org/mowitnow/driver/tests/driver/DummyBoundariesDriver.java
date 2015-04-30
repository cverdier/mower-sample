package org.mowitnow.driver.tests.driver;

import org.mowitnow.driver.action.exception.CollisionException;
import org.mowitnow.driver.action.exception.OutOfBoundariesException;

/**
 * Dummy Driver implementation that throws a OutOfBoundariesException for the checkPosition method
 */
public class DummyBoundariesDriver extends DummyDriver {

	@Override
	public void checkPosition(int xPosition, int yPosition)
			throws CollisionException, OutOfBoundariesException {
		throw new OutOfBoundariesException();
	}
}
