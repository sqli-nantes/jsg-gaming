package com.sqli.jsg.amiennemis.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.application.Platform;

import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import com.sqli.jsg.amiennemis.client.model.Game;
import com.sqli.jsg.amiennemis.client.model.Personnage;

public class LocalEndpoint extends Endpoint implements MessageHandler.Whole<String> {

    private String SERVER = "ws://ec2-54-242-90-129.compute-1.amazonaws.com:80/tictactoeserver/endpoint";
//  private String SERVER = "ws://localhost:8080/tictactoeserver/endpoint";
	
	public static AmiEnnemisClient client;
	private Session session;
	
	private Game game;
	
	private static final LocalEndpoint SINGLETON = new LocalEndpoint();
	
	public static LocalEndpoint getInstance() {
		return SINGLETON;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
    public void startGame() throws URISyntaxException, DeploymentException, IOException {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(LocalEndpoint.class, null, new URI(SERVER));
    }
	
	@Override
	public void onOpen(Session session, EndpointConfig config) {
		System.out.println("Endpoint opened, session = "+session+", config = "+config);
		this.session = session;
		session.addMessageHandler(this);
		client.setEndpoint(this);
	}
	
	public void broadcastPosition(Personnage personnage) {
		String x = String.valueOf((int) personnage.getPosition().getX());
		String y = String.valueOf((int) personnage.getPosition().getY());
		
		try {
			session.getBasicRemote().sendText(String.format("%s:%s", x, y));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    @Override
	public void onMessage(final String message) {
//		Platform.runLater(new Runnable() {
//			public void run() {

    	String[] split = message.split(":");
    	game.updatePersonnage(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
//		
//		});

	}

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("closed: " + closeReason);
    }

	void done () throws IOException {
		System.out.println("closing session");
		session.close();
		System.out.println("closed session");
	}
	
	
	
}
