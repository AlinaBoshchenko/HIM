package sample.Chain;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputServer implements Runnable {
    private int portNumber;
    private ChainLink link;
    private static Logger logger = Logger.getLogger(InputServer.class.getName());

    public InputServer(ChainLink link, int portNumber) {
        this.portNumber = portNumber;
        this.link = link;
    }

    public void run() {
        try {
            ServerSocket s = new ServerSocket(this.portNumber);  //to receive products or materials
            while (true) {
                // create new socket object for incoming request
                Socket incoming = s.accept();
                // new thread for this request
                Thread t = new Thread(new ServerProcess(link, incoming));
                t.start();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception: ", e);

        }
    }
}
