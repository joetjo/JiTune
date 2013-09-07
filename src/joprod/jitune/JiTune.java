package joprod.jitune;

import joprod.jitune.JiTuneViews.ViewIdentifier;
import joprod.jitune.data.storage.Storage;
import joprod.jitune.gui.actions.Actions;
import joprod.jitune.gui.panels.JtGuiAccount;
import joprod.jitune.resources.JTStrings;
import joprod.jitune.session.Session;
import joprod.rcp.common.login.LoginDialog;

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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class JiTune extends ApplicationWindow {

	public static JiTune APP;
	public static JiTuneViews VIEWS = new JiTuneViews();
	public static Storage STORAGE;
	public static final Session SESSION = new Session();
	
	private JtGuiAccount accountEditor;
	
	/**
	 * Create the application window.
	 * @param user 
	 */
	private JiTune() {
		super(null);
		APP = this;
		VIEWS.register(ViewIdentifier.JITUNE_APP, this);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		
		// TODO Selection de fichier de compte
		STORAGE = new Storage();
	}

	@Override
	public int open() {

		// Identification avant connexion
		final LoginDialog dial = new LoginDialog(getShell(), JTStrings.APPNAME, JTStrings.loginMessage, STORAGE.getDefaultUser());
		final int retcode = dial.open();

		if ( retcode == LoginDialog.OK ) {
			SESSION.setUser(dial.getUser());
		} else {
			shutdown();
		}

		return super.open();
	}

	public void shutdown() {
		close();
		System.exit(0);
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		accountEditor = new JtGuiAccount(parent);

		Actions.selectAccount.run();
		Actions.viewComptesList.run();
		Actions.viewDateSelection.run();

		return accountEditor;
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
		
		MenuManager menuView = new MenuManager(JTStrings.menu_view);
		menuManager.add(menuView);
		menuView.add(Actions.viewComptesList);
		menuView.add(Actions.viewDateSelection);

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

	    final Display display = new Display();
	    final Shell shell = new Shell(display);
	    shell.setText(JTStrings.APPNAME);

		shell.dispose();
		display.dispose();
		
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
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return JiTuneViews.INITIALSIZE_JITUNE_APP;
	}

	@Override
	protected Point getInitialLocation(Point initialSize) {
		return JiTuneViews.INITIALPOS_JITUNE_APP;
	}

	public void warningMessage(final String title, final String message)
	{
		MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_WARNING /* | SWT.ABORT | SWT.RETRY | SWT.IGNORE */);
		messageBox.setText(title);
		messageBox.setMessage(message);
		messageBox.open();
	}

	public JtGuiAccount getAccountEditor() {
		return accountEditor;
	}
}
