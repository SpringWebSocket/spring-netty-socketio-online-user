/*package com.phearun.configuration;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;

@Configuration
public class SocketIOConfiguration {

	@Value("${socket.io.port}")  //inject value of property file
	private int port;
	
	@Value("${socket.io.host}")
	private String host;
	
	@Bean
	public com.corundumstudio.socketio.Configuration socketConfig(){
		com.corundumstudio.socketio.Configuration socketConfig = new com.corundumstudio.socketio.Configuration();
	    socketConfig.setHostname(host);
	    socketConfig.setPort(port);
	    return socketConfig;
	}
	
	@Bean
	public SocketIOServer socketIOServer(){
	    SocketIOServer server = new SocketIOServer(socketConfig());
	    System.out.println("Starting SocketIO Server(Port:" + port + ")...");
		server.start();
		return server;
	}
	
	//For enable socket.io annotation ( @onConnect, @onEvent,...)
	@Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer server) {
        return new SpringAnnotationScanner(server);
    }

	@PreDestroy
	public void stopSocketIOServer(){
		System.out.println("Stopping SocketIO Server...");
		socketIOServer().stop();
	}
	
}

*/