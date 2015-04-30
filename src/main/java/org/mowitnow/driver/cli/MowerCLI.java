package org.mowitnow.driver.cli;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.mowitnow.driver.configuration.ConfigurationService;
import org.mowitnow.driver.configuration.FileConfigurationService;
import org.mowitnow.driver.configuration.exception.ConfigurationException;
import org.mowitnow.driver.driver.Driver;
import org.mowitnow.driver.driver.SequentialDriver;
import org.mowitnow.driver.rendition.ConsoleRenderService;

/**
 * Command Line Interface providing class
 */
public class MowerCLI {

	private static final Logger LOGGER = Logger.getLogger(MowerCLI.class.getName());
	
	/**
	 * Executable method
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
		if (args.length == 0 || args.length > 2 || (! "-f".equals(args[0]))) {
			printUsage();
			return;
		}
		
		try {
			Driver driver = new SequentialDriver(new ConsoleRenderService());
			configureWithFile(driver, args[1]);
			driver.run();
		}
		catch (ConfigurationException ce) {
			LOGGER.log(Level.SEVERE, "Failed to configure from file", ce);
		}
		catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Unhandeld Exception", e);
		}
	}
	
	public static void configureWithFile(Driver driver, String filePath) throws ConfigurationException {
		ConfigurationService configurationService = new FileConfigurationService(filePath);
		configurationService.setupDriver(driver);
		
	}
	
	public static void printUsage() {
		System.out.println("Usage : java -jar mower-driver-0.1-SNAPSHOT.jar -f path/to/file");
	}
}
