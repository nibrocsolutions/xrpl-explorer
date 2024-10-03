package com.nibroc.xrpl.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nibroc.xrpl.account.AccountData;
import com.nibroc.xrpl.account.AccountInfo;
import com.nibroc.xrpl.account.XRPLExplorerAccount;
import com.nibroc.xrpl.common.CommonMethods;

@SuppressWarnings("serial")
public class XRPLExplorerAccountPanel extends JPanel {
	Properties propertiesFile = CommonMethods.readPropertiesFile("xrpl-explorer.properties");
    
    String[] criteriaColumnNames = {"Network", "TypeOfSearch", "Account"};
    //Object[][] criteriaData = null;
    Object[][] criteriaData = new Object[][] {
        {"XRPLCluster~wss://xrplcluster.com", "Classic Address/Account", "ra8LZ8ePhR7MPkpuovc4hJyqwPUaxfiaX6"}
    };
    DefaultTableModel criteriaTableModel = new DefaultTableModel(criteriaData, criteriaColumnNames); 
    JTable criteriaTable = new JTable(criteriaTableModel);
    String[] networks = propertiesFile.getProperty("networks").split(",");
    @SuppressWarnings("rawtypes")
	JComboBox networkComboBox = new JComboBox();
    String[] typeOfSearchNames = {"Classic Address/Account"};
    @SuppressWarnings("rawtypes")
	JComboBox typeOfSearchComboBox = new JComboBox();
    String[] accounts = propertiesFile.getProperty("accounts").split(",");
    @SuppressWarnings("rawtypes")
	JComboBox accountsComboBox = new JComboBox();
	
    String[] resultsColumnNames = {"ledgerEntryType","account","balance","flags","ownerCount","previousTransactionId","previousTransactionLedgerSequence","sequence","index","ledgerCurrentIndex","validated"};
    Object[][] resultsData = null;
    DefaultTableModel resultsTableModel = new DefaultTableModel(resultsData, resultsColumnNames);
	JTable resultsTable = new JTable(resultsTableModel) {
        public boolean editCellAt(int row, int column, java.util.EventObject e) {
            return false;
         }
	};
	
	@SuppressWarnings("unchecked")
	public XRPLExplorerAccountPanel(String searchPanelLabelText) throws Exception  {
		super(new BorderLayout());
		
		JPanel controlsPane1 = new JPanel(new GridLayout(0,1));
		JPanel controlsPane2 = new JPanel();
		GroupLayout layout = new GroupLayout(controlsPane2);
		controlsPane2.setLayout(layout);
		JScrollPane controlsScrollPane = new JScrollPane(controlsPane2);
		
		JLabel searchCriteriaTableLabel = new JLabel("Account Search Criteria:");
		criteriaTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setVisibleRowCount(criteriaTable, 5);
		resizeColumnWidth(criteriaTable, 200);
		JScrollPane criteriaTableScrollPane = new JScrollPane(criteriaTable);
		
		for(int i = 0; i < networks.length; i++) {
			networkComboBox.addItem(networks[i]);
		}
        TableColumn networkColumn = criteriaTable.getColumn("Network");
        networkColumn.setCellEditor(new DefaultCellEditor(networkComboBox));
        
		for(int i = 0; i < typeOfSearchNames.length; i++) {
			typeOfSearchComboBox.addItem(typeOfSearchNames[i]);
		}
        TableColumn typeOfSearchColumn = criteriaTable.getColumn("TypeOfSearch");
        typeOfSearchColumn.setCellEditor(new DefaultCellEditor(typeOfSearchComboBox));
        
		for(int i = 0; i < accounts.length; i++) {
			accountsComboBox.addItem(accounts[i]);
		}
        TableColumn accountColumn = criteriaTable.getColumn("Account");
        accountColumn.setCellEditor(new DefaultCellEditor(accountsComboBox));
		
		JButton criteriaAddButton = new JButton("Add New Criteria");
		criteriaAddButton.addActionListener(new ActionListener() { 
            @SuppressWarnings("rawtypes")
			public void actionPerformed(ActionEvent e) {
        		DefaultCellEditor dce = (DefaultCellEditor)criteriaTable.getCellEditor(); 
        		if (dce != null) dce.stopCellEditing();
            	Vector defaultValues = new Vector();
            	defaultValues.addElement("Click here to add network");
            	defaultValues.addElement("Click here to add type of search");
            	defaultValues.addElement("Click here to add account id");
            	criteriaTableModel.addRow(defaultValues);
            	criteriaTableModel.fireTableDataChanged();
            } 
        });
		
		JButton criteriaRemoveButton = new JButton("Remove Criteria");
		criteriaRemoveButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            	if(criteriaTable.getRowCount() > 0) {
            		DefaultCellEditor dce = (DefaultCellEditor)criteriaTable.getCellEditor(); 
            		if (dce != null) dce.stopCellEditing();
	        		int[] rows = criteriaTable.getSelectedRows();
	        		if(rows.length == 1) {
	            		criteriaTableModel.removeRow(rows[0]);
	            		criteriaTableModel.fireTableDataChanged();
	            		if(rows[0] > 0) {
	            			criteriaTable.setRowSelectionInterval(rows[0] - 1, rows[0] - 1);
	            		}
	            	} else {
	            		JOptionPane.showMessageDialog(null, "Please select only one row and try again.", "Error", JOptionPane.ERROR_MESSAGE);
	            	}
            	}
            } 
        });
		
		JButton criteriaUpButton = new JButton("Up");
		criteriaUpButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
        		DefaultCellEditor dce = (DefaultCellEditor)criteriaTable.getCellEditor(); 
        		if (dce != null) dce.stopCellEditing();
            	criteriaMoveUp();
            }
		});
		
		JButton criteriaDnButton = new JButton("Dn");
		criteriaDnButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
        		DefaultCellEditor dce = (DefaultCellEditor)criteriaTable.getCellEditor(); 
        		if (dce != null) dce.stopCellEditing();
            	criteriaMoveDown();
            }
		});
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
        		DefaultCellEditor dce = (DefaultCellEditor)criteriaTable.getCellEditor(); 
        		if (dce != null) dce.stopCellEditing();
            	if(checkSearchCriteria()) {
            		boolean allRowsRemoved = false;
            		while(resultsTableModel.getRowCount() > 0) {
            			resultsTableModel.removeRow(0);
            			resultsTableModel.fireTableDataChanged();
            			allRowsRemoved = true;
            		}
            		if(allRowsRemoved) {
            		      JOptionPane messagePane = new JOptionPane(
            		              "Resetting account results...",
            		              JOptionPane.INFORMATION_MESSAGE);
            		        final JDialog dialog = messagePane.createDialog("Accounts Reset");
            		        new SwingWorker<Void, Void>() {
            		            @Override
            		            protected Void doInBackground() throws Exception {
            		               Thread.sleep(0); 
            		               return null;
            		            }
            		            protected void done() {
            		               dialog.dispose();
            		            };
            		         }.execute();
            		        dialog.setVisible(true);
            		}
            		pullData(1, 1, getCriteriaFromTable());
            	} else {
            		JOptionPane.showMessageDialog(null, "The search criteria is not set correctly. Please check the search criteria and try again.", "Error", JOptionPane.ERROR_MESSAGE);
            	}
            } 
        });
		
		JLabel resultsTableLabel = new JLabel("Account Results (Double click row to see a row view):");
		resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resultsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resultsTable.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					StringBuffer sb = new StringBuffer();
					String lineSeparator = System.getProperty("line.separator");
					for(int rowCount = 0; rowCount < resultsTableModel.getRowCount(); rowCount++) {
						if(rowCount == row) {
							for(int column = 0; column < resultsTableModel.getColumnCount(); column++) {  
								sb.append(resultsTableModel.getColumnName(column) + ":" + resultsTableModel.getValueAt(row, column) + lineSeparator);
							}
							break;
						}
					}
		            JFrame oneRowFrame = new JFrame();
		            oneRowFrame.setSize(600,300);
		            oneRowFrame.setTitle("XRPL Account One Row View");
		            oneRowFrame.setLocationRelativeTo(null);
		            JTextArea textArea = new JTextArea(36, 50);
		            textArea.setEditable(false);
		            textArea.setText(sb.toString());
		            JScrollPane scrollPane = new JScrollPane(textArea); 
		            oneRowFrame.add(scrollPane);
		            oneRowFrame.addWindowListener(new WindowAdapter() {
		            	  public void windowClosing(WindowEvent e) {
		            		  
		            	  }
		            });
		            oneRowFrame.setVisible(true);
				 }
			 }
		});
		JScrollPane resultsTableScrollPane = new JScrollPane(resultsTable);
        
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(criteriaAddButton)
					.addComponent(criteriaUpButton)
					.addComponent(criteriaDnButton)
					.addComponent(criteriaRemoveButton)
				)
				.addComponent(searchCriteriaTableLabel)
				.addComponent(criteriaTableScrollPane)
				.addComponent(searchButton)
				.addComponent(resultsTableLabel)
				.addComponent(resultsTableScrollPane)
			)
		);
		
		layout.setVerticalGroup(
		layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(criteriaAddButton)
							.addComponent(criteriaUpButton)
							.addComponent(criteriaDnButton)
							.addComponent(criteriaRemoveButton)
						)
					)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(searchCriteriaTableLabel)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(criteriaTableScrollPane)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(searchButton)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(resultsTableLabel)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(resultsTableScrollPane)
				)
		);
        
		controlsPane1.add(controlsScrollPane);
		add(controlsPane1, BorderLayout.CENTER);
	}
	
	public void resizeColumnWidth(JTable table, int minWidth) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = minWidth;
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width > 300)
	            width = 300;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
	
    public void criteriaMoveUp() {
    	if(criteriaTable.getRowCount() > 1 && criteriaTable.getSelectedRow() > 0) {
    		int[] rows = criteriaTable.getSelectedRows();
    		if(rows.length == 1) {
    			criteriaTableModel.moveRow(rows[0], rows[0], rows[0] - 1);
    			criteriaTable.setRowSelectionInterval(rows[0] - 1, rows[0] - 1);
    		} else {
    			JOptionPane.showMessageDialog(null, "More than one row is selected. Please select only one row and try again.", "Error", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    }
    
    public void criteriaMoveDown() {
    	if(criteriaTable.getRowCount() > 1 && criteriaTable.getSelectedRow() > -1) {
    		int[] rows = criteriaTable.getSelectedRows();
    		if(rows.length == 1) {
    			if(criteriaTable.getRowCount() == rows[0] + 1) {
    				//JOptionPane.showMessageDialog(null, "This is the last row. Please select another row and try again.", "Error", JOptionPane.ERROR_MESSAGE);
    			} else {
    				criteriaTableModel.moveRow(rows[0], rows[0], rows[0] + 1);
    				criteriaTable.setRowSelectionInterval(rows[0] + 1, rows[0] + 1);
    			}
    		} else {
    			JOptionPane.showMessageDialog(null, "More than one row is selected. Please select only one row and try again.", "Error", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    }
    
    public void setVisibleRowCount(JTable table, int rows){ 
        int height = 0; 
        for(int row = 0; row < rows; row++) {
            height += table.getRowHeight(row);
        }
        table.setPreferredScrollableViewportSize(new Dimension(table.getPreferredScrollableViewportSize().width, height)); 
    }
    
    public boolean checkSearchCriteria() {
    	boolean returnValue = true;
	    for(int row = 0; row < criteriaTableModel.getRowCount(); row++) {
	        for(int column = 0; column < criteriaTableModel.getColumnCount(); column++) {  
	        	if(((String)criteriaTableModel.getValueAt(row, column)).equalsIgnoreCase("Click here to add network") || ((String)criteriaTableModel.getValueAt(row, column)).equalsIgnoreCase("Click here to add type of search")) {
	        		returnValue = false;
	        		break;
	        	}
	        }
	        
	    }
    	return returnValue;
    }
    
	public long pullData(int currentPage, int pageSize, ArrayList<ArrayList<String>> criteria) {
		long resultCount = 0;
		for (int i = 0; i < criteria.size(); i++) {
			ArrayList<String> criteriaRow = criteria.get(i);
			try {
				String accountInfoJson = XRPLExplorerAccount.getAccountInfoWebSocket(criteriaRow.get(0).split("~")[1], criteriaRow.get(2));
				final ObjectMapper objectMapper = new ObjectMapper();
				AccountInfo accountInfo = objectMapper.readValue(accountInfoJson, AccountInfo.class);
				AccountData accountData = accountInfo.getResult().getAccountData();
				resultsTableModel.addRow(new Object[] {accountData.getLedgerEntryType(), accountData.getAccount(), accountData.getBalance(), accountData.getFlags(), accountData.getOwnerCount(), accountData.getPreviousTxnID(), accountData.getPreviousTxnLgrSeq(), accountData.getSequence(), accountData.getIndex(), accountInfo.getResult().getLedgerCurrentIndex(), accountInfo.getResult().getValidated()});
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error getting account info from XRPL. Error = " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		resizeColumnWidth(resultsTable, 50);
    	return resultCount;
    }
    
	public ArrayList<ArrayList<String>> getCriteriaFromTable() {
		ArrayList<ArrayList<String>> criteria = new ArrayList<ArrayList<String>>();
	    for(int row = 0; row < criteriaTableModel.getRowCount(); row++) {
	    	ArrayList<String> criteriaRow = new ArrayList<String>();
	        for(int column = 0; column < criteriaTableModel.getColumnCount(); column++) {  
	        	criteriaRow.add((String)criteriaTableModel.getValueAt(row, column));
	        }
	        criteria.add(criteriaRow);
	    }
		return criteria;
    }
}
