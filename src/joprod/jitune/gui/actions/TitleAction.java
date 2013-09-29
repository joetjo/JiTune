package joprod.jitune.gui.actions;

import org.eclipse.jface.action.Action;

public class TitleAction extends Action {

	String title;

	public TitleAction(String title) {
		super();
		this.title = title;
		setEnabled(false);
	}

	@Override
	public String getText() {
		return title; 
	}

}
