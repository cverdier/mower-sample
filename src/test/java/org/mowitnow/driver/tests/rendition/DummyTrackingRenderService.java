package org.mowitnow.driver.tests.rendition;

import java.util.LinkedList;
import java.util.List;

import org.mowitnow.driver.driver.Driver;
import org.mowitnow.driver.mower.Mower;
import org.mowitnow.driver.rendition.RenderService;

/**
 * Dummy RenderService implementation that stacks mowers in a list for each call
 */
public class DummyTrackingRenderService implements RenderService {

	private List<Mower> renderedMowers;
	
	public DummyTrackingRenderService() {
		this.renderedMowers = new LinkedList<Mower>();
	}
	
	@Override
	public void render(Driver driver, Mower mower) {
		renderedMowers.add(mower);
	}

	public List<Mower> getRenderedMowers() {
		return renderedMowers;
	}
}
