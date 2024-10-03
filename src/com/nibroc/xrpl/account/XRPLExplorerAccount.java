package com.nibroc.xrpl.account;

import com.nibroc.xrpl.websocket.WebsocketClient;

public class XRPLExplorerAccount {
	
	public static String getAccountInfoWebSocket(String websocketURL, String accountString) {
		WebsocketClient websocketClient = WebsocketClient.getInstance();
		String requestMessage = "{\"id\": 2,\"command\": \"account_info\",\"account\": \"" + accountString + "\",\"strict\": true,\"ledger_index\": \"current\",\"queue\": true}";
        return websocketClient.getAccountInfoUsingWebSocket(websocketURL, requestMessage);
	}
	
	public String addAccount() {
		return "";
	}
	
}
