package sample.Mine;

public enum MinerStatus {
	/**
	 *finished - miner paused, product is ready
	 * initialised - init thread, not mining
	 * mining - mining product
	 */
	finished,
	initialised,  // TODO very strange enum. should be removed?
	mining
}