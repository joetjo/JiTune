package joprod.jitune.gui.actions;

import joprod.jitune.JiTune;
import joprod.jitune.JiTuneViews.ViewIdentifier;
import joprod.jitune.gui.actions.common.ViewAction;
import joprod.jitune.gui.windows.PropertyWindow;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.window.Window;

public class ViewPropertyAction extends ViewAction {

	public ViewPropertyAction() {
		super(ViewIdentifier.PROPERTY);
	}

	@Override
	protected Window createView() {
		return new PropertyWindow(JiTune.APP.getShell());
	}

	@Override
	public String getText() {
		return JTStrings.menu_property;
	}

}
