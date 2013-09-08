package joprod.jitune.data.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import joprod.jitune.JiTune;
import joprod.jitune.data.Comptes;
import joprod.jitune.data.storage.compatibility.StorageV2;
import joprod.jitune.resources.JTStrings;

public class Storage {

	/** Data de JiTune V2 */
	private StorageV2 mOldStorage;
	/** Setup specifique JiTune V3 */
	private JiTuneSetup setup;
	
	private Comptes mCachedComptes;

	public Storage() throws StorageException {
		mOldStorage = new StorageV2();
		loadSetup();
	}

	private void loadSetup() throws StorageException {
	    FileInputStream fis = null;
		try {
			fis = new FileInputStream( mOldStorage.getDirectory().getAbsolutePath() 
													   + File.separator
													   + JiTuneSetup.FILENAME );
		    ObjectInputStream ois = new ObjectInputStream(fis);
		    setup = (JiTuneSetup) ois.readObject();
		    ois.close();
		} catch (FileNotFoundException e) {
			// Setup absent, 1ére ouverture ? --> Le storage est ouvert donc tout est ok... creation d'un setup vide
			setup = new JiTuneSetup();
			setup.setDefaultValue();
		} catch (IOException e) {
			throw new StorageException(JTStrings.storage_error_io, e);
		} catch (ClassNotFoundException e) {
			throw new StorageException(JTStrings.storage_error_serial, e);
		}
		finally {
			if ( fis != null ) {
				try {
					fis.close();
				} catch (IOException e) {
					// tant pis...
				}
			}
			if ( setup == null ) {
				setup = new JiTuneSetup();
				setup.setDefaultValue();
			}
		}
	}

	public JiTuneSetup getSetup() {
		return setup;
	}

	public void saveSetup() {
	    FileOutputStream fos;
		try {
			fos = new FileOutputStream(mOldStorage.getDirectory().getAbsolutePath() 
					   				+ File.separator
					   				+ JiTuneSetup.FILENAME);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(setup);
		    oos.flush();
		    oos.close();
		} catch (FileNotFoundException e) {
			JiTune.APP.warningMessage(JTStrings.storage_error_title, JTStrings.storage_error_save);
		} catch (IOException e) {
			JiTune.APP.warningMessage(JTStrings.storage_error_title, JTStrings.storage_error_io);
		}
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
