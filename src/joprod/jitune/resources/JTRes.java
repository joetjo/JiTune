package joprod.jitune.resources;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class JTRes {
	public static ImageRegistry ir = new ImageRegistry();

	public static Color blue;
	public static Color yellow;

	public static void init(Display display) {
		blue = display.getSystemColor(SWT.COLOR_BLUE);
		yellow = display.getSystemColor(SWT.COLOR_YELLOW);
	}
}
