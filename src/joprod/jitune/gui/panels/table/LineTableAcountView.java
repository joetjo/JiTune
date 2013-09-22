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
		if ( ! isCumul() ) {
			return JTStrings.AMOUNT_FORMAT.format(data[m]);
		} else {
			return JTStrings.AMOUNT_FORMAT.format(data[m]) + " " + JTStrings.DEVISE;
		}
	}

	public String getTotal() {
		return JTStrings.AMOUNT_FORMAT.format(total) + " " + JTStrings.DEVISE;
	}

	private boolean isCumul() {
		return parent == null;
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

	public String getTooltip(int m) {
		StringBuilder st = new StringBuilder();
		if ( ! isCumul() ) {
			boolean first = true;
			for ( Operation op : rawData[m]) {
				if ( ! first ) {
					st.append("\n");
				} else {
					first = false;
				}
				st.append(JTStrings.DATE_DAY_FORMAT.format(op.getDate()));
				st.append(" : ");
				st.append(JTStrings.AMOUNT_FORMAT.format(op.getMontant()));
				st.append(" ");
				st.append(JTStrings.DEVISE);
				st.append(" - ");
				st.append(op.getTier());
				st.append(" (");
				st.append(op.getLabel());
				st.append(")");
			}
		}
		return st.toString();
	}
	
}
