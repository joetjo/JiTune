package joprod.jitune.gui.actions;

import joprod.jitune.JiTune;
import joprod.jitune.JiTuneViews.ViewIdentifier;
import joprod.jitune.gui.actions.common.ViewAction;
import joprod.jitune.gui.windows.AccountCheckWindow;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.window.Window;

public class ViewAccountCheckAction extends ViewAction {

	public ViewAccountCheckAction() {
		super(ViewIdentifier.ACCOUNT_CHECK);
	}

	@Override
	protected Window createView() {
		return new AccountCheckWindow(JiTune.APP.getShell());
	}

	@Override
	public String getText() {
		return JTStrings.menu_account_check;
	}

}
