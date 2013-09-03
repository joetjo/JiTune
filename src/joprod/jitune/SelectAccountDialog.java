package joprod.jitune;

import joprod.jitune.data.Compte;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class SelectAccountDialog extends TitleAreaDialog {

	private ListViewer list;
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
	    setTitle("Sélection du compte actif");
	    setMessage("La version actuelle de JiTune New Generation travaille sur un seul compte actif.", IMessageProvider.INFORMATION);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		
	    Composite container = new Composite(area, SWT.NONE);
	    container.setLayoutData(new GridData(GridData.FILL_BOTH));
	    GridLayout layout = new GridLayout(2, false);
	    container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    container.setLayout(layout);

	    list = new ListViewer(container);
	    list.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

	    loadData();

	    return container;
	}

	private void loadData() {
		for ( Compte compte : JiTune.STORAGE.getComptes().getComptes() )
		{
			list.add(compte);
		}
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, 
				false);
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
		IStructuredSelection selection = (IStructuredSelection) list.getSelection();
		if ( ! selection.isEmpty() ) {
			mSelection =  (Compte) selection.getFirstElement();
		} else {
			mSelection = null;
		}
		super.okPressed();
	}

	public Compte getCompte() {
		return mSelection;
	}

}
