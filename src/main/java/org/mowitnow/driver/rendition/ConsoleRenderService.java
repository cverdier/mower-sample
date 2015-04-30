package org.mowitnow.driver.rendition;

import org.mowitnow.driver.driver.Driver;
import org.mowitnow.driver.mower.Mower;

/**
 * RenderService implementation that renders mower position informations in the system console
 */
public class ConsoleRenderService implements RenderService {

	@Override
	public void render(Driver driver, Mower mower) {
		System.out.println(mower.getxPosition()
			+ " " + mower.getyPosition()
			+ " " + mower.getOrientation());
	}
}
