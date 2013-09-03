package joprod.jitune.data;

import java.util.HashMap;

import joprod.jitune.data.storage.PersistentData;

public class CompteAnnuel extends PersistentData {
	
	private final int mAnnee;
	private final HashMap<Integer, CompteMensuel> mMois;

	public CompteAnnuel(final int pAnnee) {
		super();
		mAnnee = pAnnee;
		mMois = new HashMap<Integer, CompteMensuel>();
		for(int i=0; i<Compte.MONTH_COUNT;i++)
		{
			mMois.put(new Integer(i), new CompteMensuel(i));
		}
	}

	
}
