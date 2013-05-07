package com.std.menucommands;

import com.std.commands.MenuCommand;
import com.std.controller.CalendarController;

public class Exit implements MenuCommand {
	private CalendarController controller = CalendarController.getInstance();
	
	public Exit() { }
	
	public void execute() {
		controller.getView().dispose();
	}
}
