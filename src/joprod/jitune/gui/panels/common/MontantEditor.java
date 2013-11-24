package joprod.jitune.gui.panels.common;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;

public class MontantEditor extends Composite {

	private Spinner value;

	public MontantEditor(Composite parent, int style) {
		super(parent, style);

		setLayout(new FillLayout());
		setBounds(0,  0,  0, 0);

		value = new Spinner(this, style);
		value.setTextLimit(9);
		value.setValues(0, 0, 100000000, 2, 1, 1000);
	}

	public double getValue() {
		return ((double) value.getSelection()) / 100;
	}
	
	public void setValue(double val) {
		value.setSelection(Math.abs((int) (val * 100)));
		value.redraw();
	}
}
