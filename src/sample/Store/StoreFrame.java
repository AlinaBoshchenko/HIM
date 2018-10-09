package sample.Store;

import sample.Chain.ChainLinkFrame;

import javax.swing.JLabel;

public class StoreFrame extends ChainLinkFrame {

	private Store store;

	public StoreFrame(Store store) {
		super(store);
		this.store = store;
	}

	@Override
	protected void addLabels() {
		JLabel statusLabel = new JLabel(store.getStatus());
		super.addToPanel(statusLabel);
		labels.put(store, statusLabel);
	}
}