package org.tux2586.noticeBoard.data;

import org.bson.Document;
import org.tux2586.noticeBoard.beans.User;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
	private MongoClient client;
	private MongoDatabase database;
	private MongoCollection collection;
	private static final String MONGO_URI = "mongodb://192.168.1.4:45045";
	private static final String DATABASE_NAME = "NoticeBoard";
	private static final String USERS_COLLECTION = "users";

	public MongoConnection() {
		client = null;
		database = null;
	}

	public void init() {
		client = new MongoClient(new MongoClientURI(MONGO_URI));
		database = client.getDatabase(DATABASE_NAME);
	}
	
	public MongoCollection getCollection(String collectionName){
		MongoCollection<Document> collection = database.getCollection(collectionName);
		return collection;
	}


	public void close() {
		client.close();
	}

	public static void main(String[] args) {
		MongoConnection c = new MongoConnection();
		c.init();
	}

	public void insertDocument(Document document, String collectionName) {

		MongoCollection<Document> collection = database.getCollection(collectionName);
		collection.insertOne(document);
	}

	public Document getDocument(String _id, String collectionName) {
		
		MongoCollection<Document> collection = database.getCollection(collectionName);
		Document document = collection.find(new Document("_id",_id)).first();
		return document;
	}
}
