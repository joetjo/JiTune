package joprod.jitune.gui.panels;

import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.util.ArrayList;
import java.util.List;

import joprod.jitune.JiTune;
import joprod.jitune.data.Compte;
import joprod.jitune.data.CompteMensuel;
import joprod.jitune.data.Operation;
import joprod.jitune.data.storage.JiCompteSetup;
import joprod.jitune.data.storage.StorageException;
import joprod.jitune.gui.actions.Actions;
import joprod.jitune.gui.actions.NewCredit;
import joprod.jitune.gui.actions.NewDebit;
import joprod.jitune.gui.actions.NewVirement;
import joprod.jitune.gui.actions.TitleAction;
import joprod.jitune.gui.actions.copyAction;
import joprod.jitune.gui.events.JiTuneEvent;
import joprod.jitune.gui.events.JiTuneEventListenener;
import joprod.jitune.gui.events.JiTuneSubscription;
import joprod.jitune.gui.panels.table.AccountContentProvider;
import joprod.jitune.gui.panels.table.LineTableAcountView;
import joprod.jitune.resources.JTRes;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class JtGuiAccount extends Composite {

	private Compte account = null;
	
	private TableViewer tableView;
	private List<LineTableAcountView> content;
	
	final private JiTuneSubscription dateListener;
	
	public JtGuiAccount(Composite parent) {
		super(parent, SWT.None);
		
		create();
		
		dateListener = new JiTuneSubscription(JiTuneEvent.GLOBAL_DATE_UPDATED, new JiTuneEventListenener() {
			@Override
			public void eventRaised(JiTuneEvent evt) {
				dateUpdated();
			}
		});

	}
	
	final private void close() {
		dateListener.unsubscribe();
	}

	final private void create() {
		setBackground(JTRes.yellow);
	    setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    setLayout(new FillLayout());

	 // Define the TableViewer
	    tableView = new TableViewer(this, SWT.MULTI | SWT.H_SCROLL
	          | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
	    tableView.setContentProvider(new AccountContentProvider());

	    // Mise en place des tooltip pour la liste des opérations par cellules.
	    ColumnViewerToolTipSupport.enableFor(tableView, ToolTip.NO_RECREATE); 
	    
	    // Create the columns 
	    // Not yet implemented
	    createColumns();

	    createMenus();
	    
	    // Make lines and make header visible
	    final Table table = tableView.getTable();
	    table.setHeaderVisible(true);
	    table.setLinesVisible(true); 	
	}

	private int currentColumnIndex = 0;
	private void createMenus() {
		final MenuManager menuMgr = new MenuManager();
		final Menu menu = menuMgr.createContextMenu(tableView.getControl());

		tableView.getTable().addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				final Table table = tableView.getTable();
				Rectangle clientArea = table.getClientArea();
		        Point pt = new Point(event.x, event.y);
		        int index = table.getTopIndex();
		        while (index < table.getItemCount()) {
		          boolean visible = false;
		          TableItem item = table.getItem(index);
		          for (int i = 0; i < table.getColumns().length; i++) {
		            Rectangle rect = item.getBounds(i);
		            if (rect.contains(pt)) {
		            	currentColumnIndex = i;
		            	System.out.println("Item " + index + "-" + i);
		            }
		            if (!visible && rect.intersects(clientArea)) {
		              visible = true;
		            }
		          }
		          if (!visible)
		            return;
		          index++;
		        }
		        menuMgr.setVisible(true);
		      }
		});

		menuMgr.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				if(tableView.getSelection().isEmpty()) {
					return;
				}
				
				if(tableView.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection) tableView.getSelection();
					final LineTableAcountView selectedLine = (LineTableAcountView) selection.getFirstElement();
					if ( currentColumnIndex > 0 && currentColumnIndex < 13 ) {
						final int month = currentColumnIndex-1;
						manager.add(new TitleAction(JTStrings.mois[month] + " - " + selectedLine.getCategoryName() ));

						MenuManager newMenu = new MenuManager(JTStrings.new_operation);
						newMenu.add(new NewDebit());
						newMenu.add(new NewCredit());
						newMenu.add(new NewVirement());
						manager.add(newMenu);

						MenuManager copyMenu = new MenuManager(JTStrings.new_operation_from_previous);
						List<Action> actions = new ArrayList<>();
						buildContextualCopyMenu(actions, month-1, JiTune.STORAGE.getSetup().getActiveYear(), selectedLine.getCategoryName(), 0);
						for ( Action act : actions ) {
							copyMenu.add(act);
						}
						manager.add(copyMenu);
					}
				}
			}
		});

		menuMgr.setRemoveAllWhenShown(true);
		tableView.getControl().setMenu(menu);	
	}

	private void createColumns() {

		// create column for the first name
		TableViewerColumn colCategory = new TableViewerColumn(tableView, SWT.NONE);

		colCategory.getColumn().setWidth(150);
		colCategory.getColumn().setText(JTStrings.table_title_category);
		colCategory.setLabelProvider(new ColumnLabelProvider() {
		  @Override
		  public String getText(Object element) {
		    final LineTableAcountView p = (LineTableAcountView) element;
		    return p.getCategoryName();
		  }
		});

		for(int i=0; i<12; i++) {
			final int month = i; 
			TableViewerColumn colMonth = new TableViewerColumn(tableView, SWT.NONE);
			colMonth.getColumn().setText(JTStrings.mois[month]);
			colMonth.getColumn().setAlignment(SWT.RIGHT);
			colMonth.getColumn().setWidth(75);
			colMonth.setLabelProvider(new ColumnLabelProvider() {
				  @Override
				  public String getText(Object element) {
				    final LineTableAcountView p = (LineTableAcountView) element;
				    return p.getMonth(month);
				  }
				  @Override
				  public String getToolTipText(Object element) {
					    final LineTableAcountView p = (LineTableAcountView) element;
					    return p.getTooltip(month);
				  }
				  @Override
				  public Point getToolTipShift(Object object) {
				    return new Point(5, 5);
				  }
				  @Override
				  public int getToolTipDisplayDelayTime(Object object) {
				    return 100; // msec
				  }
				  @Override
				  public int getToolTipTimeDisplayed(Object object) {
				    return -1; // msec
				  }
			});
		}

		TableViewerColumn colTotal = new TableViewerColumn(tableView, SWT.NONE);

		colTotal.getColumn().setWidth(75);
		colTotal.getColumn().setText(JTStrings.table_title_total);
		colTotal.getColumn().setAlignment(SWT.RIGHT);
		colTotal.setLabelProvider(new ColumnLabelProvider() {
		  @Override
		  public String getText(Object element) {
		    final LineTableAcountView p = (LineTableAcountView) element;
		    return p.getTotal();
		  }
		});
	}

	private void dateUpdated() {
		loadAccount();
	}

	public void setAccount(Compte c)
	{
		if ( c == null &&
				account == null )
		{
			JiTune.APP.shutdown();
		}
		
		if ( c != null && c != account ) {
			account = c;
			if ( ! c.isLoaded() ) {
				try {
					c.load();
				} catch (StorageException e) {
					JiTune.APP.warningMessage("Erreur de chargement", e.getMessage());
				}
			}
			loadAccount();
		}
	}
	
	private void loadAccount() {
		if ( getShell() != null )  {
			getShell().setText("JTune - " + account.getName());
			JiTune.APP.setStatus(JiTune.SESSION.getUser() + " / " + account.getName());
		}
		
		LineTableAcountView totalLine = new LineTableAcountView(JTStrings.table_title_total);
		final int year = JiTune.STORAGE.getSetup().getActiveYear();
		content = new ArrayList<LineTableAcountView>();
		JiCompteSetup setup = JiTune.STORAGE.getCompteSetup(account);
		for ( String s : setup.getCategories()) {
			LineTableAcountView line = new LineTableAcountView(s, totalLine);
			content.add(line);
			line.load(account.getYear(year));
		}
		content.add(totalLine);
		tableView.setInput(content); 
	}

	private void buildContextualCopyMenu(List<Action> actions, int month, int year, String categoryName, int deep) {
		if ( month == -1 ) {
			month = 11;
			year = year - 1;
		}
		actions.add(new TitleAction(JTStrings.mois[month]));
		CompteMensuel cpt = account.getYear(year).getMonth(month);
		for( Operation op : cpt.getOperations(categoryName) ) {
			if ( categoryName.equals(op.getCategorie()) ) {
				actions.add(new copyAction(op));
			}
		}
		if ( deep < 3 && actions.size() < 12 ) {
			buildContextualCopyMenu( actions, month-1, year, categoryName, deep+1 );
		}
	}

}
