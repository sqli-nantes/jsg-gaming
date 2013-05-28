package com.sqli.jsg.amiennemis.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

import javax.websocket.*;

public class LocalEndpoint extends Endpoint implements MessageHandler.Whole<String> {

	public static AmiEnnemisClient client;
	private Session session;
	
	@Override
	public void onOpen(Session session, EndpointConfig config) {
		System.out.println("Endpoint opened, session = "+session+", config = "+config);
		this.session = session;
		session.addMessageHandler(this);
		client.setEndpoint(this);
	}

    @Override
	public void onMessage(final String message) {
		Platform.runLater(new Runnable() {
			public void run() {

				System.out.println("GOT MESSAGE: " + message);
				if ("p1".equals(message)) {
					client.setInfo("Waiting for a second player to join...");
				}
				if (message.startsWith("p2")) {
					client.setInfo("You play 'O'");
					client.setSymbol(1);
                    client.setOtherSymbol(2);
					client.myTurn(true);
				}
				if ("p3".equals(message)) {
					client.setInfo("You play 'X'");
					client.setSymbol(2);
                    client.setOtherSymbol(1);
				}
				if (message.startsWith("om")){
					int c = Integer.parseInt(message.substring(3));
					client.doMove(c);
				}


			}
		});

	}

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("closed: " + closeReason);
    }

    void myMove(int coords, int symbol) {
		String move = "pm " + coords;
		System.out.println("send this move: "+move);
		try {
			session.getBasicRemote().sendText(move);
		} catch (IOException ex) {
			Logger.getLogger(LocalEndpoint.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	void done () throws IOException {
		System.out.println("closing session");
		session.close();
		System.out.println("closed session");
	}
	
	
	
}
