package sample.Mine;

import sample.Chain.ChainLinkProcess;
import sample.Factory.Product;
import sample.Chain.StatusUpdater;

import javax.swing.SwingUtilities;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Miner extends ChainLinkProcess implements Runnable {

	private MinerStatus status;
	private static Logger log = Logger.getLogger(Miner.class.getName());

	public Miner(Mine mine) {
		super(mine);
		this.status = MinerStatus.initialised;  // miner is created
	}

	public void run() {
		while (true) {
			setStatus("mining");

			sleep(1000);

			((Mine)link).processOre(Product.Ore);     // give ore to Mine

			setStatus("finished");
		}
	}

	private void sleep(Integer time) {
		try {
			Thread.sleep(time);			// mining takes 1 second
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception: ", e);
		}
	}

	@Override
	public void setStatus(String status) {
		this.status = MinerStatus.valueOf(status);
		// invoke GUI updater on EDT 
		SwingUtilities.invokeLater(new StatusUpdater(this, this.getChainlink()));
	}

	@Override
	public String getStatus() {
		return this.status.toString();
	}
}