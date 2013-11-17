package joprod.jitune.gui.dialogs;

import joprod.jitune.data.Operation;
import joprod.jitune.data.OperationType;
import joprod.jitune.gui.panels.JtGuiOperation;
import joprod.jitune.resources.JTRes;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class EditOperationDialog extends TitleAreaDialog {

	private OperationType type      = null;
	private Operation 	  operation = null;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public EditOperationDialog(Shell parentShell, OperationType type) {
		super(parentShell);
		operation = null;
		this.type = type;
	}

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public EditOperationDialog(Shell parentShell, Operation operation) {
		super(parentShell);
		this.operation = operation;
		type = operation.getType();
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(JTStrings.title_edit_operation);
	}

	@Override
	public void create() {
		super.create();
		if ( operation == null ) {
		    setTitle(JTStrings.new_operation_title);
		} else {
		    setTitle(JTStrings.edit_operation_title);
		}
		switch ( type ) {
		case DEBIT:
		    setMessage(JTStrings.operation_debit, IMessageProvider.INFORMATION);
			break;
		case CREDIT:
		    setMessage(JTStrings.operation_credit, IMessageProvider.INFORMATION);
			break;
		case VIREMENT:
		    setMessage(JTStrings.operation_virement, IMessageProvider.INFORMATION);
			break;
		}
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		
		JtGuiOperation panel = new JtGuiOperation(area);
		GridData gd = new GridData(GridData.FILL, GridData.FILL, true, true );
		panel.setLayoutData(gd);
		return panel;
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
		super.okPressed();
	}

}
