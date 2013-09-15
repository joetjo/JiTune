package joprod.jitune.data.storage.compatibility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import joetjo.jtune.jtuneapp.configuration.ConfigurationDialog;
import joetjo.jtune.jtunecore.cfg.Configuration;
import joetjo.jtune.jtunecore.cfg.ConfigurationException;
import joetjo.jtune.jtunecore.storage.memory.StorageException;
import joetjo.jtune.jtunecore.storage.xml.XmlStorage;
import joetjo.jtune.jtunecore.storage.xml.basic.Jtune.Comptes.CompteRef;
import joprod.jitune.JiTune;
import joprod.jitune.data.Categorie;
import joprod.jitune.data.Compte;
import joprod.jitune.data.Comptes;
import joprod.jitune.resources.JTStrings;

/**
 * Compatibility module with JTuneV2
 */
public class StorageV2 {

	/** Database au format XML (JiTune V2) */
    private joetjo.jtune.jtunecore.Storage mStorage;
    /** Configuration héritée de JiTune V2) */
    private Configuration mConfiguration;
    /** Répertoire racine des données */
    private File directory;
    
    public StorageV2() {
        // Chargement de la configuration
        String cfgFile = System.getProperty("user.home") + File.separator + ".jtune.cfg";

        File cfg = new File(cfgFile);
        mConfiguration = new Configuration(cfg);
        try {
            mConfiguration.init();
        } catch (ConfigurationException ex) {
            JOptionPane.showMessageDialog(null, "Init configuration failed. Configuration file : " + cfg.getAbsolutePath()); // TODO internationalisation
        }
        String filename = mConfiguration.getFileConfiguration().getLocalisation();
        try {
            mStorage = new XmlStorage(filename);
            directory = new File(filename).getParentFile();
        } catch (StorageException ex) {
            JiTune.APP.warningMessage(JTStrings.storage_error_title, "Failed to load account file : " + filename + " - Configuration file " + cfg.getAbsolutePath()); // TODO internationalisation

            ConfigurationDialog dialog = new ConfigurationDialog(null, true);
            dialog.loadFromData(mConfiguration);
            dialog.pack();
            dialog.setVisible(true);
            
            JiTune.APP.shutdown();
        }
	}

	public Comptes loadComptes() {
		joetjo.jtune.jtunecore.storage.xml.basic.Jtune.Comptes cpt = mStorage.getComptesHelper().getComptes().getComptes();

		Comptes cpts = new Comptes();
		for ( CompteRef ref : cpt.getCompteRef() ) {
			cpts.add(new Compte(ref));
		}
		return cpts;
	}

	public List<Categorie> loadCategories() {
		List<Categorie> categories = new ArrayList<Categorie>();

		joetjo.jtune.jtunecore.storage.xml.basic.Jtune.Categories cats = mStorage.getCategories();
		for ( joetjo.jtune.jtunecore.storage.xml.basic.Jtune.Categories.Category c : cats.getCategory() ) {
			categories.add(new Categorie(c));
		}
		return categories;
	}

	public String getDefaultUser() {
		return mConfiguration.getDefaultUser();
	}

	public File getDirectory() {
		return directory;
	}


    
}
