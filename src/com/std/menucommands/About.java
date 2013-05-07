package com.std.menucommands;

import javax.swing.JOptionPane;

import com.std.commands.MenuCommand;
import com.std.controller.CalendarController;

public class About implements MenuCommand {
	private CalendarController controller = CalendarController.getInstance();
	
	public About() { }
	
	public void execute() {
		JOptionPane.showMessageDialog(controller.getView(), "DCal.  Copyright 2008, Super Team D, RIT", "About", JOptionPane.INFORMATION_MESSAGE);
	}
}
