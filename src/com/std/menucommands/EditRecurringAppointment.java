package com.std.menucommands;

import javax.swing.JOptionPane;

import com.std.commands.MenuCommand;
import com.std.controller.CalendarController;
import com.std.controller.dialog.AppointmentDialog;
import com.std.model.CalendarModelUtility;
import com.std.model.appointment.RefAppointment;

public class EditRecurringAppointment implements MenuCommand {
	private CalendarController controller = CalendarController.getInstance();
	
	public EditRecurringAppointment() { }
	
	public void execute() {
		RefAppointment ref = controller.getModel().getCurrentAppointment();
		if(ref != null) {
			AppointmentDialog.changeAppointment(controller.getView(), ref);
			
			CalendarModelUtility.addUsingPattern(controller.getModel(), ref);
		} else
			JOptionPane.showMessageDialog(controller.getView(), "no appointment is selected", "", JOptionPane.ERROR_MESSAGE);
	}
}
