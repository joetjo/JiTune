package joprod.jitune.data.storage;

import joprod.jitune.data.Comptes;
import joprod.jitune.data.storage.compatibility.StorageV2;

public class Storage {

	private StorageV2 mOldStorage;
	
	private Comptes mCachedComptes;

	public Storage() {
		mOldStorage = new StorageV2();
	}

	public String getDefaultUser() {
		return mOldStorage.getDefaultUser();
	}

	public Comptes getComptes() {
		if ( mCachedComptes == null )
		{
			mCachedComptes =  mOldStorage.loadComptes();
		}
		return mCachedComptes;
	}

	
	
}
