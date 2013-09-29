package joprod.jitune.gui.windows;

import joprod.jitune.JiTune;
import joprod.jitune.JiTuneViews;
import joprod.jitune.JiTuneViews.ViewIdentifier;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class AccountCheckWindow extends Window {

	public AccountCheckWindow(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		
		newShell.setText(JTStrings.title_account_check);
		JiTune.VIEWS.register(ViewIdentifier.ACCOUNT_CHECK, this);
	}

	@Override
	protected Control createContents(Composite parent) {
	    return new CLabel(parent, SWT.NONE);
	}

	@Override
	protected Point getInitialLocation(Point initialSize) {
		return JiTuneViews.INITIALPOS_ACCOUNT_CHECK;
	}

	@Override
	protected Point getInitialSize() {
		return JiTuneViews.INITIALSIZE_ACCOUNT_CHECK;
	}
}
