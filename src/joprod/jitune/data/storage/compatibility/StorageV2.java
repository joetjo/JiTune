package joprod.jitune.data.storage.compatibility;

import java.io.File;

import javax.swing.JOptionPane;

import joetjo.jtune.jtuneapp.configuration.ConfigurationDialog;
import joetjo.jtune.jtunecore.cfg.Configuration;
import joetjo.jtune.jtunecore.cfg.ConfigurationException;
import joetjo.jtune.jtunecore.storage.memory.StorageException;
import joetjo.jtune.jtunecore.storage.xml.XmlStorage;
import joetjo.jtune.jtunecore.storage.xml.basic.Jtune.Comptes.CompteRef;
import joprod.jitune.data.Compte;
import joprod.jitune.data.Comptes;

/**
 * Compatibility module with JTuneV2
 */
public class StorageV2 {

    private joetjo.jtune.jtunecore.Storage mStorage;
    private Configuration mConfiguration;

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
        } catch (StorageException ex) {
            JOptionPane.showMessageDialog(null, "Failed to load account file : " + filename + " - Configuration file " + cfg.getAbsolutePath()); // TODO internationalisation

            ConfigurationDialog dialog = new ConfigurationDialog(null, true);
            dialog.loadFromData(mConfiguration);
            dialog.pack();
            dialog.setVisible(true);
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

	public String getDefaultUser() {
		return mConfiguration.getDefaultUser();
	}

    
}
