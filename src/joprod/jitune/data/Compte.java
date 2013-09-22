package joprod.jitune.data;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import joetjo.jtune.jtunecore.CompteHelper;
import joetjo.jtune.jtunecore.OperationHelper;
import joprod.jitune.data.storage.PersistentData;
import joprod.jitune.data.storage.StorageException;
import joprod.jitune.resources.JTStrings;

public class Compte extends PersistentData<CompteHelper> {

	public static final int MONTH_COUNT = JTStrings.mois.length; 

	/**
	 * Toutes les opérations par année
	 */
	private final HashMap<Integer, CompteAnnuel> mAnnees;

	public Compte(CompteHelper ref) {
		super(ref);
		mAnnees = new HashMap<Integer, CompteAnnuel>();
	}

	public String getName() {
		return data().getCompte().getName();
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public void load() throws StorageException {
		mAnnees.clear();
		Calendar cal = new GregorianCalendar();
		for ( joetjo.jtune.jtunecore.storage.xml.compte.Compte.Operation op : data().getCompte().getOperation() ) {
			final OperationHelper opH = new OperationHelper(data(), op);
			final Date date  = opH.getDate();
			cal.setTime(date);
			final int year = cal.get(Calendar.YEAR);
			final int month = cal.get(Calendar.MONTH);
			CompteAnnuel cptA = mAnnees.get(year);
			if ( cptA == null ) {
				cptA = new CompteAnnuel(this, year);
				mAnnees.put(year, cptA);
			}
			cptA.add(month, opH);
		}
		markLoaded();
	}

	public CompteAnnuel getYear(int activeYear) {
		return mAnnees.get(activeYear);
	}
	
}
