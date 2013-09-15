package joprod.jitune.data;

import java.util.ArrayList;
import java.util.List;

import joetjo.jtune.jtunecore.ComptesHelper;
import joetjo.jtune.jtunecore.storage.xml.basic.Jtune.Comptes.CompteRef;
import joprod.jitune.data.storage.PersistentData;
import joprod.jitune.data.storage.StorageException;

public class Comptes extends PersistentData<ComptesHelper> {
	private final List<Compte> mComptes = new ArrayList<Compte>();

	public Comptes(ComptesHelper comptesHelper) {
		super(comptesHelper);
	}

	public List<Compte> getComptes()
	{
		return mComptes;
	}
	
	@Override
	public void load() throws StorageException {
		mComptes.clear();
		joetjo.jtune.jtunecore.storage.xml.basic.Jtune.Comptes cpt = data().getComptes().getComptes();

		for ( CompteRef ref : cpt.getCompteRef() ) {
			mComptes.add(new Compte(data().getCompte(ref)));
		}
		markLoaded();
	}


}
