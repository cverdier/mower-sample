package org.mowitnow.driver.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mowitnow.driver.action.Action;
import org.mowitnow.driver.action.ForwardAction;
import org.mowitnow.driver.action.TurnLeftAction;
import org.mowitnow.driver.action.TurnRightAction;
import org.mowitnow.driver.driver.Driver;
import org.mowitnow.driver.mower.Cardinal;
import org.mowitnow.driver.mower.Mower;
import org.mowitnow.driver.tests.driver.DummyBoundariesDriver;
import org.mowitnow.driver.tests.driver.DummyCollisionDriver;
import org.mowitnow.driver.tests.driver.DummyDriver;

/**
 * Unit test set for Actions
 * Atomic execution of each action implementation
 */
public class ActionTest {

	private Driver dummyDriver;
	private Driver dummyCollisionDriver;
	private Driver dummyOutOfBoundariesDriver;
	
	@Before
	public void init() {
		dummyDriver = new DummyDriver();
		dummyCollisionDriver = new DummyCollisionDriver();
		dummyOutOfBoundariesDriver = new DummyBoundariesDriver();
	}
	
	@Test
	public void forwardNorthTest() {
		Mower mower = new Mower(1, 1, Cardinal.NORTH);
		Action action = new ForwardAction();
		action.execute(dummyDriver, mower);
		Assert.assertEquals(1, mower.getxPosition());
		Assert.assertEquals(2, mower.getyPosition());
		Assert.assertEquals(Cardinal.NORTH, mower.getOrientation());
	}
	
	@Test
	public void forwardEastTest() {
		Mower mower = new Mower(1, 1, Cardinal.EAST);
		Action action = new ForwardAction();
		action.execute(dummyDriver, mower);
		Assert.assertEquals(2, mower.getxPosition());
		Assert.assertEquals(1, mower.getyPosition());
		Assert.assertEquals(Cardinal.EAST, mower.getOrientation());
	}
	
	@Test
	public void forwardSouthTest() {
		Mower mower = new Mower(1, 1, Cardinal.SOUTH);
		Action action = new ForwardAction();
		action.execute(dummyDriver, mower);
		Assert.assertEquals(1, mower.getxPosition());
		Assert.assertEquals(0, mower.getyPosition());
		Assert.assertEquals(Cardinal.SOUTH, mower.getOrientation());
	}
	
	@Test
	public void forwardWestTest() {
		Mower mower = new Mower(1, 1, Cardinal.WEST);
		Action action = new ForwardAction();
		action.execute(dummyDriver, mower);
		Assert.assertEquals(0, mower.getxPosition());
		Assert.assertEquals(1, mower.getyPosition());
		Assert.assertEquals(Cardinal.WEST, mower.getOrientation());
	}
	
	@Test
	public void forwardCollisionTest() {
		Mower mower = new Mower(1, 1, Cardinal.NORTH);
		Action action = new ForwardAction();
		action.execute(dummyCollisionDriver, mower);
		Assert.assertEquals(1, mower.getxPosition());
		Assert.assertEquals(1, mower.getyPosition());
		Assert.assertEquals(Cardinal.NORTH, mower.getOrientation());
	}
	
	@Test
	public void forwardOutOfBoundariesTest() {
		Mower mower = new Mower(1, 1, Cardinal.NORTH);
		Action action = new ForwardAction();
		action.execute(dummyOutOfBoundariesDriver, mower);
		Assert.assertEquals(1, mower.getxPosition());
		Assert.assertEquals(1, mower.getyPosition());
		Assert.assertEquals(Cardinal.NORTH, mower.getOrientation());
	}
	
	@Test
	public void turnLeftTest() {
		Mower mower = new Mower(1, 1, Cardinal.NORTH);
		Action action = new TurnLeftAction();
		action.execute(dummyDriver, mower);
		Assert.assertEquals(1, mower.getxPosition());
		Assert.assertEquals(1, mower.getyPosition());
		Assert.assertEquals(Cardinal.WEST, mower.getOrientation());
	}
	
	@Test
	public void turnLeftTwiceTest() {
		Mower mower = new Mower(1, 1, Cardinal.NORTH);
		Action action1 = new TurnLeftAction();
		Action action2 = new TurnLeftAction();
		action1.execute(dummyDriver, mower);
		action2.execute(dummyDriver, mower);
		Assert.assertEquals(1, mower.getxPosition());
		Assert.assertEquals(1, mower.getyPosition());
		Assert.assertEquals(Cardinal.SOUTH, mower.getOrientation());
	}
	
	@Test
	public void turnRightTest() {
		Mower mower = new Mower(1, 1, Cardinal.NORTH);
		Action action = new TurnRightAction();
		action.execute(dummyDriver, mower);
		Assert.assertEquals(1, mower.getxPosition());
		Assert.assertEquals(1, mower.getyPosition());
		Assert.assertEquals(Cardinal.EAST, mower.getOrientation());
	}
	
	@Test
	public void turnRightTwiceTest() {
		Mower mower = new Mower(1, 1, Cardinal.NORTH);
		Action action1 = new TurnRightAction();
		Action action2 = new TurnRightAction();
		action1.execute(dummyDriver, mower);
		action2.execute(dummyDriver, mower);
		Assert.assertEquals(1, mower.getxPosition());
		Assert.assertEquals(1, mower.getyPosition());
		Assert.assertEquals(Cardinal.SOUTH, mower.getOrientation());
	}
}
