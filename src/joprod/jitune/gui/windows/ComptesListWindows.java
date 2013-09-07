package joprod.jitune.gui.windows;

import joprod.jitune.JiTune;
import joprod.jitune.JiTuneViews.ViewIdentifier;
import joprod.jitune.gui.panels.JtGuiAccountlist;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class ComptesListWindows extends Window {

	private JtGuiAccountlist list;

	public ComptesListWindows(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		
		newShell.setText(JTStrings.title_list_account);
		JiTune.VIEWS.register(ViewIdentifier.COMPTES_LIST, this);
	}

	@Override
	protected Control createContents(Composite parent) {
		list = new JtGuiAccountlist(parent, SWT.NONE); 
		list.setUniqueListener(new JtGuiAccountlist.Listener() {
			@Override
			public void simpleClicSelection() {}
			@Override
			public void doubleClicSelection() {
				JiTune.APP.warningMessage("Not implemented", "To be done one day or another...");
			}
			@Override
			public void popupMenu() {}
		});
	    return list;
	}

	@Override
	protected Point getInitialLocation(Point initialSize) {
		return new Point(0, 0);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(250, 700);
	}

}
