package com.std.controller;

import java.awt.FileDialog;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import com.std.controller.listener.AppointmentSelectionMouseListener;
import com.std.controller.listener.DateSelectionActionListener;
import com.std.controller.listener.DefaultWindowListener;
import com.std.controller.listener.NextButtonActionListener;
import com.std.controller.listener.PrevButtonActionListener;
import com.std.model.CalendarModel;
import com.std.view.CalendarView;

/**
 * This class is the observer of the model, it acts as the
 * communication bridge between the view and the model. Whenever
 * the model changes this observe can change what is needed in the
 * view, also when there is an ActionEvent this class passes the information 
 * to the model
 * 
 * @author xxx
 *
 */
public class CalendarController implements Observer {

	/**
	 * The Calendar model is the mechanism to do all the logical operations of the calendar (add/remove/modify appointments)
	 */
	public CalendarModel theModel;
	
	/**
	 * The calendar view is the highest level of representation for the UI
	 */
	public CalendarView theView;

	private static CalendarController instance;
	
	/**
	 *  Called when there is a change in the model, this method will
	 *  invoke the update method on the view and pass it the new
	 *  set of ref appointments as well as the selected date and
	 *  selected appointment
	 *  
	 *  @param o is the observable object that has changed
	 *  @param param is the parameter sent by the notifyObservers methods 
	 */
	public void update(Observable o, Object param) {
		theView.update(
			theModel.getAppointmentSet(), 
			theModel.getCurrentDate(), 
			theModel.getCurrentAppointment(),
			theModel.getFile());
	}
	
	/**
	 * This method instantiates all the necessary listeners, giving them
	 * reference to this CalendarController, and then adds them to the CalendarView
	 */
	private void instantiateListeners () {
		
		// listens for when the view tries to be disposed
		DefaultWindowListener defW = new DefaultWindowListener(this);
		theView.addWindowListener(defW);
		
		// listens for when an appointment is selected
		AppointmentSelectionMouseListener apptSelL = new AppointmentSelectionMouseListener(this);
		theView.addAppointmentSelectionListener(apptSelL);
		
		// listens for when a date is selected
		DateSelectionActionListener dateSelL = new DateSelectionActionListener(this);
		theView.addDateSelectionListener(dateSelL);
		
		
		// listens for when the next button is pressed
		NextButtonActionListener nextL = new NextButtonActionListener(this);
		theView.addNextButtonActionListener(nextL);

		// listens for when the previous button is pressed
		PrevButtonActionListener prevL = new PrevButtonActionListener(this);
		theView.addPrevButtonActionListener(prevL);
	}
	
	/**
	 * Returns the highest level of representation for the UI
	 * 
	 * @return returns the highest level of representation for the UI
	 */
	public CalendarView getView() {
		return theView;
	}
	
	/**
	 * Returns the mechanism to do all the logical operations of the calendar
	 *
	 * @return returns the mechanism to do all the logical operations of the calendar
	 */ 
	public CalendarModel getModel() {
		return theModel;
	}
	
	public boolean confirm(Runnable runnable) {
		boolean ret = false;
		
		// if the model has changed since the last save
		if(theModel.isDifferentFromFile()) {
			
			// loop while the user's decision hasn't been made.
			// this is here in case the user selects an erroneous
			// choice.  in that case, it will loop back around to
			// asking them what to do instead of defaulting to
			// dispose.
			boolean loop = false;
			do {
				
				// prompt with the save option
				int result = JOptionPane.showConfirmDialog(
					theView, 
					"do you want to save the changes to " + (theModel.getFile() == null ? "Untitled Calendar" : theModel.getFile().getName()), 
					"", 
					JOptionPane.YES_NO_CANCEL_OPTION, 
					JOptionPane.PLAIN_MESSAGE);
				switch(result) {
				
				// if they chose to save, "save" or "save as" 
				// depending on the state of the model's current file 
				case JOptionPane.YES_OPTION:
						
					// if there's no current file, prompt with a "save as"
					ret = save();
					loop = !ret;
					break;
					
				// if they chose not to save, continue with the dispose
				case JOptionPane.NO_OPTION:
					loop = false;
					ret = true;
					break;
					
				// if they chose to cancel, don't dispose
				case JOptionPane.CANCEL_OPTION:
					loop = false;
					break;
				}
			} while(loop);
			
		// if there's been no changes, open normally
		} else 
			ret = true;
		
		if(ret)
			runnable.run();
		return ret;
	}
	
	public boolean open() {
		boolean ret  = false;

		FileDialog dialog = new FileDialog(theView, "Open File");
		dialog.setVisible(true);
		if(dialog.getFile() != null) {
			String path = dialog.getDirectory() + dialog.getFile();
			try {
				theModel.load(new File(path));
				ret = true;
			} catch(Exception ex) {
				handleException(ex);
			}
		}
		return ret;
	}
	
	/**
	 * This methd checks to see if the program needs to save or
	 * save a
	 * 
	 * @return true if the program can just save, or save as otherwise
	 */
	
	public boolean save() {
		boolean ret = false;
		try{
			if(theModel.getFile() == null)
				ret = saveAs();
			else {
				theModel.save(theModel.getFile());
				ret = true;
			}
		} catch(Exception ex) {
			handleException(ex);
		}
		return ret;
	}
	
	public boolean saveAs() {
		FileDialog dialog = new FileDialog(theView, "Save As");
		dialog.setMode(FileDialog.SAVE);
		dialog.setVisible(true);

		boolean ret = dialog.getFile() != null;
		if(ret) {
			String path = dialog.getDirectory() + dialog.getFile();
			try {
				theModel.save(new File(path));
			} catch (Exception ex) {
				handleException(ex);
			}
		}
		return ret;
	}
	
	public void handleException(Exception ex) {
		JOptionPane.showMessageDialog(theView, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		ex.printStackTrace();
	}
	
	public static CalendarController getInstance() {
		return instance;
	}
	
	/**
	 * The constructor of the Controller takes a model and a view and
	 * adds various listeners to the view and then adds itself as an
	 * observer of the model 
	 * 
	 * @param model the mechanism to do all the logical operations of the calendar
	 * @param view the highest level of representation for the UI
	 */
	public CalendarController(CalendarModel model, CalendarView view) {
		theModel = model;
		theView = view;
		instance = this;
		
		// add this CalendarController as an observer to the 
		// model, so the view can be updated when the model changes
		theModel.addObserver(this);
		
		// instantiate the listeners
		instantiateListeners();
	}
}
