package com.nibroc.xrpl.gui;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

@SuppressWarnings("serial")
public class XRPLExplorerMainPanel extends JPanel {
	public CardLayout cardLayout;
	public JPanel cardPanel;
	Properties properites = null;
	public JScrollPane treePane;
	public XRPLExplorerAccountTabbedPane tabbedPane;

	public XRPLExplorerMainPanel() throws Exception {
		super(new GridLayout(1,0));
		treePane = new JScrollPane(new XRPLExplorerMainTree());
		tabbedPane = new XRPLExplorerAccountTabbedPane();
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		cardPanel.add("Search", tabbedPane);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePane, cardPanel);
		splitPane.setDividerLocation(150); 
		add(splitPane);
	}
}
