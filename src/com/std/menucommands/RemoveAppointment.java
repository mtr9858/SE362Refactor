package com.std.menucommands;

import javax.swing.JOptionPane;

import com.std.commands.MenuCommand;
import com.std.controller.CalendarController;
import com.std.model.appointment.RefAppointment;

public class RemoveAppointment implements MenuCommand {
	private CalendarController controller = CalendarController.getInstance();
	
	public RemoveAppointment() { }
	
	public void execute() {
		RefAppointment ref = controller.getModel().getCurrentAppointment();
		if(ref != null)
			controller.getModel().getAppointmentSet().remove(ref);
		else
			JOptionPane.showMessageDialog(controller.getView(), "no appointment is selected", "", JOptionPane.ERROR_MESSAGE);
	}
}
