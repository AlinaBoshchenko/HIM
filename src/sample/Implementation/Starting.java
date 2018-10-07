package sample.Implementation;

import sample.Factory.PureSteelFactory;
import sample.Factory.SteelProductFactory;
import sample.Mine.Mine;
import sample.Store.Store;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class Starting {
    public static void startLinks(Map<String, InetSocketAddress> links1, Map<String, InetSocketAddress> links2) {

        // production chain 1
        links1.put("Mine", new InetSocketAddress("localhost", 1500));
        links1.put("PurifiedSteelFactory", new InetSocketAddress("localhost", 1600));
        links1.put("SteelProductFactory", new InetSocketAddress("localhost", 1650));
        links1.put("Store", new InetSocketAddress("localhost", 1700));

        // production chain 2
        links2.put("Mine 1", new InetSocketAddress("localhost", 2500));
        links2.put("Mine 2", new InetSocketAddress("localhost", 2510));
        links2.put("PurifiedSteelFactory 1", new InetSocketAddress("localhost", 2600));
        links2.put("PurifiedSteelFactory 2", new InetSocketAddress("localhost", 2610));
        links2.put("SteelProductFactory 1", new InetSocketAddress("localhost", 2650));
        links2.put("SteelProductFactory 2", new InetSocketAddress("localhost", 2660));
        links2.put("SteelProductFactory 3", new InetSocketAddress("localhost", 2670));
        links2.put("Store 1", new InetSocketAddress("localhost", 2700));
        links2.put("Store 2", new InetSocketAddress("localhost", 2710));
    }

    public static void startAction(Map<String, InetSocketAddress> links1, Map<String, InetSocketAddress> links2) {

        startStoreLine1(links1);
        startMine1(links1, "Mine", "PurifiedSteelFactory");
        startPureSteelFactory(links1);
        startSteelProductFactory(links1, "SteelProductFactory", "Store", "PurifiedSteelFactory", 2, 0);


        startStoreLine2(links2, "Store 1", "SteelProductFactory 1", "SteelProductFactory 2");
        startStoreLine2(links2, "Store 2", "SteelProductFactory 2", "SteelProductFactory 3");
        startMine1(links2, "Mine 1", "PurifiedSteelFactory 1");
        startMine2Line2(links2);
        startPureSteelFactoryLine2(links2);
        startPureSteelFactory2Line2(links2);
        startSteelProductFactory(links2, "SteelProductFactory 1", "Store 1", "PurifiedSteelFactory 1", 2, 0);
        startSteelProductFactory2Line2(links2);
        startSteelProductFactory(links2, "SteelProductFactory 3", "Store 2", "PurifiedSteelFactory 2", 0, 2);
    }

    private static void startSteelProductFactory2Line2(Map<String, InetSocketAddress> links2) {
        SteelProductFactory proFactory2 = new SteelProductFactory(
                links2.get("SteelProductFactory 2"),
                new InetSocketAddress[]{links2.get("Store 1"), links2.get("Store 2")},
                new InetSocketAddress[]{links2.get("PurifiedSteelFactory 1"), links2.get("PurifiedSteelFactory 2")},
                1, 1 //cable lines, plate lines
        );
    }

    private static void startPureSteelFactory2Line2(Map<String, InetSocketAddress> links2) {
        PureSteelFactory purFactory2 = new PureSteelFactory(
                links2.get("PurifiedSteelFactory 2"),
                new InetSocketAddress[]{links2.get("SteelProductFactory 2"), links2.get("SteelProductFactory 3")},
                new InetSocketAddress[]{links2.get("Mine 2")},
                1 // number of production lines
        );
    }

    private static void startPureSteelFactoryLine2(Map<String, InetSocketAddress> links2) {
        PureSteelFactory purFactory1 = new PureSteelFactory(
                links2.get("PurifiedSteelFactory 1"),
                new InetSocketAddress[]{links2.get("SteelProductFactory 1"), links2.get("SteelProductFactory 2")},
                new InetSocketAddress[]{links2.get("Mine 1"), links2.get("Mine 2")},
                2 // number of production lines
        );
    }

    private static void startMine2Line2(Map<String, InetSocketAddress> links2) {
        Mine mine2 = new Mine(
                links2.get("Mine 2"),
                new InetSocketAddress[]{links2.get("PurifiedSteelFactory 1"), links2.get("PurifiedSteelFactory 2")},
                3 // number of miners
        );
    }

    private static void startStoreLine2(Map<String, InetSocketAddress> links2, String s, String s2, String s3) {
        Store store1 = new Store(
                links2.get(s),
                new InetSocketAddress[]{links2.get(s2), links2.get(s3)}
        );
    }

    private static void startSteelProductFactory(Map<String, InetSocketAddress> links1, String steelProductFactory, String store, String purifiedSteelFactory, int i, int i2) {
        SteelProductFactory proFactory = new SteelProductFactory(
                links1.get(steelProductFactory),
                new InetSocketAddress[]{links1.get(store)},
                new InetSocketAddress[]{links1.get(purifiedSteelFactory)},
                i, i2                                    //cable lines, plate lines
        );
    }

    private static void startPureSteelFactory(Map<String, InetSocketAddress> links1) {
        PureSteelFactory purFactory = new PureSteelFactory(
                links1.get("PurifiedSteelFactory"),
                new InetSocketAddress[]{links1.get("SteelProductFactory")},
                new InetSocketAddress[]{links1.get("Mine")},
                1                                            // number of production lines
        );
    }

    private static void startMine1(Map<String, InetSocketAddress> links1, String mine3, String purifiedSteelFactory) {
        Mine mine = new Mine(
                links1.get(mine3),
                new InetSocketAddress[]{links1.get(purifiedSteelFactory)},
                2                                            // number of miners
        );
    }

    private static void startStoreLine1(Map<String, InetSocketAddress> links1) {
        Store store = new Store(
                links1.get("Store"),
                new InetSocketAddress[]{links1.get("SteelProductFactory")}
        );
    }
}



