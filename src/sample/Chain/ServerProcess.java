package sample.Chain;/*An instance of ServerProcess is created when the server receives new request*/

import sample.Chain.ChainLink;
import sample.Mine.Miner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerProcess implements Runnable {
    private static Logger log = Logger.getLogger(ServerProcess.class.getName());
    private Socket inputSocket;
    private ChainLink link;

    public ServerProcess(ChainLink link, Socket socket) {
        inputSocket = socket;
        this.link = link;
    }

    public void run() {
        try {
            BufferedReader inPutReader = new BufferedReader(new InputStreamReader(inputSocket.getInputStream()));

            this.link.handleMessage(inPutReader.readLine());    // handleMessage implemented in ChainLink subclasses

            inputSocket.close();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }
}

