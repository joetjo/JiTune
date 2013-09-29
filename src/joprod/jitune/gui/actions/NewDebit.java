package joprod.jitune.gui.actions;

import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.action.Action;

public class NewDebit extends Action {

	public NewDebit() {
		super();
	}

	@Override
	public String getText() {
		return JTStrings.new_debit; 
	}

	
}
