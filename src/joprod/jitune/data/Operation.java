package joprod.jitune.data;

import joetjo.jtune.jtunecore.OperationHelper;
import joprod.jitune.data.common.Date;
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

	public void setMontant(double value) {
		if ( isCredit() ) {
			data().getOperation().setMontant(value);
		} else if ( isDebit()) {
			data().getOperation().setMontant(- value);
		} else {
			throw new RuntimeException("Save opetion type Virement non supportée pour l'instant :-/");
		}
	}

	public OperationType getType() {
		if ( isDebit() ) {
			return OperationType.DEBIT;
		} else if ( isCredit()) {
			return OperationType.CREDIT;
		} else {
			return OperationType.VIREMENT;
		}
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
		return new Date(data().getDate());
	}

	public void setDate(Date date) {
		data().setDate(date.getJavaDate());
	}

	public String getTier() {
		return data().getOperation().getTier();
	}

	public void setTier(String text) {
		data().getOperation().setTier(text);
	}

	public String getLabel() {
		return data().getOperation().getLabel();
	}

	public void setLabel(String text) {
		data().getOperation().setLabel(text);
	}

	public String getCategorie() {
		return data().getOperation().getCategorie();
	}

	public void setCategorie(String text) {
		data().getOperation().setCategorie(text);
	}

	public String getAssociatedCompte() {
		return data().getOperation().getAssociatedCompte();
	}

	public String getNumero() {
		return data().getOperation().getNumero();
	}

	public void setNumero(String text) {
		data().getOperation().setNumero(text);
	}

}
