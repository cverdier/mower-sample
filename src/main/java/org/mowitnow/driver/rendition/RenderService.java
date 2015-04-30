package org.mowitnow.driver.rendition;

import org.mowitnow.driver.driver.Driver;
import org.mowitnow.driver.mower.Mower;

/**
 * Interface to render information about a mower managed by a driver
 */
public interface RenderService {
	
	/**
	 * Renders the mower managed by the driver
	 * @param driver the driver
	 * @param mower the mower
	 */
	public void render(Driver driver, Mower mower);
}
