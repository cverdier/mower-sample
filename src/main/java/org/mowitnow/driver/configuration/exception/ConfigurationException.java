package org.mowitnow.driver.configuration.exception;

/**
 * Exception to indicate and trace errors during Driver's configuration
 */
public class ConfigurationException extends Exception {

	private static final long serialVersionUID = -2884362215925331945L;

	public ConfigurationException(String message) {
		super(message);
	}
	
	public ConfigurationException(Exception e) {
		super(e);
	}
}
