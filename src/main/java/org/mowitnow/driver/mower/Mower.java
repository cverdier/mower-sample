package org.mowitnow.driver.mower;

import java.util.LinkedList;
import java.util.List;

import org.mowitnow.driver.action.Action;

/**
 * Simple Object to represent a Mower
 */
public class Mower {

	private int xPosition;
	private int yPosition;
	private Cardinal orientation;
	private List<Action> actions = new LinkedList<Action>();
	
	public Mower(int xPosition, int yPosition, Cardinal orientation) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.orientation = orientation;
	}
	
	public int getxPosition() {
		return xPosition;
	}
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	public int getyPosition() {
		return yPosition;
	}
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	public Cardinal getOrientation() {
		return orientation;
	}
	public void setOrientation(Cardinal orientation) {
		this.orientation = orientation;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
}
