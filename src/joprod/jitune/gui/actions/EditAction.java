package joprod.jitune.gui.actions;

import joprod.jitune.JiTune;
import joprod.jitune.data.Operation;
import joprod.jitune.gui.dialogs.EditOperationDialog;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;

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
	
	@Override
	public void run() {
		EditOperationDialog dial = new EditOperationDialog(JiTune.APP.getShell(), operation);
		dial.open();
		
		if ( dial.getReturnCode() == Dialog.OK )
		{
			// TODO apply change a faire directement dans le dialog... reste un truc a faire ici ?
		}
	}
	
}
