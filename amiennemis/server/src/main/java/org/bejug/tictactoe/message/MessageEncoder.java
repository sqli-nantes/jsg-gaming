package org.bejug.tictactoe.message;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author johan
 */
public class MessageEncoder implements Encoder.Text<TicTacToeMessage>{

	@Override
	public String encode(TicTacToeMessage message) throws EncodeException {
		 StringBuilder builder = new StringBuilder(message.getMessage());
	            for (String extraParameter : message.getExtra()) {
	                builder.append(" ").append(extraParameter.trim());
	            }
	            return builder.toString();
	}

    @Override
    public void init(EndpointConfig config) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
