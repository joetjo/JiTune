package joprod.jitune.gui.panels;

import java.awt.Label;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public class JtGuiDateSelection extends Composite {

	public JtGuiDateSelection(Composite parent) {
		super(parent, SWT.None);
		
		create();
	}
	
	final private void create() {
	    setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    new Label("Not yet available :-(");
	}

}
