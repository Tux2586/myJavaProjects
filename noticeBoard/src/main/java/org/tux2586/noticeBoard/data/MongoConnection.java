package org.tux2586.noticeBoard.data;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
	private MongoClient client;
	private MongoDatabase database;
	private static final String mongoURI = "mongodb://192.168.1.4:45045";
	private static final String dbName = "NoticeBoard";
	
	public MongoConnection(){
		client = new MongoClient(mongoURI);
		database = client.getDatabase(dbName);
	}
	
	 
	
}
