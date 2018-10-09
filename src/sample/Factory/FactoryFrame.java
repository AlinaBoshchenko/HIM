package sample.Factory;

import sample.Chain.ChainLinkFrame;
import sample.Implementation.HasStatus;

import javax.swing.JLabel;

public class FactoryFrame extends ChainLinkFrame {

	public FactoryFrame(Factory factory) { //constructor
		super(factory);
	}

	@Override
	protected void addLabels() {
	}

	@Override
	public void setStatus(HasStatus proces, String status) {		// status info in GUI
		if (!labels.containsKey(proces)) {
			JLabel name = new JLabel("ProductionLine " + labels.size() + ":");
			super.addToPanel(name);
			JLabel statusLabel = new JLabel(proces.getStatus());
			super.addToPanel(statusLabel);
			labels.put(proces, statusLabel);
		}

		super.setStatus(proces, status);
	}
}