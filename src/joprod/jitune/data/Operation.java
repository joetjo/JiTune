package joprod.jitune.data;

import java.util.Date;

import joetjo.jtune.jtunecore.OperationHelper;
import joprod.jitune.data.storage.PersistentData;
import joprod.jitune.data.storage.StorageException;

public class Operation extends PersistentData<OperationHelper> {

	public Operation(OperationHelper opH) {
		super(opH);
	}

	@Override
	public void load() throws StorageException {
		// Pas de fiston, rien a faire ici
		markLoaded();
	}

	public boolean isCategorie(String category) {
		return    category != null
			   && category.equals(data().getOperation().getCategorie());
	}

	public double getMontant() {
		return data().getOperation().getMontant();
	}

	public boolean isDebit() {
		return data().isDebit();
	}
	
	public boolean isCredit() {
		return data().isCredit();
	}

	public boolean isVirement() {
		return data().isVirement();
	}

	public Date getDate() {
		return data().getDate();
	}

	public String getTier() {
		return data().getOperation().getTier();
	}

	public String getLabel() {
		return data().getOperation().getLabel();
	}

}
