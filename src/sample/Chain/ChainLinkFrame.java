package sample.Chain;

import sample.Implementation.EndingListener;
import sample.Implementation.HasStatus;
import sample.Implementation.LayoutCount;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public abstract class ChainLinkFrame extends JFrame {
		
	private ChainLink chainlink;
	private JPanel panel;
	// ChainLinkProcess and corresponding status label
	protected Map<HasStatus, JLabel> labels = new HashMap<HasStatus, JLabel>();

	public ChainLinkFrame(ChainLink chainlink) { //constructor
		this.chainlink = chainlink;
		// create GUI on EDT
		SwingUtilities.invokeLater(this::createGui);
	}
			
	
	private void createGui() {
		setSize(LayoutCount.windowWidth, LayoutCount.windowHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// reveal properly besides and below each other
		setLocation(LayoutCount.getHorizontalPixels(), LayoutCount.getVerticalPixels());	
		LayoutCount.addPixels();
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(20, 1)); 
		add(panel);
		
		// label with name and port
		JLabel chainLinkName = new JLabel(chainlink.getClass().toString() + chainlink.getAddress().getPort());
		panel.add(chainLinkName);
		
		// labels of subprocesses
		addLabels();
		
		JButton endButtom = new JButton("Click to terminate");  
		endButtom.addActionListener(new EndingListener());
		add(endButtom, BorderLayout.SOUTH);
	}
	
	protected abstract void addLabels();

	protected void addToPanel(Component c){
		panel.add(c);
	}

	// update status in panel (see class StatusUpdater)
	public void setStatus(HasStatus proces, String status) {
		labels.get(proces).setText(status);
		
		// redraw the panel
		panel.revalidate();
	}
}