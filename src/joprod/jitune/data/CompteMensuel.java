package joprod.jitune.data;

import java.util.ArrayList;
import java.util.List;

import joprod.jitune.data.storage.PersistentData;

@SuppressWarnings("rawtypes")
public class CompteMensuel extends PersistentData {

	private final int mMois;
	private final List<Operation> mOperations;
	
	public CompteMensuel(final int pMois) {
		super();
		mMois = pMois;
		mOperations = new ArrayList<Operation>();
	}

	
}
