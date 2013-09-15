package joprod.jitune.data.storage;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

import joprod.jitune.data.storage.common.SetupData;

public class JiCompteSetup  extends SetupData {
	
	private static final long serialVersionUID = 1879L;

	static final String FILENAME = /* account name  + */"_Setup.dbj";
	static final int    VERSION = 1;

	private String rawCategories = "";
	private transient List<String> categories = null;
	
	public JiCompteSetup() {
		super();
	}

	public JiCompteSetup(String filename) {
		super(filename);
		categories = new ArrayList<String>();
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		int version = in.readInt();
		switch ( version ) {
		case VERSION:
			rawCategories = (String) in.readObject();
		}
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
	      out.writeInt(VERSION);
	      out.writeObject(toCSVString(categories));
	}

	public List<String> getCategories() {
		if ( categories == null ) {
			categories = parseList(rawCategories);
		}
		return categories;
	}

	public void add(String categorie) {
		categories.add(categorie);
	}

}
