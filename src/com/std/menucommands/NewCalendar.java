package com.std.menucommands;

import com.std.commands.MenuCommand;
import com.std.controller.CalendarController;

public class NewCalendar implements MenuCommand {
	private CalendarController controller = CalendarController.getInstance();
	
	public NewCalendar() {
	}
	
	public void execute() {
		try {
			controller.getModel().load(null);
		} catch (Exception e) {
			controller.handleException(e);
		}
	}
}
