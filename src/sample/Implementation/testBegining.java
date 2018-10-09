package sample.Implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static sample.Implementation.Starting .*;


import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sample.Factory.PureSteelFactory;
import sample.Factory.SteelProductFactory;
import sample.Mine.Mine;
import sample.Store.Store;


public class testBeginning<links2, links1> {//TODO have to improve test
    /*private Map<String, InetSocketAddress> links1 = new HashMap<String, InetSocketAddress>();
    Map<String, InetSocketAddress> links2 = new HashMap<String, InetSocketAddress>();
    Starting.startLinks(links1,links2);


    private Store store = startStoreLine1(links1);
    private String status;
    private Mine mine = startMine1(links1, "Mine", "PurifiedSteelFactory");
    private int numOfMiners;
    private PureSteelFactory factory1 = startPureSteelFactory(links1);
    private SteelProductFactory factory2 = startSteelProductFactory(links1, "SteelProductFactory", "Store", "PurifiedSteelFactory", 2, 0);

    @Before
    public void setUp() {

        status = store.getStatus();
        numOfMiners = mine.getMiners().size();
    }

    @Test
    public void testCreation() {
        assertTrue(numOfMiners == 2);
        assertEquals(status, "initialised");
    }

    @After
    public void tearDown() {
        mine = null;
        factory1 = null;
        factory2 = null;
        status = null;
    }

*/
}
