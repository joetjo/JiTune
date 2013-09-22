package joprod.jitune.gui.panels.table;

import java.util.List;

import joprod.jitune.data.CompteAnnuel;
import joprod.jitune.data.CompteMensuel;
import joprod.jitune.data.Operation;
import joprod.jitune.resources.JTStrings;


public class LineTableAcountView {
	private LineTableAcountView parent;
	private String category;
	@SuppressWarnings("unchecked")
	private List<Operation> rawData[] = new List[12];
	private double[] data = new double[12];
	private double total = 0;

	public LineTableAcountView(String categorie) {
		super();
		this.category = categorie;
	}

	public LineTableAcountView(String categorie, LineTableAcountView totalLine) {
		this.category = categorie;
		parent = totalLine;
	}

	public String getCategoryName() {
		return category;
	}

	public String getMonth(int m) {
		if ( parent != null ) {
			return JTStrings.AMOUNT_FORMAT.format(data[m]);
		} else {
			return JTStrings.AMOUNT_FORMAT.format(data[m]) + " " + JTStrings.DEVISE;
		}
	}

	public String getTotal() {
		return JTStrings.AMOUNT_FORMAT.format(total) + " " + JTStrings.DEVISE;
	}

	public void load(CompteAnnuel cptA) {
		total = 0;
		for(int m=0; m<12; m++) {
			data[m] = 0;
			if ( cptA != null ) {
				CompteMensuel cptM = cptA.getMonth(m);
				if ( cptM != null ) {
					rawData[m] =  cptM.getOperations(category);
					for( Operation op : rawData[m] ) {
						data[m] += op.getMontant();
					}
				}
			}
			total += data[m];
			parent.data[m] += data[m];
			parent.total += data[m];
		}
	}

	
}
