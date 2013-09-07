package joprod.jitune.gui.actions;

import joprod.jitune.JiTune;
import joprod.jitune.gui.dialogs.SelectAccountDialog;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;

public class SelectAccount extends Action {

	@Override
	public void run() {
		SelectAccountDialog dial = new SelectAccountDialog(JiTune.APP.getShell());
		dial.open();
		
		if ( dial.getReturnCode() == Dialog.OK )
		{
			JiTune.APP.getAccountEditor().setAccount(dial.getCompte());
		}
	}

	@Override
	public String getText() {
		return JTStrings.menu_select_account;
	}

	
}
