package joprod.jitune.gui.dialogs;

import joprod.jitune.data.Compte;
import joprod.jitune.gui.panels.JtGuiAccountlist;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class SelectAccountDialog extends TitleAreaDialog {

	JtGuiAccountlist list;
	private Compte mSelection = null;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public SelectAccountDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(JTStrings.title_select_account);
	}

	@Override
	public void create() {
		super.create();
	    setTitle(JTStrings.selection_active_account);
	    setMessage(JTStrings.selection_active_account_message, IMessageProvider.INFORMATION);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		
		list = new JtGuiAccountlist(area, SWT.NONE); 
		list.setUniqueListener(new JtGuiAccountlist.Listener() {
			@Override
			public void simpleClicSelection() {}
			@Override
			public void doubleClicSelection() {
				okPressed();
			}
			@Override
			public void popupMenu() {}
		});
	    return list;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	@Override
	protected void okPressed() {
		mSelection = list.getSelection();
		super.okPressed();
	}

	public Compte getCompte() {
		return mSelection;
	}

}
