package org.mowitnow.driver.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mowitnow.driver.action.ForwardAction;
import org.mowitnow.driver.action.TurnLeftAction;
import org.mowitnow.driver.action.TurnRightAction;
import org.mowitnow.driver.action.exception.CollisionException;
import org.mowitnow.driver.action.exception.OutOfBoundariesException;
import org.mowitnow.driver.configuration.exception.ConfigurationException;
import org.mowitnow.driver.driver.Driver;
import org.mowitnow.driver.mower.Cardinal;
import org.mowitnow.driver.mower.Mower;

/**
 * ConfigurationService implementation using a file to configure drivers
 * The constructor requires a path to the file
 * The file structure is fixed
 */
public class FileConfigurationService implements ConfigurationService {

	private static final String PATTERN_DRIVER = "^([0-9]+) +([0-9]+) *$";
	private static final String PATTERN_MOWER_START = "^([0-9]+) +([0-9]+) +([NEWS]) *$";
	private static final String PATTERN_MOWER_COMMAND = "^([AGD]+) *$";
	private static final char CARDINAL_NORTH = 'N';
	private static final char CARDINAL_EAST = 'E';
	private static final char CARDINAL_SOUTH = 'S';
	private static final char CARDINAL_WEST = 'W';
	private static final char ACTION_FORWARD = 'A';
	private static final char ACTION_TURN_LEFT = 'G';
	private static final char ACTION_TURN_RIGHT = 'D';
	
	private String filePath;
	private Pattern driverPattern;
	private Pattern mowerStartPattern;
	private Pattern mowerCommandPattern;
	
	public FileConfigurationService(String filePath) {
		this.filePath = filePath;
		this.driverPattern = Pattern.compile(PATTERN_DRIVER);
		this.mowerStartPattern = Pattern.compile(PATTERN_MOWER_START);
		this.mowerCommandPattern = Pattern.compile(PATTERN_MOWER_COMMAND);
	}
	
	@Override
	public void setupDriver(Driver driver) throws ConfigurationException {
		try {
			File file = new File(filePath);
			BufferedReader br = new BufferedReader(new FileReader(file));
			 
			try {
				String line = br.readLine();
				processDriverLine(driver, line);
				
				while ((line = br.readLine()) != null) {
					while (line.trim().isEmpty()) {
						line = br.readLine();
					}
					String startLine = line;
					line = br.readLine();
					processMowerLines(driver, startLine, line);
				}
			}
			catch (IOException readException) {
				throw new ConfigurationException(readException);
			}
			catch (ConfigurationException configurationException) {
				throw configurationException;
			}
			finally {
				br.close();
			}
		}
		catch (IOException fileException) {
			throw new ConfigurationException(fileException);
		}
	}
	
	private void processDriverLine(Driver driver, String line) throws ConfigurationException {
		Matcher matcher = driverPattern.matcher(line);
		if (! matcher.find()) {
			throw new ConfigurationException("Invalid Terrain configuration line : " + line);
		}
		driver.setup(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)));
	}
	
	private void processMowerLines(Driver driver, String startLine, String commandLine) throws ConfigurationException {
		Matcher startMatcher = mowerStartPattern.matcher(startLine);
		if (! startMatcher.find()) {
			throw new ConfigurationException("Invalid Mower start configuration : " + startLine);
		}
		Cardinal cardinal;
		switch(startMatcher.group(3).charAt(0)) {
		case CARDINAL_NORTH: cardinal = Cardinal.NORTH; break;
		case CARDINAL_EAST: cardinal = Cardinal.EAST; break;
		case CARDINAL_SOUTH: cardinal = Cardinal.SOUTH; break;
		case CARDINAL_WEST: cardinal = Cardinal.WEST;  break;
		default: throw new ConfigurationException("Invalid Mower start configuration : bad cardinal: " + startMatcher.group(3));
		}
		Mower mower = new Mower(Integer.valueOf(startMatcher.group(1)), Integer.valueOf(startMatcher.group(2)), cardinal);
		
		Matcher commandMatcher = mowerCommandPattern.matcher(commandLine);
		if (! commandMatcher.find()) {
			throw new ConfigurationException("Invalid Mower commands configuration : " + commandLine);
		}
		String commands = commandMatcher.group(1);
		for (int i = 0; i < commands.length(); i++) {
			switch(commands.charAt(i)) {
			case ACTION_FORWARD: mower.getActions().add(new ForwardAction()); break;
			case ACTION_TURN_LEFT: mower.getActions().add(new TurnLeftAction()); break;
			case ACTION_TURN_RIGHT: mower.getActions().add(new TurnRightAction()); break;
			default: throw new ConfigurationException("Invalid Mower commands configuration - bad command : " + commands.charAt(i));
			}
		}
		
		try {
			driver.addManagedMower(mower);
		}
		catch (CollisionException | OutOfBoundariesException e) {
			throw new ConfigurationException(e);
		}
	}
}
