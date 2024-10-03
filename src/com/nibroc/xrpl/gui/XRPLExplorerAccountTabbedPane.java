package com.nibroc.xrpl.gui;

import javax.swing.JTabbedPane;

@SuppressWarnings("serial")

public class XRPLExplorerAccountTabbedPane extends JTabbedPane {
	
	public XRPLExplorerAccountPanel xrplExplorerAccountPanel;
	
	public XRPLExplorerAccountTabbedPane() throws Exception {
		String accountPanelLabelText = "XRPL Accounts";
		xrplExplorerAccountPanel = new XRPLExplorerAccountPanel(accountPanelLabelText);
		this.addTab(accountPanelLabelText, xrplExplorerAccountPanel);
	}
}
