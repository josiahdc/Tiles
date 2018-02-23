package game.panels.sections;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import game.panels.StatusPane;
import game.panels.sections.components.GButton;

public class NavSection extends JComponent implements ActionListener {
	private StatusPane parent;
	private GButton swapBuild;
	private GButton swapResource;
	private GButton t1;
	private GButton t2;
	private GButton t3;
	private GButton t4;
	
	// setup
	public NavSection(StatusPane parent) {
		this.parent = parent;
		this.setLayout(new GridLayout(3, 2));
		
		// prep buttons
		swapBuild = new GButton();
		swapBuild.setText("Build");
		swapBuild.setActionCommand(StatusPane.BUILD);
		swapBuild.addActionListener(this);
		
		swapResource = new GButton();
		swapResource.setText("Resources");
		swapResource.setActionCommand(StatusPane.RESOURCE);
		swapResource.addActionListener(this);
		swapResource.setEnabled(false);
		
		t1 = new GButton();
		t1.setText("Future Feature");
		t1.setActionCommand(StatusPane.RESOURCE);
		t1.addActionListener(this);
		t1.setEnabled(false);
		
		t2 = new GButton();
		t2.setText("Future Feature");
		t2.setActionCommand(StatusPane.RESOURCE);
		t2.addActionListener(this);
		t2.setEnabled(false);
		
		t3 = new GButton();
		t3.setText("Future Feature");
		t3.setActionCommand(StatusPane.RESOURCE);
		t3.addActionListener(this);
		t3.setEnabled(false);
		
		t4 = new GButton();
		t4.setText("Future Feature");
		t4.setActionCommand(StatusPane.RESOURCE);
		t4.addActionListener(this);
		t4.setEnabled(false);
		
		// add buttons
		this.add(swapBuild);
		this.add(swapResource);
		this.add(t1);
		this.add(t2);
		this.add(t3);
		this.add(t4);
		
		//housekeeping
		this.setFocusable(false);
		}

	// button click listener
	public void actionPerformed(ActionEvent e) {
		parent.switchView(e.getActionCommand());
	}
}
