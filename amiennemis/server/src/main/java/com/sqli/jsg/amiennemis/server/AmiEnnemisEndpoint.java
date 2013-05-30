package com.sqli.jsg.amiennemis.server;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;

@ServerEndpoint(value = "/endpoint")
public class AmiEnnemisEndpoint {

    private static List<Session> clients = new ArrayList<Session>();

    /**
     * Called when a web-socket connection is opened.
     *
     * @param session the web-socket session
     */
    @OnOpen
    public void onOpen(final Session session) throws Exception {
        clients.add(session);
        System.out.println("Client open : " + session);
    }

    /**
     * Called when a web-socket connection is closed.
     *
     * @param session the web-socket session
     */
    @OnClose
    public void onClose(final Session session) throws Exception {
        clients.remove(session);
        System.out.println("Client close : " + session);
    }

    /**
     * Called when a web-socket message arrives.
     *
     * Format du message :
     *  ID_AGENT,X,Y
     *
     * @param message the message to be send
     * @param session the web-socket session
     */
    @OnMessage
    public void onMessage(final String message, final Session session) throws Exception {
    	 System.out.println("Client send message : " + session);
    	 System.out.println("            message : " + message);
        for(Session client : clients) {
            if(!client.equals(session)) {
            	System.out.println("Resend message to : " + client);
            	client.getBasicRemote().sendText(message);
            }
        }
    }
}
