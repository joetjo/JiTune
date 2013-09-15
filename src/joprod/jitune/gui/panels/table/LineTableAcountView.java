package joprod.jitune.gui.panels.table;

import java.util.List;

import joprod.jitune.data.Operation;

public class LineTableAcountView {
	private String category;
	private List<Operation> data;

	public LineTableAcountView(String categorie) {
		super();
		this.category = categorie;
	}

	public String getCategoryName() {
		return category;
	}

	public String getMonth(int month) {
		return "todo";
	}

	public String getTotal() {
		return "todo";
	}

	
}
