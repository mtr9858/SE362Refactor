package com.std.menucommands;

import com.std.commands.MenuCommand;
import com.std.controller.CalendarController;
import com.std.controller.dialog.AppointmentDialog;
import com.std.model.CalendarModelUtility;
import com.std.model.appointment.RefAppointment;

public class NewAppointment implements MenuCommand {
	private CalendarController controller = CalendarController.getInstance();
	
	public NewAppointment() { }
	
	public void execute() {
		RefAppointment ref = CalendarModelUtility.getNewAppointment(controller.getModel());
		
		if(AppointmentDialog.changeAppointment(controller.getView(), ref)) {
			CalendarModelUtility.addUsingPattern(controller.getModel(), ref);
		}
	}
}
