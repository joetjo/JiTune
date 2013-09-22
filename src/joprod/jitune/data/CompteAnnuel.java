package joprod.jitune.data;

import java.util.HashMap;

import joetjo.jtune.jtunecore.OperationHelper;
import joprod.jitune.data.storage.PersistentData;
import joprod.jitune.data.storage.StorageException;

@SuppressWarnings("rawtypes")
public class CompteAnnuel extends PersistentData {
	
	private Compte mCompte;
	private final int mAnnee;
	private final HashMap<Integer, CompteMensuel> mMois;

	public CompteAnnuel(Compte compte, final int pAnnee) {
		super();
		mAnnee = pAnnee;
		mCompte = compte;

		mMois = new HashMap<Integer, CompteMensuel>();
		for(int i=0; i<Compte.MONTH_COUNT;i++)
		{
			mMois.put(new Integer(i), new CompteMensuel(i));
		}
	}

	public void add(int mois, OperationHelper opH) {
		markLoaded();
		
		mMois.get(mois).add(opH);
	}

	@Override
	public void load() throws StorageException {
		// Chargé lors du load de Compte.
	}

	public CompteMensuel getMonth(int m) {
		return mMois.get(m);
	}

	
}
