package joprod.jitune.data;

import java.util.ArrayList;
import java.util.List;

import joprod.jitune.data.storage.PersistentData;

@SuppressWarnings("rawtypes")
public class Comptes extends PersistentData {
	private final List<Compte> mComptes = new ArrayList<Compte>();

	public List<Compte> getComptes()
	{
		return mComptes;
	}
	
	public void add(Compte compte) {
		mComptes.add(compte);
	}
}
