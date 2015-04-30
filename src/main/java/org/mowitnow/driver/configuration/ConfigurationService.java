package org.mowitnow.driver.configuration;

import org.mowitnow.driver.configuration.exception.ConfigurationException;
import org.mowitnow.driver.driver.Driver;

/**
 * Interface for a configuration system for a Driver
 */
public interface ConfigurationService {
	
	/**
	 * Setups the drive form the configuration
	 * @param driver the driver to setup
	 * @throws ConfigurationException if an error occurs during setup
	 */
	public void setupDriver(Driver driver) throws ConfigurationException;
}
