package joprod.jitune.data.storage;


public class StorageException extends Exception {

	private static final long serialVersionUID = 1565L;

	public StorageException(String message, Exception e) {
		super(message, e);
	}

	public StorageException(String message) {
		super(message);
	}

}
