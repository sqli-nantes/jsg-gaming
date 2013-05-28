package com.sqli.jsg.amiennemis.server;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/endpoint")
public class AmiEnnemisEndpoint {

    /**
     * Called when a web-socket connection is opened.
     *
     * @param session the web-socket session
     */
    @OnOpen
    public void onOpen(final Session session) throws Exception {

    }

    /**
     * Called when a web-socket connection is closed.
     *
     * @param session the web-socket session
     */
    @OnClose
    public void onClose(final Session session) throws Exception {

    }

    /**
     * Called when a web-socket message arrives.
     *
     * @param message the message to be send
     * @param session the web-socket session
     */
    @OnMessage
    public void onMessage(final String message, final Session session) throws Exception {

    }
}
