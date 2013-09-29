package joprod.jitune.gui.actions;

import joprod.jitune.data.Operation;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.action.Action;

public class copyAction extends Action {

	private Operation op;

	public copyAction(Operation op) {
		super();
		this.op = op;
	}

	@Override
	public String getText() {
		return JTStrings.AMOUNT_FORMAT.format(op.getMontant())  + " € ( " + op.getTier() + " )" ;
	}

	
}
