package joprod.jitune.data.storage;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Calendar;
import java.util.Date;

import joprod.jitune.data.storage.common.SetupData;

public class JiTuneSetup extends SetupData {

	private static final long serialVersionUID = 1565L;

	static final String FILENAME = "jituneSetup.dbj";
	static final int    VERSION = 1;

	private Date beginActivePeriod;
	
	public JiTuneSetup() {
	}

	public JiTuneSetup(String filename) {
		super(filename);
	}

	public void setDefaultValue() {
		beginActivePeriod = new Date();
	}
	
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		int version = in.readInt();
		switch ( version ) {
		case VERSION:
			beginActivePeriod = (Date) in.readObject();
		}
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
	      out.writeInt(VERSION);
	      out.writeObject(beginActivePeriod);
	}

	public Date getBeginActivePeriod() {
		return beginActivePeriod;
	}

	public void setBeginActivePeriod(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		beginActivePeriod = cal.getTime();
	}

}
