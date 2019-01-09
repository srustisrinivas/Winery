package com.mindtree.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class WineUtil {

	static TransportClient client;
	
	
	@SuppressWarnings("resource")
	public static TransportClient getConnection() {
		File file = new File("C:\\Users\\M1049114\\eclipse-workspace-ds\\Winery\\WebContent\\WEB-INF\\application.properties");
		try {
			Properties properties = new Properties();
			FileInputStream fileInput = new FileInputStream(file);
			properties.load(fileInput);
			fileInput.close();
			
			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				System.out.println(key + ": " + value);
			}
		
		Settings settings = Settings.builder().put("cluster.name",properties.getProperty("cluster.name")).build();
		
			int tcport=Integer.parseInt(properties.getProperty("tcp"));
		    client= new PreBuiltTransportClient(settings)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(properties.getProperty("localhost")),tcport));
		System.out.println(client);
		 
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		return client;
		
	}
	
	public void closeConnection() {
	     
		client.close();
	}
	
}
