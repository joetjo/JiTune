package joprod.jitune;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Point;

public class JiTuneViews {

	public static final Point INITIALSIZE_JITUNE_APP     = new Point(1200, 900);
	public static final Point INITIALSIZE_DATE_SELECTION = new Point(INITIALSIZE_JITUNE_APP.x, 80);
	public static final Point INITIALSIZE_COMPTES_LIST   = new Point(250, INITIALSIZE_JITUNE_APP.y + INITIALSIZE_DATE_SELECTION.y );
	public static final Point INITIALSIZE_ACCOUNT_CHECK  = new Point(250, 400);
	public static final Point INITIALSIZE_PROPERTY       = new Point(INITIALSIZE_ACCOUNT_CHECK.x , 
										INITIALSIZE_JITUNE_APP.y + INITIALSIZE_DATE_SELECTION.y - INITIALSIZE_ACCOUNT_CHECK.y);

	public static final Point INITIALPOS_COMPTES_LIST   = new Point(0, 0);
	public static final Point INITIALPOS_DATE_SELECTION = new Point(INITIALSIZE_COMPTES_LIST.x, 0);
	public static final Point INITIALPOS_JITUNE_APP     = new Point(INITIALSIZE_COMPTES_LIST.x, INITIALSIZE_DATE_SELECTION.y);
	public static final Point INITIALPOS_ACCOUNT_CHECK  = new Point(INITIALSIZE_COMPTES_LIST.x + INITIALSIZE_JITUNE_APP.x, 0);
	public static final Point INITIALPOS_PROPERTY       = new Point(INITIALSIZE_COMPTES_LIST.x + INITIALSIZE_JITUNE_APP.x, INITIALSIZE_ACCOUNT_CHECK.y);


	static public enum ViewIdentifier {
		JITUNE_APP,
		COMPTES_LIST,
		DATE_SELECTOR,
		ACCOUNT_CHECK,
		PROPERTY
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
