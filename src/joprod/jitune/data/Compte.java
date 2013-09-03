package joprod.jitune.data;

import java.util.HashMap;

import joprod.jitune.data.storage.PersistentData;
import joprod.jitune.resources.JTStrings;

public class Compte extends PersistentData<joetjo.jtune.jtunecore.storage.xml.basic.Jtune.Comptes.CompteRef> {

	public static final int MONTH_COUNT = JTStrings.mois.length; 

	/**
	 * Toutes les opérations par année
	 */
	private final HashMap<Integer, CompteAnnuel> mAnnees;

	public Compte(joetjo.jtune.jtunecore.storage.xml.basic.Jtune.Comptes.CompteRef ref) {
		super(ref);
		mAnnees = new HashMap<Integer, CompteAnnuel>();
	}

	public String getName() {
		return data().getName();
	}

	@Override
	public String toString() {
		return getName();
	}

	
}
