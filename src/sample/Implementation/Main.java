package sample.Implementation;

import sample.Mine.Mine;
import sample.Factory.PureSteelFactory;
import sample.Factory.SteelProductFactory;
import sample.Store.Store;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
/**
 * REFACTORING
 * for refactoring we did the following(main points):
 * added loggers instead of printing errors on the screen
 * extracted methods
 * renamed variables
 * deleted some unused code
 * united classes into packages
 * created tests
 * added necessary comments
 * added an extra class in order to remove all the methods from main
 */
public class Main {
	public static void main(String[] args) {
		Map<String, InetSocketAddress> links1 = new HashMap<String, InetSocketAddress>();
		Map<String, InetSocketAddress> links2 = new HashMap<String, InetSocketAddress>();
		Starting.startLinks(links1, links2);
		Starting.startAction(links1, links2);
	}
}