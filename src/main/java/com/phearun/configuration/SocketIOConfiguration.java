package com.phearun.configuration;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.phearun.configuration.component.Property;

@Configuration
public class SocketIOConfiguration {

	@Autowired
	private Property prop;
	
	@Bean
	public com.corundumstudio.socketio.Configuration socketConfig(){
		com.corundumstudio.socketio.Configuration socketConfig = new com.corundumstudio.socketio.Configuration();
	    socketConfig.setHostname(prop.SOCKET_IO_HOST);
	    socketConfig.setPort(prop.SOCKET_IO_PORT);
	    return socketConfig;
	}
	
	@Bean
	public SocketIOServer socketIOServer(){
	    SocketIOServer server = new SocketIOServer(socketConfig());
	    System.out.println("Starting SocketIO Server(Port:" + prop.SOCKET_IO_PORT + ")...");
		server.start();
		//server.startAsync();
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

