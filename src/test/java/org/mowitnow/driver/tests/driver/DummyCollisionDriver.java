package org.mowitnow.driver.tests.driver;

import org.mowitnow.driver.action.exception.CollisionException;
import org.mowitnow.driver.action.exception.OutOfBoundariesException;

/**
 * Dummy Driver implementation that throws a CollisionException for the checkPosition method
 */
public class DummyCollisionDriver extends DummyDriver {

	@Override
	public void checkPosition(int xPosition, int yPosition)
			throws CollisionException, OutOfBoundariesException {
		throw new CollisionException();
	}
}
