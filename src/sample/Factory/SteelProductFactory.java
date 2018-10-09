package sample.Factory;
import sample.Mine.Mine;

import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SteelProductFactory extends Factory {

	private static Logger logger = Logger.getLogger(SteelProductFactory.class.getName());

	public SteelProductFactory(InetSocketAddress address, InetSocketAddress[] nextLinks, InetSocketAddress[] previousLinks, int numberOfCableLines, int numberOfPlateLines) {
		super(address, nextLinks, previousLinks);

		createCableProdLine(numberOfCableLines);

		createPlateProdLine(numberOfPlateLines);
	}

	private void createPlateProdLine(int numberOfPlateLines) {
		for(int i = 0; i < numberOfPlateLines; i++) {
			// create plate production line
			SteelPlateProductionLine line = new SteelPlateProductionLine(this, backlog);
			Thread t = new Thread(line);
			t.start();
		}
	}

	private void createCableProdLine(int numberOfCableLines) {
		for(int i = 0; i < numberOfCableLines; i++) {
			// create cable production line
			SteelCableProductionLine line = new SteelCableProductionLine(this, backlog);
			Thread t = new Thread(line);
			t.start();
		}
	}

	public boolean checkProduct(Product product) {
		// only accept PurifiedSteel
		if (product != Product.PurifiedSteel) {
			throw new UnsupportedOperationException("SteelProductFactory does not accept anything other than PurifiedSteel");
		} else {
			return true;
		}
	}

	public  void acceptProduct(Product product) {
		if (product == Product.SteelCable || product == Product.SteelPlate){
			try {
				send(product);
			} catch (Exception e) {
				logger.log(Level.WARNING, "Error!Unable to send ore to factory" + e.getMessage());
			}
		} else {
			throw new UnsupportedOperationException("Factory expected to receive " + Product.SteelCable.toString() + " or " + Product.SteelPlate.toString() + " but got " + product.toString());
		}
	}
}
