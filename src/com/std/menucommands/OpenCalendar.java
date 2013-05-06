package com.std.menucommands;

import com.std.commands.MenuCommand;
import com.std.controller.CalendarController;

public class OpenCalendar implements MenuCommand {
	private class OpenRunnable implements Runnable {
		public void run() {
			controller.open();
		}
	}
	
	private CalendarController controller = CalendarController.getInstance();
	
	public OpenCalendar() {}
	
	public void execute() {
		controller.confirm(new OpenRunnable());
	}
}
