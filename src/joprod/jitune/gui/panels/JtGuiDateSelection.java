package joprod.jitune.gui.panels;

import java.util.Calendar;
import java.util.Date;

import joprod.jitune.JiTune;
import joprod.jitune.resources.JTStrings;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;

public class JtGuiDateSelection extends Composite {

	private DateTime from;
	private DateTime to;
	private Label    message;
	
	public JtGuiDateSelection(Composite parent) {
		super(parent, SWT.None);
		
		create();
	}
	
	final private void create() {
	    setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    
	    GridLayout layout = new GridLayout(5, false);
	    setLayout(layout);

	    Label fromLabel = new Label(this, SWT.NONE);
	    fromLabel.setText(JTStrings.date_from);
	    from = new DateTime(this, SWT.NONE);
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(JiTune.STORAGE.getSetup().getBeginActivePeriod());
	    from.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
	    Label toLabel = new Label(this, SWT.NONE);
	    toLabel.setText(JTStrings.date_to);
	    to = new DateTime(this, SWT.NONE);
	    cal.setTime(new Date());
	    to.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) + 1);

	    message = new Label(this, SWT.NONE);
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

	private void fromUpdated() {
		JiTune.STORAGE.getSetup().setBeginActivePeriod(from.getYear(), from.getMonth(), from.getDay());
		JiTune.STORAGE.saveSetup();
		periodUpdated();
	}

	private void toUpdated() {
		periodUpdated();
	}
	
	private void periodUpdated() {
	}
}
