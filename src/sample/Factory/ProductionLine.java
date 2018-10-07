package sample.Factory;

import sample.Chain.ChainLinkProcess;
import sample.Chain.StatusUpdater;
import sample.Mine.Miner;

import javax.swing.SwingUtilities;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ProductionLine extends ChainLinkProcess implements Runnable {

	private Factory factory;
	protected ProductionLineStatus status;
	private Backlog backlog;
	private static Logger log = Logger.getLogger(Miner.class.getName());

	public ProductionLine(Factory factory, Backlog backlog) {  // production line knows the factory and its backlog
		super(factory);
		this.backlog = backlog;
		this.factory = factory;
		this.status = ProductionLineStatus.initialised;
	}

	public void run() {
		while(true) {
			// don't do anything when there's no product on the production line
			if (backlog.getNumOfProducts() > 0) {
				setStatus(ProductionLineStatus.producing.toString());

				sleep(1000);

				// do the actual conversion
				Product product = convert();

				// from production line to factory
				this.factory.acceptProduct(product);

				backlog.decrementNumOfProducts();

				setStatus(ProductionLineStatus.nonactive.toString());
			}
		}
	}

	
	@Override
	public String getStatus() {
		return this.status.toString();
	}

	@Override
	public void setStatus(String status) {
		this.status = ProductionLineStatus.valueOf(status);
		// invoke GUI updater on EDT 
		SwingUtilities.invokeLater(new StatusUpdater(this, this.getChainlink()));
	}

	private void sleep(Integer time) {
		try {
			Thread.sleep(time);			// mining takes 1 second
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception: ", e);
		}
	}

	public Factory getFactory() {
		return this.factory;
	}
	
	// this is implemented in subclasses
	public abstract Product convert() ;
}