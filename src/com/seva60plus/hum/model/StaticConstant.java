package com.seva60plus.hum.model;

import java.io.Serializable;

public class StaticConstant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4187063046530340238L;
	// Saves the instance of checkbox Receive Notification
	private boolean showReminder = false;

	public boolean isShowReminder() {
		return showReminder;
	}

	public void setShowReminder(boolean showReminder) {
		this.showReminder = showReminder;
	}

}
