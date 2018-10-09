package sample.Factory;

import sample.Chain.ChainLink;
import sample.Chain.ChainLinkFrame;

import java.net.InetSocketAddress;

public abstract class Factory extends ChainLink {

	protected FactoryFrame frame;
	protected Backlog backlog = new Backlog();  // stock of raw material

	public Factory(InetSocketAddress address, InetSocketAddress[] nextLinks, InetSocketAddress[] previousLinks) {
		super(address, nextLinks, previousLinks);

		frame = new FactoryFrame(this); // create GUI
		frame.setVisible(true);
	}

	@Override
	public void handleMessage(String s) {
		Product product = Product.valueOf(s);

		if (checkProduct(product)) {
			backlog.incrementNumOfProducts();
		}
	}

	public abstract boolean checkProduct(Product product); // subclass checks the product TODO always true

	public abstract void acceptProduct(Product product);  // subclass accepts product

	@Override
	public String getStatus() {
		return "Open";
	}

	@Override
	public ChainLinkFrame getFrame() {
		return frame;
	}
}