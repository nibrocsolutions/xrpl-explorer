package com.nibroc.xrpl.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

@SuppressWarnings("serial")
public class XRPLExplorerMainTree extends JTree implements MouseListener,  TreeSelectionListener {
	@SuppressWarnings("rawtypes")
	public HashMap nodesHashmap = new HashMap();
	public String rootName = "XRPL Explorer";
	
	@SuppressWarnings("unchecked")
	public XRPLExplorerMainTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootName);
		nodesHashmap.put(rootName, root);
		addNodeTo(rootName, "Account");
		setModel(new DefaultTreeModel(root));
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		addMouseListener(this);
		addTreeSelectionListener(this);
	}

	@SuppressWarnings("unchecked")
	public DefaultMutableTreeNode getNode(String name) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)(nodesHashmap.get(name));
		if(node == null) {
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(name);
			nodesHashmap.put(name, newNode);
			return newNode;
		} else {
			return node;
		}
	}
	
	public void expandRoot() {
		expandPath(new TreePath(getNode(rootName)));
	}
	 
	public void addNodeTo(String parentNodeName, String childNodeName) {
		DefaultMutableTreeNode parentNode = getNode(parentNodeName);
		DefaultMutableTreeNode childNode = getNode(childNodeName);
		((DefaultTreeModel)getModel()).insertNodeInto(childNode, parentNode, parentNode.getChildCount());
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.getLastSelectedPathComponent();
		if (node == null) return;
		if (node.isLeaf()) {
			//String path = new java.io.File("").getAbsolutePath();
			//JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this).getParent(), "path is " + path, "Warning", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int selRow = this.getRowForLocation(e.getX(), e.getY());
		if(selRow != -1) {
			if(e.getClickCount() == 1) {
				String nodeSelection = "";
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.getLastSelectedPathComponent();
				if (node == null) return;
				if (node.isLeaf()) {
					Object nodeInfo = node.getUserObject();
					nodeSelection = (String)nodeInfo;
					XRPLExplorerMainPanel mainPanel = (XRPLExplorerMainPanel)this.getParent().getParent().getParent().getParent();
					mainPanel.cardLayout.show(mainPanel.cardPanel, nodeSelection);
				}
			} else {
				
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}
