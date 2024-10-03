package com.nibroc.xrpl.websocket;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.concurrent.CountDownLatch;

public class WebsocketClient {
	
	private static WebsocketClient websocketClient = null;
	private WebSocket ws = null;
	private WebsocketClientListener websocketClientListener = null;
	private CountDownLatch latch = null;
	private WebsocketClient() {}
	
	public static WebsocketClient getInstance() {
		if(websocketClient ==  null) {
			websocketClient = new WebsocketClient();
		}
		return websocketClient;
	}
	
	public String getAccountInfoUsingWebSocket(String websocketURL, String requestMessage) {
		if(latch == null) {
			latch = new CountDownLatch(1);
		}
		if(websocketClientListener == null) { 
			websocketClientListener = new WebsocketClientListener(latch);
		}
		if(ws == null) {
	        ws = HttpClient
	                .newHttpClient()
	                .newWebSocketBuilder()
	                .buildAsync(URI.create(websocketURL), websocketClientListener)
	                .join();
		}
        ws.sendText(requestMessage, true);
        try {
        	latch.await();
    	} catch(Exception ex) {
    		System.err.println("exception: " + ex.getMessage());
    	}
		return websocketClientListener.getResponse();
	}
}
