package org.mowitnow.driver.action;

import org.mowitnow.driver.driver.Driver;
import org.mowitnow.driver.mower.Mower;

/**
 * Interface for executable atomic mower action
 */
public interface Action {

	/**
	 * Executes the action for a mower managed by driver
	 * @param driver the Driver
	 * @param mower the Mower
	 */
	public void execute(Driver driver, Mower mower);
}
