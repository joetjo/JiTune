package joprod.jitune.gui.actions;

import joprod.jitune.JiTune;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.action.Action;

public class Exit extends Action {

	@Override
	public void run() {
		// TODO ACTION : exit confirmation ?
		JiTune.APP.shutdown();
	}

	@Override
	public String getText() {
		return JTStrings.menu_exit;
	}

	
}
