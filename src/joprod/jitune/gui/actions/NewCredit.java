package joprod.jitune.gui.actions;

import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.action.Action;

public class NewCredit extends Action {

	public NewCredit() {
		super();
	}

	@Override
	public String getText() {
		return JTStrings.new_credit; 
	}

	
}
