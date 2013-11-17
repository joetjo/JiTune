package joprod.jitune.gui.actions;

import joprod.jitune.JiTune;
import joprod.jitune.data.OperationType;
import joprod.jitune.gui.dialogs.EditOperationDialog;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;

public class NewCredit extends Action {

	public NewCredit() {
		super();
	}

	@Override
	public String getText() {
		return JTStrings.new_credit; 
	}

	@Override
	public void run() {
		EditOperationDialog dial = new EditOperationDialog(JiTune.APP.getShell(), OperationType.CREDIT);
		dial.open();
		
		if ( dial.getReturnCode() == Dialog.OK )
		{
			// TODO apply change a faire directement dans le dialog... reste un truc a faire ici ?
		}
	}
	
}
