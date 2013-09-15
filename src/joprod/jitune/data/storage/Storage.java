package joprod.jitune.data.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import joprod.jitune.JiTune;
import joprod.jitune.data.Categorie;
import joprod.jitune.data.Compte;
import joprod.jitune.data.Comptes;
import joprod.jitune.data.storage.common.SetupData;
import joprod.jitune.data.storage.compatibility.StorageV2;

public class Storage {

	/** Data de JiTune V2 */
	private StorageV2 mOldStorage;
	/** Setup specifique JiTune V3 */
	private JiTuneSetup setup;
	private HashMap<Compte, JiCompteSetup> comptesSetup = new HashMap<>();
	
	private Comptes mCachedComptes;
	private List<Categorie> mCachedCategories = null;

	public Storage() throws StorageException {
		mOldStorage = new StorageV2();
		loadSetup();
	}

	public JiTuneSetup getSetup() {
		return setup;
	}

	public String getDefaultUser() {
		return mOldStorage.getDefaultUser();
	}

	public Comptes getComptes() {
		if ( mCachedComptes == null )
		{
			mCachedComptes =  mOldStorage.loadComptes();
			try {
				mCachedComptes.load();
			} catch (StorageException e) {
				JiTune.APP.warningMessage("Erreur de chargement", e.getMessage());
			}
		}
		return mCachedComptes;
	}

	public List<Categorie> getCategories() {
		if ( mCachedCategories == null )
		{
			mCachedCategories =  mOldStorage.loadCategories();
		}
		return mCachedCategories;
	}

	public JiCompteSetup getCompteSetup(Compte account) {
		JiCompteSetup set = comptesSetup.get(account);
		if ( set == null ) {
			set = loadCompteSetup(account.getName());
			if ( set.getCategories().isEmpty() ) {
				for( Categorie c : getCategories() ) {
					set.add(c.getName());
				}
				set.save();
			}
			comptesSetup.put(account, set);
		}
		return set;
	}

	private void loadSetup() {
		String filename = mOldStorage.getDirectory().getAbsolutePath() 
   				+ File.separator
   				+ JiTuneSetup.FILENAME;

		try {
			setup = (JiTuneSetup) SetupData.loadData(filename);
		} catch (StorageException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
		}
		finally {
			if ( setup == null ) {
				setup = new JiTuneSetup(filename);
				setup.setDefaultValue();
				setup.save();
			}
		}
	}

	private JiCompteSetup loadCompteSetup(String id) {
		String filename = mOldStorage.getDirectory().getAbsolutePath() 
   				+ File.separator
   				+ id + JiCompteSetup.FILENAME;
		JiCompteSetup result = null;
		try {
			result = (JiCompteSetup) SetupData.loadData(filename);
		} catch (StorageException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
		}
		finally {
			if ( result == null ) {
				result = new JiCompteSetup(filename);
				result.save();
			}
		}
		return result;
	}

}
