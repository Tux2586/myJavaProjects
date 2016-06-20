package com.mongodb.M101J.homework.week2;

import java.util.Arrays;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class App {

	public static void main(String[] args) {
		MongoClient client = new MongoClient(new ServerAddress("localhost",27017));
	       MongoDatabase db = client.getDatabase("students");
	       MongoCollection<Document> collection = db.getCollection("grades");
	       Bson filter = new Document("type","homework");
	       MongoCursor<Document> cursor = collection.find(filter).sort(new Document("student_id",1).append("score",1)).iterator();
	       boolean isDelete = true;
	       
	       while(cursor.hasNext()){
	    	   Document d = cursor.next();
	    	   if(isDelete){
	    		   int s_id = d.getInteger("student_id");
	    		   double score = d.getDouble("score");
	    		   System.out.println(d.getInteger("student_id") + " -- " + d.getDouble("score"));
//	    		   collection.deleteOne(new Document("student_id",s_id).append("score", score).append("type", "homework"));
	    		   collection.deleteOne(new Document("_id",d.get("_id")));
	    	   }
	    	   isDelete = !isDelete;
	       }
	       
	       cursor.close();
	}

}
