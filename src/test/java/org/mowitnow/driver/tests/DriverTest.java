package org.mowitnow.driver.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mowitnow.driver.action.ForwardAction;
import org.mowitnow.driver.action.TurnRightAction;
import org.mowitnow.driver.action.exception.CollisionException;
import org.mowitnow.driver.action.exception.OutOfBoundariesException;
import org.mowitnow.driver.driver.Driver;
import org.mowitnow.driver.driver.SequentialDriver;
import org.mowitnow.driver.mower.Cardinal;
import org.mowitnow.driver.mower.Mower;
import org.mowitnow.driver.rendition.RenderService;
import org.mowitnow.driver.rendition.VoidRenderService;
import org.mowitnow.driver.tests.rendition.DummyTrackingRenderService;

/**
 * Unit test set for Driver, SequentialDriver implementation
 * Managed mowers and coordinates check
 * Sequential execution of actions for a mower
 * Sequential execution of mower's action sets
 */
public class DriverTest {

	private RenderService renderService;
	
	@Rule
	public ExpectedException collisionExceptation = ExpectedException.none();
	
	@Rule
	public ExpectedException outOfBoundariesExceptation = ExpectedException.none();
	
	@Before
	public void init() {
		this.renderService = new VoidRenderService();
	}
	
	@Test
	public void addCorrectMowersTest() throws CollisionException, OutOfBoundariesException {
		Driver driver = new SequentialDriver(1, 1, renderService);
		Mower mower1 = new Mower(0, 0, Cardinal.NORTH);
		Mower mower2 = new Mower(1, 0, Cardinal.NORTH);
		driver.addManagedMower(mower1);
		driver.addManagedMower(mower2);
	}
	
	@Test
	public void addCollidingMowersTest() throws CollisionException, OutOfBoundariesException {
		Driver driver = new SequentialDriver(1, 1, renderService);
		Mower mower1 = new Mower(0, 0, Cardinal.NORTH);
		Mower mower2 = new Mower(0, 0, Cardinal.NORTH);
		driver.addManagedMower(mower1);
		
		collisionExceptation.expect(CollisionException.class);
		driver.addManagedMower(mower2);
	}
	
	@Test
	public void addOutOfBoundariesMowerTest() throws CollisionException, OutOfBoundariesException {
		Driver driver = new SequentialDriver(1, 1, renderService);
		Mower mower = new Mower(1, 2, Cardinal.NORTH);
		
		outOfBoundariesExceptation.expect(OutOfBoundariesException.class);
		driver.addManagedMower(mower);
	}
	
	@Test
	public void checkEmptyPositionTest() throws CollisionException, OutOfBoundariesException {
		Driver driver = new SequentialDriver(1, 1, renderService);
		driver.checkPosition(0, 0);
	}
	
	@Test
	public void checkValidPositionTest() throws CollisionException, OutOfBoundariesException {
		Driver driver = new SequentialDriver(1, 1, renderService);
		Mower mower = new Mower(0, 0, Cardinal.NORTH);
		driver.addManagedMower(mower);
		driver.checkPosition(0, 1);
	}
	
	@Test
	public void checkCollidingPositionTest() throws CollisionException, OutOfBoundariesException {
		Driver driver = new SequentialDriver(1, 1, renderService);
		Mower mower = new Mower(0, 1, Cardinal.NORTH);
		driver.addManagedMower(mower);
		
		collisionExceptation.expect(CollisionException.class);
		driver.checkPosition(0, 1);
	}
	
	@Test
	public void checkOutOfBoundariesPositionTest() throws CollisionException, OutOfBoundariesException {
		Driver driver = new SequentialDriver(1, 1, renderService);
		
		outOfBoundariesExceptation.expect(OutOfBoundariesException.class);
		driver.checkPosition(0, 2);
	}
	
	@Test
	public void singleMowerSequentialActionsRunTest() throws CollisionException, OutOfBoundariesException {
		Driver driver = new SequentialDriver(2, 1, renderService);
		Mower mower = new Mower(0, 0, Cardinal.NORTH);
		mower.getActions().add(new ForwardAction());
		mower.getActions().add(new TurnRightAction());
		mower.getActions().add(new ForwardAction());
		mower.getActions().add(new ForwardAction());
		driver.addManagedMower(mower);
		driver.run();
		Assert.assertEquals(2, mower.getxPosition());
		Assert.assertEquals(1, mower.getyPosition());
		Assert.assertEquals(Cardinal.EAST, mower.getOrientation());
	}
	
	@Test
	public void multipleMowersSequentialActionRunTest() throws CollisionException, OutOfBoundariesException {
		DummyTrackingRenderService trackingRenderService = new DummyTrackingRenderService();
		Driver driver = new SequentialDriver(1, 1, trackingRenderService);
		Mower mower0 = new Mower(0, 0, Cardinal.NORTH);
		Mower mower1 = new Mower(1, 0, Cardinal.NORTH);
		Mower mower2 = new Mower(0, 1, Cardinal.NORTH);
		driver.addManagedMower(mower0);
		driver.addManagedMower(mower1);
		driver.addManagedMower(mower2);
		driver.run();
		Assert.assertEquals(mower0, trackingRenderService.getRenderedMowers().get(0));
		Assert.assertEquals(mower1, trackingRenderService.getRenderedMowers().get(1));
		Assert.assertEquals(mower2, trackingRenderService.getRenderedMowers().get(2));
	}
}
