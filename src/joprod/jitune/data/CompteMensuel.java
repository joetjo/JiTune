package joprod.jitune.data;

import java.util.ArrayList;
import java.util.List;

import joetjo.jtune.jtunecore.OperationHelper;
import joprod.jitune.data.storage.PersistentData;
import joprod.jitune.data.storage.StorageException;

@SuppressWarnings("rawtypes")
public class CompteMensuel extends PersistentData {

	private final int mMois;
	private final List<Operation> mOperations;
	
	public CompteMensuel(final int pMois) {
		super();
		mMois = pMois;
		mOperations = new ArrayList<Operation>();
	}

	public void add(OperationHelper opH) {
		markLoaded();
		mOperations.add(new Operation(opH));
	}

	@Override
	public void load() throws StorageException {
		// Chargé lors du load de Compte.
	}

	public List<Operation> getOperations(String category) {
		List<Operation> result = new ArrayList<Operation>();
		for( Operation op : mOperations ) {
			if ( op.isCategorie(category) ) {
				result.add(op);
			}
		}
		return result;
	}
	
}
