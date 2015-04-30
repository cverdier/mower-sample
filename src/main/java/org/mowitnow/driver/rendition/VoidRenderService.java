package org.mowitnow.driver.rendition;

import org.mowitnow.driver.driver.Driver;
import org.mowitnow.driver.mower.Mower;

/**
 * RenderService implementation that does not actually render
 */
public class VoidRenderService implements RenderService {

	@Override
	public void render(Driver driver, Mower mower) {
		// Does nothing
	}
}
