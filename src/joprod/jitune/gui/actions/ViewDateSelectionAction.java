package joprod.jitune.gui.actions;

import joprod.jitune.JiTune;
import joprod.jitune.JiTuneViews.ViewIdentifier;
import joprod.jitune.gui.actions.common.ViewAction;
import joprod.jitune.gui.windows.DateSelectionWindow;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.window.Window;

public class ViewDateSelectionAction extends ViewAction {

	public ViewDateSelectionAction() {
		super(ViewIdentifier.DATE_SELECTOR);
	}

	@Override
	protected Window createView() {
		return new DateSelectionWindow(JiTune.APP.getShell());
	}

	@Override
	public String getText() {
		return JTStrings.menu_date_selection;
	}

}
