package joprod.jitune;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Point;

public class JiTuneViews {

	public static final Point INITIALPOS_COMPTES_LIST   = new Point(0, 0);
	public static final Point INITIALPOS_DATE_SELECTION = new Point(250, 0);
	public static final Point INITIALPOS_JITUNE_APP     = new Point(250, 100);

	public static final Point INITIALSIZE_COMPTES_LIST   = new Point(250, 700);
	public static final Point INITIALSIZE_DATE_SELECTION = new Point(600, 100);
	public static final Point INITIALSIZE_JITUNE_APP     = new Point(1200, 800);

	static public enum ViewIdentifier {
		JITUNE_APP,
		COMPTES_LIST,
		DATE_SELECTOR
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
