package sample.Factory;

// manages the stock of raw material for factories
public class Backlog {

	private int numOfProducts;
	public Backlog() {
		numOfProducts = 0;
	}

	public synchronized int getNumOfProducts() {
		return numOfProducts;
	}

	public synchronized void incrementNumOfProducts() {
		numOfProducts++;
		notify();
	}

	public synchronized void decrementNumOfProducts() {
		numOfProducts--;
		notify();
	}
}
