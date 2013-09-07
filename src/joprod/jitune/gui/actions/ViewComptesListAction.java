package joprod.jitune.gui.actions;

import joprod.jitune.JiTune;
import joprod.jitune.JiTuneViews.ViewIdentifier;
import joprod.jitune.gui.actions.common.ViewAction;
import joprod.jitune.gui.windows.ComptesListWindows;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.window.Window;

public class ViewComptesListAction extends ViewAction {

	public ViewComptesListAction() {
		super(ViewIdentifier.COMPTES_LIST);
	}

	@Override
	protected Window createView() {
		return new ComptesListWindows(JiTune.APP.getShell());
	}

	@Override
	public String getText() {
		return JTStrings.menu_view;
	}

}
