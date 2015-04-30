package org.mowitnow.driver.driver;

import java.util.LinkedList;
import java.util.List;

import org.mowitnow.driver.action.exception.CollisionException;
import org.mowitnow.driver.action.exception.OutOfBoundariesException;
import org.mowitnow.driver.mower.Mower;

/**
 * Base class to hold the driver's tile constraints and list of managed mowers
 */
public abstract class AbstractTerrainMowersDriver implements Driver {

	private static final int MINX = 0;
	private static final int MINY = 0;
	private int maxX;
	private int maxY;
	private List<Mower> managedMowers = new LinkedList<Mower>();
	
	public AbstractTerrainMowersDriver() {
	}
	
	public AbstractTerrainMowersDriver(int maxX, int maxY) {
		this.setup(maxX, maxY);
	}
	
	@Override
	public void setup(int maxX, int maxY) {
		this.maxX= maxX;
		this.maxY = maxY;
	}
	
	@Override
	public void addManagedMower(Mower mower) throws CollisionException, OutOfBoundariesException {
		checkPosition(mower.getxPosition(), mower.getyPosition());
		managedMowers.add(mower);
	}
	
	@Override
	public void checkPosition(int xPosition, int yPosition)
			throws CollisionException, OutOfBoundariesException {
		// Terrain boundaries
		if (xPosition < MINX || xPosition > maxX || yPosition < MINY || yPosition > maxY) {
			throw new OutOfBoundariesException();
		}
		// Mowers collision
		for (Mower managedMower : managedMowers) {
			if (xPosition == managedMower.getxPosition() && yPosition == managedMower.getyPosition()) {
				throw new CollisionException();
			}
		}
	}
	
	protected List<Mower> getManagedMowers() {
		return managedMowers;
	}
}
