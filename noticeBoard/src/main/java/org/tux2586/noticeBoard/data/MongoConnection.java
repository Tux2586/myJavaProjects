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
	private static final String mongoURI = "mongodb://192.168.1.4:45045";
	private static final String dbName = "NoticeBoard";
	
	public MongoConnection(){
		client = null;
		database = null;
	}
	
	public void init(){
		client = new MongoClient(new MongoClientURI(mongoURI));
		database = client.getDatabase(dbName);
	}

	public void save(Object object, String string) {
		
		if("users".equals(string)){
			Document user = new Document();
			User u = (User)object;
			MongoCollection<Document> usersCollection = database.getCollection("users");
			
			user.append("_id", u.getuName())
				.append("firstName", u.getfName())
				.append("lName", u.getlName())
				.append("uName", u.getuName())
				.append("eMail",u.getEmail())
				.append("contactNo", u.getContactNo())
				.append("passwordHash",u.getPassword());
			
			usersCollection.insertOne(user);
		}
	}

	public void close() {
		client.close();
	}
	
public static void main(String[] args){
    	MongoConnection c = new MongoConnection();
    	c.init();
    }
	
}
