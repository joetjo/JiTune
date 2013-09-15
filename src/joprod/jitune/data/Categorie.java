package joprod.jitune.data;

import joprod.jitune.data.storage.PersistentData;

public class Categorie extends PersistentData<joetjo.jtune.jtunecore.storage.xml.basic.Jtune.Categories.Category> {

	public Categorie(joetjo.jtune.jtunecore.storage.xml.basic.Jtune.Categories.Category compatibilityHandle) {
		super(compatibilityHandle);
	}

	public String getName() {
		return data().getName();
	}
	
}
