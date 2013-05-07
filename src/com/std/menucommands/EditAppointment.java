package com.std.menucommands;

import javax.swing.JOptionPane;

import com.std.commands.MenuCommand;
import com.std.controller.CalendarController;
import com.std.controller.dialog.AppointmentDialog;
import com.std.model.CalendarModelUtility;
import com.std.model.appointment.AppointmentTemplate;
import com.std.model.appointment.RefAppointment;

public class EditAppointment implements MenuCommand {
	private CalendarController controller = CalendarController.getInstance();
	
	public EditAppointment() { }
	
	public void execute() {
		RefAppointment ref = controller.getModel().getCurrentAppointment();
		if(ref != null) {
			if(ref.getPattern() != null) {
				AppointmentTemplate apptTmpl = (AppointmentTemplate)ref.getTemplate().clone();
				apptTmpl.setPattern(null);
				RefAppointment appt = new RefAppointment(ref.getStartDate(), apptTmpl);
				
				if(AppointmentDialog.changeAppointment(controller.getView(), appt)) {
					controller.getModel().getAppointmentSet().remove(ref);
					CalendarModelUtility.add(controller.getModel(), appt);
				}
			} else
				JOptionPane.showMessageDialog(controller.getView(), "the selected appointment does not recur", "", JOptionPane.ERROR_MESSAGE);
		} else
			JOptionPane.showMessageDialog(controller.getView(), "no appointment is selected", "", JOptionPane.ERROR_MESSAGE);
	}
}
