package com.std.menucommands;

import com.std.commands.MenuCommand;
import com.std.controller.CalendarController;
import com.std.controller.dialog.AppointmentDialog;

public class Preferences implements MenuCommand{
	private CalendarController controller = CalendarController.getInstance();
	
	public Preferences() { }
	
	public void execute() {
		AppointmentDialog.changeAppointmentDefaults(controller.getView(), controller.getModel());
	}
}
