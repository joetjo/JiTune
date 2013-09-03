package joprod.jitune;

import joprod.jitune.data.Compte;
import joprod.jitune.data.storage.Storage;
import joprod.jitune.gui.actions.Actions;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class JiTune extends ApplicationWindow {

	public static JiTune APP;
	public static Storage STORAGE;

	private Compte mActiveAccount = null;
	
	/**
	 * Create the application window.
	 */
	private JiTune() {
		super(null);
		APP = this;
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		
		// TODO Login + Selection de fichier
		STORAGE = new Storage();
	}

	@Override
	public int open() {
		if ( mActiveAccount == null )
		{
			Actions.selectAccount.run();
		}
		return super.open();
	}

	public void shutdown() {
		close();
		System.exit(0);
	}

	public void setActiveAccount(Compte c)
	{
		if ( c == null &&
		     mActiveAccount == null )
		{
			shutdown();
		}
		
		if ( c != null && c != mActiveAccount ) {
			mActiveAccount = c;
			loadAccount();
		}
	}
	
	private void loadAccount() {
		if ( getShell() != null )  {
			getShell().setText("JTune - " + mActiveAccount.getName());
		}
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
		Actions.initialise();
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		
		MenuManager menuFile = new MenuManager(JTStrings.menu_file);
		menuManager.add(menuFile);

		menuFile.add(Actions.selectAccount);
		menuFile.add(new Separator());
		menuFile.add(Actions.exit);
		
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			JiTune window = new JiTune();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(JTStrings.APPNAME);
		if ( mActiveAccount != null ) {
			newShell.setText(JTStrings.APPNAME + " - " + mActiveAccount.getName());
		}
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(800, 600);
	}

}
