package joprod.jitune;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.window.Window;

public class JiTuneViews {

	static public enum ViewIdentifier {
		JITUNE_APP,
		COMPTES_LIST 
	}

	Map<ViewIdentifier, Window> views = new HashMap<ViewIdentifier, Window>();
	
	public void register(ViewIdentifier id, Window view)
	{
		views.put(id,  view);
	}
	
	public Window get(ViewIdentifier id)
	{
		return views.get(id);
	}
}
