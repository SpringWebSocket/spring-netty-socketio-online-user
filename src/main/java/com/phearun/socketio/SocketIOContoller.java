package com.phearun.socketio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.phearun.entity.User;
import com.phearun.service.UserService;

@Component
public class SocketIOContoller {

	private SocketIOServer server;

	@Autowired
	private UserService userService;
	
	@Autowired
	public SocketIOContoller(SocketIOServer server) {
		this.server = server;
	}
	
	@OnConnect
	public void onConnect(SocketIOClient client) {
		System.out.println("Connected client: " + client.getSessionId());
	}

	@OnEvent("login")
	public void onEvent(SocketIOClient client, int userId, AckRequest ack){
		if(userService.updateOnlineAndClientId(userId, client.getSessionId().toString())){
			List<User> users = userService.findAllOnlineUsers();
			this.server.getBroadcastOperations().sendEvent("online-users", users);
		}
	}
	
	@OnDisconnect
	public void onDisconnect(SocketIOClient client) {
		if(userService.updateOfflineByClientId(client.getSessionId().toString())){	
			List<User> users = userService.findAllOnlineUsers();
			this.server.getBroadcastOperations().sendEvent("online-users", users);
		}
	}
}
