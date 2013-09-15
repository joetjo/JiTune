package joprod.jitune.data;

import joetjo.jtune.jtunecore.OperationHelper;
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
	
}
