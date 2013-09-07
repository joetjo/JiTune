package joprod.jitune.gui.windows;

import joprod.jitune.JiTune;
import joprod.jitune.JiTuneViews;
import joprod.jitune.JiTuneViews.ViewIdentifier;
import joprod.jitune.gui.panels.JtGuiDateSelection;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class DateSelectionWindow extends Window {

	private JtGuiDateSelection dateSelection;

	public DateSelectionWindow(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		
		newShell.setText(JTStrings.title_date_selection);
		JiTune.VIEWS.register(ViewIdentifier.DATE_SELECTOR, this);
	}

	@Override
	protected Control createContents(Composite parent) {
		dateSelection = new JtGuiDateSelection(parent); 
	    return dateSelection;
	}

	@Override
	protected Point getInitialLocation(Point initialSize) {
		return JiTuneViews.INITIALPOS_DATE_SELECTION;
	}

	@Override
	protected Point getInitialSize() {
		return JiTuneViews.INITIALSIZE_DATE_SELECTION;
	}
}
