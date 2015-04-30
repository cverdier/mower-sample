package org.mowitnow.driver.driver;

import org.mowitnow.driver.action.Action;
import org.mowitnow.driver.mower.Mower;
import org.mowitnow.driver.rendition.RenderService;

/**
 * Driver that runs each set of actions for each mowers sequentially
 */
public class SequentialDriver extends AbstractTerrainMowersDriver {
	
	private RenderService renderService;
	
	public SequentialDriver(RenderService renderService) {
		super();
		this.renderService = renderService;
	}
	
	public SequentialDriver(int maxX, int maxY, RenderService renderService) {
		super(maxX, maxY);
		this.renderService = renderService;
	}
	
	@Override
	public void run() {
		for (Mower managedMower : this.getManagedMowers()) {
			for (Action action : managedMower.getActions()) {
				action.execute(this, managedMower);				
			}
			renderService.render(this, managedMower);
		}
	}
}
