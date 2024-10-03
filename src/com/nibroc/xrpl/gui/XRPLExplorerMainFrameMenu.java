package com.nibroc.xrpl.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class XRPLExplorerMainFrameMenu extends JMenuBar implements ActionListener {
	
	public XRPLExplorerMainFrameMenu mainFrameMenu;
	
	public XRPLExplorerMainFrameMenu() {
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		add(fileMenu);        
		
        JMenuItem menuItemClose = new JMenuItem("Close",KeyEvent.VK_C);
        menuItemClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        menuItemClose.addActionListener(this);
        fileMenu.add(menuItemClose);
        
		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_E);
		add(editMenu);
		
		JMenu helpMenu = new JMenu("Help");
		editMenu.setMnemonic(KeyEvent.VK_H);
		add(helpMenu);	
		
        JMenuItem menuItemAbout = new JMenuItem("About XRPLExplorer",KeyEvent.VK_A);
        menuItemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        menuItemAbout.addActionListener(this);
        helpMenu.add(menuItemAbout);
        
        mainFrameMenu = this;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem sourceJMenuItem;
		if(e.getSource() instanceof JMenuItem) {
        	sourceJMenuItem = (JMenuItem)(e.getSource());
			if(sourceJMenuItem.getText().equalsIgnoreCase("Close")) {
				SwingUtilities.getWindowAncestor(this).dispose();
			}
			
			if(sourceJMenuItem.getText().equalsIgnoreCase("About XRPLExplorer")) {
				
		        SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
				
			            JFrame aboutFrame = new JFrame();
			            aboutFrame.setSize(400,200);
			            aboutFrame.setResizable(false);
			            aboutFrame.setTitle("About XRPLExplorer");
			            aboutFrame.setLocationRelativeTo(null);
			            //final MainFrameAboutPanel mfap = new MainFrameAboutPanel();
			            //aboutFrame.add(mfap);
			            aboutFrame.setVisible(true);
	            
		            }
		        });
			}
		}
	}
}
