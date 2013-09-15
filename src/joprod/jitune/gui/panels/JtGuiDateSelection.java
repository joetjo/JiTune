package joprod.jitune.gui.panels;

import java.util.Calendar;
import java.util.Date;

import joprod.jitune.JiTune;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;

public class JtGuiDateSelection extends Composite {

	private Integer[]   yearModel;
	private ComboViewer year;
	private DateTime from;
	private DateTime to;
	private Label    message;
	
	public JtGuiDateSelection(Composite parent) {
		super(parent, SWT.None);
		
		create();
	}
	
	final private void create() {
	    setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    
	    GridLayout layout = new GridLayout(7, false);
	    setLayout(layout);

	    // Year selection
	    Label yearLabel = new Label(this, SWT.NONE);
	    yearLabel.setText(JTStrings.date_year);
	    year = new ComboViewer(this);
	    // TODO chargement des dates du comptes actif + l'année suivante
	    yearModel = new Integer[5];
	    yearModel[0] = JiTune.STORAGE.getSetup().getActiveYear()-3;
	    yearModel[1] = JiTune.STORAGE.getSetup().getActiveYear()-2;
	    yearModel[2] = JiTune.STORAGE.getSetup().getActiveYear()-1;
	    yearModel[3] = JiTune.STORAGE.getSetup().getActiveYear();
	    yearModel[4] = JiTune.STORAGE.getSetup().getActiveYear()+1;
	    year.setContentProvider(ArrayContentProvider.getInstance());
	    year.setInput(yearModel);
	    year.setSelection(new StructuredSelection(yearModel[3]));

	    year.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if ( ! event.getSelection().isEmpty() ) {
					IStructuredSelection selection = (IStructuredSelection) event.getSelection();
					yearUpdated( (Integer) selection.getFirstElement());
				}
			}
		});
	    // From date
	    Label fromLabel = new Label(this, SWT.NONE);
	    fromLabel.setText(JTStrings.date_from);
	    from = new DateTime(this, SWT.NONE);
	    from.setEnabled(false);

	    // To date
	    Label toLabel = new Label(this, SWT.NONE);
	    toLabel.setText(JTStrings.date_to);
	    to = new DateTime(this, SWT.NONE);
	    to.setEnabled(false);

	    // Update from and To
	    yearUpdated(JiTune.STORAGE.getSetup().getActiveYear());

	    message = new Label(this, SWT.NONE);
	    message.setText("Selection possible uniquement d'une année (+ à paraitre)");
	    message.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

	    from.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				fromUpdated();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});

	    to.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				toUpdated();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
	}

	private void yearUpdated(int yearSelected) {
		from.setYear(yearSelected);
		from.setMonth(0);
		from.setDay(1);

		to.setYear(yearSelected);
		to.setMonth(11);
		to.setDay(31);

		JiTune.STORAGE.getSetup().setActiveYear(yearSelected);
		JiTune.STORAGE.getSetup().setBeginActivePeriod(from.getYear(), from.getMonth(), from.getDay());
		JiTune.STORAGE.getSetup().save();;
	}

	private void fromUpdated() {
		periodUpdated();
	}

	private void toUpdated() {
		periodUpdated();
	}
	
	private void periodUpdated() {
	}
}
