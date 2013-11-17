package joprod.jitune.gui.panels;

import joprod.jitune.data.Operation;
import joprod.jitune.resources.JTRes;
import joprod.jitune.resources.JTStrings;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

public class JtGuiOperation extends Composite {

	private Operation operation;
	
	// TODO editeur d'opération
	// Solde rapproché
	// Solde validé
	// Solde
	// Conversion Credit <--> Debit
	
	// Date
	private DateTime date;
	// Tiers
	private Combo tier;
	
	// Catégorie
	private Combo category; 
	
	// Type
	private Combo type;
	
	// label
	private Text description;

	// Montant
	private Spinner montant;
	
	// Compte associé pour les virements et numéro de virement
	private CLabel labelCompte;
	private Combo compteAssociated;
	
	// Numéro de chéque pour les chéques
	private CLabel labelNumero;
	private Text numero;

	public JtGuiOperation(Composite parent) {
		super(parent, SWT.NONE);

		create();
	}

	private void create() {
		GridData gdHzSpan4;
		GridData gdGrab2;
		GridData gdGrab;
		CLabel label;

		gdGrab2 = new GridData();
		gdGrab2.horizontalAlignment = GridData.FILL;
		gdGrab2.grabExcessHorizontalSpace = true;
		gdGrab2.horizontalSpan = 2;
		
		gdGrab = new GridData();
		gdGrab.horizontalAlignment = GridData.FILL;
		gdGrab.grabExcessHorizontalSpace = true;

		gdHzSpan4 = new GridData();
		gdHzSpan4.horizontalAlignment = GridData.FILL;
		gdHzSpan4.horizontalSpan = 4;
		gdHzSpan4.grabExcessHorizontalSpace = true;
		
		setLayout(new GridLayout(5, false));

		// 1st line
		label = new CLabel(this, SWT.NONE);
		label.setText(JTStrings.label_date);
		date = new DateTime(this, SWT.NONE);
	
		label = new CLabel(this, SWT.NONE);
		label.setText(JTStrings.label_category);
		category = new Combo(this, SWT.NONE);
		category.setLayoutData(gdGrab2);

		// 2nd line
		label = new CLabel(this, SWT.NONE);
		label.setText(JTStrings.label_type_paiment);
		type = new Combo(this, SWT.NONE);

		label = new CLabel(this, SWT.NONE);
		label.setText(JTStrings.label_tier);
		tier = new Combo(this, SWT.NONE);
		tier.setLayoutData(gdGrab2);
		
		// 3rd Line
		label = new CLabel(this, SWT.NONE);
		label.setText(JTStrings.label_description);
		description = new Text(this, SWT.BORDER);
		description.setLayoutData(gdHzSpan4);
		
		// 4th Line
		labelNumero = new CLabel(this, SWT.NONE);
		labelNumero.setText(JTStrings.label_numero);
		labelNumero.setVisible(false);
		numero = new Text(this, SWT.NONE);
		numero.setVisible(false);
		
		label = new CLabel(this, SWT.NONE);
		label.setText(JTStrings.label_montant);
		montant = new Spinner(this, SWT.BORDER);
		montant.setLayoutData(gdGrab);
		label = new CLabel(this, SWT.NONE);
		label.setText(JTStrings.euro);

		// 5th line
		labelCompte = new CLabel(this, SWT.NONE);
		labelCompte.setText(JTStrings.label_compteAssociated);
		labelCompte.setVisible(false);
		compteAssociated = new Combo(this, SWT.NONE);
		compteAssociated.setLayoutData(gdHzSpan4);
		compteAssociated.setVisible(false);
	}

}
