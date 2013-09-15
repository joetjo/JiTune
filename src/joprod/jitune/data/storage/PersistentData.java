package joprod.jitune.data.storage;

public abstract class PersistentData<OLD_DATA> {
	private long mKey;
	private boolean mLoaded;
	private OLD_DATA mCompatibilityHandle;

	public PersistentData() {
		super();
		mCompatibilityHandle = null;
		mKey = -1;
		mLoaded = false;
	}

	protected OLD_DATA data()
	{
		return mCompatibilityHandle;
	}
	
	public PersistentData(OLD_DATA compatibilityHandle) {
		super();
		this.mCompatibilityHandle = compatibilityHandle;
		mLoaded = false;
	}

	public boolean isLoaded()
	{
		return mLoaded;
	}
	
	protected void markLoaded() {
		mLoaded = true;
	}
	
	public abstract void load() throws StorageException;
}
