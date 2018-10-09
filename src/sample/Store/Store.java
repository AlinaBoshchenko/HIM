package sample.Store;

import sample.Chain.ChainLink;
import sample.Chain.ChainLinkFrame;
import sample.Factory.Product;
import sample.Implementation.HasStatus;
import sample.Chain.StatusUpdater;

import java.net.InetSocketAddress;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.SwingUtilities;

public class Store extends ChainLink implements HasStatus {
	Map<Product, Double> prices = new HashMap<Product, Double>();
	private Double bank = 0d;	// total revenue
	private StoreFrame frame;
	private ShopStatus status = ShopStatus.initialised;

    private double PRICE1 = 75.00;
	private double PRICE2 = 100.00;

	public Store(InetSocketAddress address, InetSocketAddress[] previousLinks) {
		super(address, new InetSocketAddress[0], previousLinks);

		prices.put(Product.SteelPlate, PRICE1);
		prices.put(Product.SteelCable, PRICE2);

		frame = new StoreFrame(this);
		frame.setVisible(true);
	}

	@Override
	public  void handleMessage(String s) {
		Product product = Product.valueOf(s);

		if (!prices.containsKey(product)) {
			throw new UnsupportedOperationException("Shop can't sell " + product.toString());
		}

		setStatus("selling");

		sleep(1000);

		setStatus("finished");

		bank += prices.get(product);
	}

	private void sleep(Integer time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public ChainLinkFrame getFrame() {
		return frame;
	}

	public void setStatus(String status) {
		this.status = ShopStatus.valueOf(status);
		// invoke GUI updater on EDT
		SwingUtilities.invokeLater(new StatusUpdater(this));
	}

	@Override
	public String getStatus() {
		Locale locale = new Locale("en", "US");
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		return "Shop is " + status.toString() + ", " + currencyFormatter.format(bank) + " in the bank";
	}
}