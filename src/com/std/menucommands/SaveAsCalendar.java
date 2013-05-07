package com.std.menucommands;

import com.std.commands.MenuCommand;
import com.std.controller.CalendarController;

public class SaveAsCalendar implements MenuCommand {
	private CalendarController controller = CalendarController.getInstance();
	
	public SaveAsCalendar() { }
	
	public void execute() {
		controller.saveAs();
	}
}
