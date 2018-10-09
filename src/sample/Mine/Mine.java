package sample.Mine;

import sample.Chain.ChainLink;
import sample.Factory.Product;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mine extends ChainLink {

    // the frame that displays the status of the mine and the miners
    private MineFrame frame;
    //collection of miners belonging to the mine
    private ArrayList<Miner> miners = new ArrayList<>();
    private static Logger logger = Logger.getLogger(Mine.class.getName());

    public Mine(InetSocketAddress address, InetSocketAddress[] nextLinks, int numberOfMiners) {
        super(address, nextLinks, new InetSocketAddress[0]);

        // at least one miner
        if (numberOfMiners < 1) {
            numberOfMiners = 1;
        }
        createMiners(numberOfMiners); // create miner Objects

        // first create the window because as soon as the miner is done it'll try and update the label  in the GUI
        // send Mine object to MineFrame, such that it can include the miners etc.
        frame = new MineFrame(this);
        frame.setVisible(true);
        startMiners();// only after the window has been created we will start the miners

    }

    private void startMiners() {
        for (Miner miner : miners) {    // miner is identified by its corresponding mine and a number
            new Thread(miner).start();  // for every miner create a separate thread
        }
    }

    private void createMiners(int numberOfMiners) {
        for (int i = 0; i < numberOfMiners; i++) {
            //send instance of this mine to the miner, so that it can send back the ore
            Miner miner = new Miner(this);
            miners.add(i, miner);        // add to the list of miners belonging to the mine
        }
    }

    public MineFrame getFrame() {
        return frame;
    }

    public void processOre(Product product) {
        // Mine received ore from miner
        if (product != Product.Ore) {
            throw new UnsupportedOperationException("The mine can't accept " + product.toString());
        }

        try {
            send(product);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error!Unable to send ore to factory" + e.getMessage());
        }
    }

    public ArrayList<Miner> getMiners() {
        return miners;
    }

    @Override
    public void handleMessage(String s) {   // mine cannot accept products
        throw new UnsupportedOperationException();
    }

    @Override
    public String getStatus() {
        return "Open";
    }
}