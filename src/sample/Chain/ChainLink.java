package sample.Chain;

import sample.Factory.Product;
import sample.Mine.Mine;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ChainLink {
	private InetSocketAddress address;
	private InetSocketAddress[] nextLinks;


	public ChainLink(InetSocketAddress address, InetSocketAddress[] nextLinks, InetSocketAddress[] previousLinks) {
		this.address = address;
		this.nextLinks = nextLinks;
		InetSocketAddress[] previousLinks1 = previousLinks;
		Logger logger = Logger.getLogger(Mine.class.getName());

		// only listen for input when there are previous links
		if (previousLinks1.length > 0) {
			try {
				// start the InputServer so that the ChainLink is ready to process the input of products/materials
				Thread inputServer = new Thread(new InputServer(this, this.address.getPort()));
				inputServer.start();
				System.out.println("Listening at " + this.address.getPort());
			} catch (Exception e) {
				//logger.log(Level.WARNING, "Exception: " + e.getMessage());  TODO decision
				logger.log(Level.SEVERE, "Exception: ", e);
							}
		}
	}
	
	// used to update the GUI
	public abstract ChainLinkFrame getFrame();

	public InetSocketAddress getAddress() {
		return address;
	}
	
	// send product to a randomly selected next link
	public void send(Product product) throws IOException {
		if (this.nextLinks.length == 0) {				// no links
			throw new UnsupportedOperationException();
		}

		// getNumOfProducts  random next link
		InetSocketAddress nextLink = chooseRandomNextLink(this.nextLinks);
		
        Socket socket = new Socket(nextLink.getHostName(), nextLink.getPort());

        //Send the message to the server
        SendMessageToServer(socket,product);
        socket.close();
	}
	
	// chooses random ChainLink address from an array of addresses
	public InetSocketAddress chooseRandomNextLink (InetSocketAddress[] links) {
		Random generator = new Random();
		int nextLink = generator.nextInt(links.length);
		return links[nextLink];
	}

	public void SendMessageToServer(Socket socket, Product product) throws IOException {
		//Send the message to the server
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(product.toString());			// 'send' product
		bw.flush();
		//closing opened stream, streamWriter and bufferedWriter
		bw.close();
		osw.close();
		os.close();
	}

	// this is implemented in subclasses
	public abstract void handleMessage(String s);
	
	public abstract String getStatus();
	
	
}