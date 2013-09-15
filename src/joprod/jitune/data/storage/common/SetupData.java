package joprod.jitune.data.storage.common;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import joprod.jitune.JiTune;
import joprod.jitune.data.storage.StorageException;
import joprod.jitune.resources.JTStrings;

public abstract class SetupData implements Externalizable {

	private transient String filename;
	
	public SetupData() {
	}	
	
	public SetupData(String filename) {
		this.filename = filename;
	}

	public void save() {
	    FileOutputStream fos;
		try {
			fos = new FileOutputStream(filename);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(this);
		    oos.flush();
		    oos.close();
		} catch (IOException e) {
			JiTune.APP.warningMessage("Failed to save data", "Unable to save file \n" + filename + "\n" + e.getMessage() );
		}
	}

	public static List<String> parseList(final String rawList) {
		final StringTokenizer tk = new StringTokenizer(rawList, ",");
		final List<String> result = new ArrayList<String>();
		
		while( tk.hasMoreTokens() ) {
			result.add(tk.nextToken());
		}
		
		return result;
	}

	public static String toCSVString(List<String> list) {
		StringBuilder result = new StringBuilder("");
		if ( list != null ) {
			for ( String s : list ) {
				result.append(s);
				result.append(",");
			}
		}
		return result.toString();
	}
	
	public static SetupData loadData(String filename) throws FileNotFoundException, StorageException {
		SetupData result;
	    FileInputStream fis = null;
		try {
			fis = new FileInputStream( filename );
		    ObjectInputStream ois = new ObjectInputStream(fis);
		    result = (SetupData) ois.readObject();
		    result.filename = filename;
		    ois.close();
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
		}
		return result;
	}

}