package joprod.jitune.gui.actions;

import joprod.jitune.data.Operation;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.action.Action;

public class EditAction extends Action {

	private Operation operation;

	public EditAction(Operation operation) {
		super();
		this.operation = operation;
	}

	@Override
	public String getText() {
		return JTStrings.AMOUNT_FORMAT.format(operation.getMontant()) + " (" + operation.getTier() + ")";
	}
	
	
}
