package joprod.jitune.gui.actions;

import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.action.Action;

public class NewVirement extends Action {

	public NewVirement() {
		super();
	}

	@Override
	public String getText() {
		return JTStrings.new_virement; 
	}

	
}
