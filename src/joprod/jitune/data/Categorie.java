package joprod.jitune.data;

import joprod.jitune.data.storage.PersistentData;
import joprod.jitune.data.storage.StorageException;

public class Categorie extends PersistentData<joetjo.jtune.jtunecore.storage.xml.basic.Jtune.Categories.Category> {

	public Categorie(joetjo.jtune.jtunecore.storage.xml.basic.Jtune.Categories.Category compatibilityHandle) {
		super(compatibilityHandle);
	}

	public String getName() {
		return data().getName();
	}

	@Override
	public void load() throws StorageException {
		// Rien de plus que le lien avec la cétorie JTune V2 pour l'instant.
		markLoaded();
	}
	
}
