package com.nibroc.xrpl.gui;

import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicLookAndFeel;

import com.bulenkov.darcula.DarculaLaf;

public class XRPLExplorer extends JFrame {
	private static final long serialVersionUID = 1L;
	private static XRPLExplorer mainFrame;
	private XRPLExplorer() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
					createAndShowMainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });		
	}
	
	public static void main(String[] args) {
		XRPLExplorer.getInstance();
	}

    public static XRPLExplorer getInstance() {
        if (mainFrame == null) {
        	mainFrame = new XRPLExplorer();
        }
        return mainFrame;
    }

	private static void createAndShowMainFrame() throws Exception {
		BasicLookAndFeel darculaLookAndFeel = new DarculaLaf();
		try {UIManager.setLookAndFeel(darculaLookAndFeel);} catch (Exception e) {System.out.println("Couldn't use darcula look and feel.");}
        //try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());} catch (Exception e) {System.out.println("Couldn't use system look and feel.");}
        XRPLExplorer frame = XRPLExplorer.getInstance();
        frame.setTitle("XRPLExplorer");
        frame.setLayout(new GridLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(new XRPLExplorerMainFrameMenu());
        XRPLExplorerMainPanel xrplExplorerMainPanel = new XRPLExplorerMainPanel();
        frame.setContentPane(xrplExplorerMainPanel);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
	}
}
