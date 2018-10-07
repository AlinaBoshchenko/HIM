package sample.Factory;

import sample.Mine.Mine;

import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PureSteelFactory extends Factory {

	private static Logger logger = Logger.getLogger(Mine.class.getName());

	public PureSteelFactory(InetSocketAddress address, InetSocketAddress[] nextLinks, InetSocketAddress[] previousLinks, int numberOfLines) {
		super(address, nextLinks, previousLinks);
		// create production lines
		createLines(numberOfLines);
	}

	private void createLines(int numberOfLines) {
		for(int i = 0; i < numberOfLines; i++) {
			PurifiedSteelProductionLine line = new PurifiedSteelProductionLine(this, backlog);
			Thread t = new Thread(line);
			t.start();					// each production line is separate thread
		}
	}

	public boolean checkProduct(Product product) {
		// only accept Ore
		if (product != Product.Ore) {
			throw new UnsupportedOperationException("PureSteelFactory does not accept anything other than Ore");
		} else {
			return true;
		}
	}

	public  void  acceptProduct(Product product) {
		if (product == Product.PurifiedSteel){
			try {
				send(product);
			} catch (Exception e) {
				logger.log(Level.WARNING, "Error!Unable to send ore to factory" + e.getMessage());
			}
		} else {
			throw new UnsupportedOperationException("Factory expected to receive " + Product.PurifiedSteel.toString() + " but got " + product.toString());
		}
	}
}
