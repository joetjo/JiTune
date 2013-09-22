package joprod.jitune.gui.panels;

import java.util.ArrayList;
import java.util.List;

import joprod.jitune.JiTune;
import joprod.jitune.data.Compte;
import joprod.jitune.data.storage.JiCompteSetup;
import joprod.jitune.data.storage.StorageException;
import joprod.jitune.gui.events.JiTuneEvent;
import joprod.jitune.gui.events.JiTuneEventListenener;
import joprod.jitune.gui.events.JiTuneSubscription;
import joprod.jitune.gui.panels.table.AccountContentProvider;
import joprod.jitune.gui.panels.table.LineTableAcountView;
import joprod.jitune.resources.JTRes;
import joprod.jitune.resources.JTStrings;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

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

	    // Make lines and make header visible
	    final Table table = tableView.getTable();
	    table.setHeaderVisible(true);
	    table.setLinesVisible(true); 	
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

}
