package sample.Chain;

import sample.Chain.ChainLink;
import sample.Implementation.HasStatus;

/**
 * ChainLinkProcess is a subprocess of a ChainLink
 * e.g. Miners of a Mine or ProductionLines of a Factory
 */
public abstract class ChainLinkProcess implements HasStatus {
	
	protected ChainLink link;
	
	public ChainLinkProcess(ChainLink link) { 
		this.link = link;
	}

	// getNumOfProducts the ChainLink that this process belongs to
	public ChainLink getChainlink() {
		return this.link;
	}

	@Override
	public abstract String getStatus();
	
	public abstract void setStatus(String status);
}