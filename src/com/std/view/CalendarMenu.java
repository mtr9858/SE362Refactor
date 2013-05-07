package com.std.view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.std.menucommands.About;
import com.std.menucommands.EditAppointment;
import com.std.menucommands.EditRecurringAppointment;
import com.std.menucommands.Exit;
import com.std.menucommands.NewAppointment;
import com.std.menucommands.NewCalendar;
import com.std.menucommands.OpenCalendar;
import com.std.menucommands.Preferences;
import com.std.menucommands.RemoveAllAppointments;
import com.std.menucommands.RemoveAppointment;
import com.std.menucommands.SaveAsCalendar;
import com.std.menucommands.SaveCalendar;

/**
 * The is the menu
 * 
 * @author xxx
 *
 */

public class CalendarMenu extends JMenuBar {
	/**
	 * UID Used for Serializable
	 */
	private static final long serialVersionUID = 2649421712299673376L;
	
	/**
	 * These will be the items displayed in the menue
	 */
	
	private final JMenuItem newCalendar;
	private final JMenuItem openCalendar;
	private final JMenuItem saveCalendar;
	private final JMenuItem saveAsCalendar;
	private final JMenuItem exitApplication;
	private final JMenuItem newAppointment;
	private final JMenuItem editAppointment;
	private final JMenuItem editAllAppointment;
	private final JMenuItem removeAppointment;
	private final JMenuItem removeAllAppointment;
	private final JMenuItem preferences;
	private final JMenuItem about;

	/**
	 * This is the constructor for the menu, it sets up how it
	 * is going to look and initializes all of the menu items
	 */
	
	public CalendarMenu() {
		super();
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		add(fileMenu);
		
		newCalendar = new JMenuItem("New Calendar");
		newCalendar.setMnemonic(KeyEvent.VK_N);
		fileMenu.add(newCalendar);
		newCalendar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				NewCalendar newcalendar = new NewCalendar();
				newcalendar.execute();
			}
		});
		
		openCalendar = new JMenuItem("Open File...");
		openCalendar.setMnemonic(KeyEvent.VK_O);
		fileMenu.add(openCalendar);
		openCalendar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				OpenCalendar opencalendar = new OpenCalendar();
				opencalendar.execute();
			}
		});
		
		fileMenu.addSeparator();
		
		saveCalendar = new JMenuItem("Save");
		saveCalendar.setMnemonic(KeyEvent.VK_S);
		fileMenu.add(saveCalendar);
		saveCalendar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SaveCalendar savecalendar = new SaveCalendar();
				savecalendar.execute();
			}
		});
		
		saveAsCalendar = new JMenuItem("Save As...");
		fileMenu.add(saveAsCalendar);
		saveAsCalendar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SaveAsCalendar saveascalendar = new SaveAsCalendar();
				saveascalendar.execute();
			}
		});
		
		fileMenu.addSeparator();
		
		exitApplication = new JMenuItem("Exit");
		fileMenu.add(exitApplication);
		exitApplication.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Exit exit = new Exit();
				exit.execute();
			}
		});
		
		
		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_E);
		add(editMenu);
		
		newAppointment = new JMenuItem("New Appointment...");
		editMenu.add(newAppointment);
		newAppointment.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				NewAppointment newappointment = new NewAppointment();
				newappointment.execute();
			}
		});
		
		editMenu.addSeparator();
		
		editAllAppointment = new JMenuItem("Edit Appointment...");
		editMenu.add(editAllAppointment);
		editAllAppointment.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				EditAppointment editappointment = new EditAppointment();
				editappointment.execute();
			}
		});
		
		
		editAppointment = new JMenuItem("Disjoin and Edit Appointment...");
		editMenu.add(editAppointment);
		editAppointment.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				EditRecurringAppointment editrecurringappointment = new EditRecurringAppointment();
				editrecurringappointment.execute();
			}
		});
		
		editMenu.addSeparator();
		
		removeAppointment = new JMenuItem("Remove Single Appointment");
		editMenu.add(removeAppointment);
		removeAppointment.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				RemoveAppointment removeappointment = new RemoveAppointment();
				removeappointment.execute();
			}
		});
		
		removeAllAppointment = new JMenuItem("Remove All Recurring Appointments");
		editMenu.add(removeAllAppointment);
		removeAllAppointment.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				RemoveAllAppointments removeallappointment = new RemoveAllAppointments();
				removeallappointment.execute();
			}
		});
		
		editMenu.addSeparator();
		
		preferences = new JMenuItem("Preferences...");
		editMenu.add(preferences);
		preferences.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Preferences preferences = new Preferences();
				preferences.execute();
			}
		});
		
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		add(helpMenu);
		
		about = new JMenuItem("About");
		helpMenu.add(about);
		about.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				About about = new About();
				about.execute();
			}
		});
	}
}
