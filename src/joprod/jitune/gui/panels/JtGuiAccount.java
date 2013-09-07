package joprod.jitune.gui.panels;

import java.awt.Label;

import joprod.jitune.JiTune;
import joprod.jitune.data.Compte;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public class JtGuiAccount extends Composite {

	private Compte account = null;

	public JtGuiAccount(Composite parent) {
		super(parent, SWT.None);
		
		create();
	}
	
	final private void create() {
	    setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    new Label("Not yet available :-(");
	}

	public void setAccount(Compte c)
	{
		if ( c == null &&
				account == null )
		{
			JiTune.APP.shutdown();
		}
		
		if ( c != null && c != account ) {
			account = c;
			loadAccount();
		}
	}
	
	private void loadAccount() {
		if ( getShell() != null )  {
			getShell().setText("JTune - " + account.getName());
			JiTune.APP.setStatus(JiTune.SESSION.getUser() + " / " + account.getName());
		}
	}

}
