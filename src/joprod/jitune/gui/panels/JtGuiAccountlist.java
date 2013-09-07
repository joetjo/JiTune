package joprod.jitune.gui.panels;

import joprod.jitune.JiTune;
import joprod.jitune.data.Compte;
import joprod.rcp.common.list.GuiListElement;

import org.eclipse.swt.widgets.Composite;

public class JtGuiAccountlist extends GuiListElement<Compte> {

	public JtGuiAccountlist(Composite parent, int style) {
		super(parent, style);
	}

	protected void afterCreate() {
	    loadData();
	}

	private void loadData() {
		for ( Compte compte : JiTune.STORAGE.getComptes().getComptes() )
		{
			add(compte);
		}
	}

}
