package joprod.jitune.gui.actions.common;

import joprod.jitune.JiTune;
import joprod.jitune.JiTuneViews.ViewIdentifier;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;

public abstract class ViewAction extends Action {

	private ViewIdentifier viewId;

	public ViewAction(ViewIdentifier viewId) {
		super();
		this.viewId = viewId;
	}

	abstract protected Window createView();

	@Override
	public void run() {
		Window w = JiTune.VIEWS.get(viewId);
		if ( w == null ) 
		{
			w = createView();
		}
		w.open();
	}

	
}
