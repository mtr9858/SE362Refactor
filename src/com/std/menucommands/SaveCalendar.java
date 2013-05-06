package com.std.menucommands;

import com.std.commands.MenuCommand;
import com.std.controller.CalendarController;

public class SaveCalendar implements MenuCommand {
	private CalendarController controller = CalendarController.getInstance();
	
	public SaveCalendar() {}
	
	public void execute() {
		controller.save();
	}
}
