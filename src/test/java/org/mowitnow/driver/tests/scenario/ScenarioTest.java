package org.mowitnow.driver.tests.scenario;

import org.junit.Assert;
import org.junit.Test;
import org.mowitnow.driver.action.ForwardAction;
import org.mowitnow.driver.action.TurnLeftAction;
import org.mowitnow.driver.action.TurnRightAction;
import org.mowitnow.driver.action.exception.CollisionException;
import org.mowitnow.driver.action.exception.OutOfBoundariesException;
import org.mowitnow.driver.configuration.ConfigurationService;
import org.mowitnow.driver.configuration.FileConfigurationService;
import org.mowitnow.driver.configuration.exception.ConfigurationException;
import org.mowitnow.driver.driver.Driver;
import org.mowitnow.driver.driver.SequentialDriver;
import org.mowitnow.driver.mower.Cardinal;
import org.mowitnow.driver.mower.Mower;
import org.mowitnow.driver.rendition.ConsoleRenderService;
import org.mowitnow.driver.tests.rendition.DummyTrackingRenderService;

/**
 * Scenario tests cases of the fully built application
 */
public class ScenarioTest {

	private final static String SCENARIO_FILE_RESOURCE = "/scenario.txt";
	
	/**
	 * Test from hardcoded data
	 */
	@Test
	public void internalScenarioTest() throws CollisionException, OutOfBoundariesException {
		Driver testDriver = new SequentialDriver(5, 5, new ConsoleRenderService());
		Mower m1 = new Mower(1, 2, Cardinal.NORTH);
		m1.getActions().add(new TurnLeftAction());
		m1.getActions().add(new ForwardAction());
		m1.getActions().add(new TurnLeftAction());
		m1.getActions().add(new ForwardAction());
		m1.getActions().add(new TurnLeftAction());
		m1.getActions().add(new ForwardAction());
		m1.getActions().add(new TurnLeftAction());
		m1.getActions().add(new ForwardAction());
		m1.getActions().add(new ForwardAction());
		Mower m2 = new Mower(3, 3, Cardinal.EAST);
		m2.getActions().add(new ForwardAction());
		m2.getActions().add(new ForwardAction());
		m2.getActions().add(new TurnRightAction());
		m2.getActions().add(new ForwardAction());
		m2.getActions().add(new ForwardAction());
		m2.getActions().add(new TurnRightAction());
		m2.getActions().add(new ForwardAction());
		m2.getActions().add(new TurnRightAction());
		m2.getActions().add(new TurnRightAction());
		m2.getActions().add(new ForwardAction());
		
		testDriver.addManagedMower(m1);
		testDriver.addManagedMower(m2);
		testDriver.run();
		
		Assert.assertEquals(1, m1.getxPosition());
		Assert.assertEquals(3, m1.getyPosition());
		Assert.assertEquals(Cardinal.NORTH, m1.getOrientation());
		Assert.assertEquals(5, m2.getxPosition());
		Assert.assertEquals(1, m2.getyPosition());
		Assert.assertEquals(Cardinal.EAST, m2.getOrientation());
	}
	
	/**
	 * Test from a resource file
	 */
	@Test
	public void fileScenarioTest() throws ConfigurationException {
		String filePath = getClass().getResource(SCENARIO_FILE_RESOURCE).getPath();
		DummyTrackingRenderService trackingRenderService = new DummyTrackingRenderService();
		Driver driver = new SequentialDriver(trackingRenderService);
		ConfigurationService configurationService = new FileConfigurationService(filePath);
		configurationService.setupDriver(driver);
		driver.run();
		
		Mower m1 = trackingRenderService.getRenderedMowers().get(0);
		Mower m2 = trackingRenderService.getRenderedMowers().get(1);
		Assert.assertEquals(1, m1.getxPosition());
		Assert.assertEquals(3, m1.getyPosition());
		Assert.assertEquals(Cardinal.NORTH, m1.getOrientation());
		Assert.assertEquals(5, m2.getxPosition());
		Assert.assertEquals(1, m2.getyPosition());
		Assert.assertEquals(Cardinal.EAST, m2.getOrientation());
	}
}
