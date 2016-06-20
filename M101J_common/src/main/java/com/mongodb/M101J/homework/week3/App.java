package com.mongodb.M101J.homework.week3;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

public class App {

	public static void main(String[] args) {
		MongoClient client = new MongoClient(new ServerAddress("localhost",27017));
	       MongoDatabase db = client.getDatabase("school");
	       MongoCollection<Document> collection = db.getCollection("students");
	       MongoCursor<Document> cursor = collection.find().sort(new Document("_id",1)).iterator();
	       boolean isDelete = true;
	       
	       while(cursor.hasNext()){
	    	   Document d = cursor.next();
	    	   int s_id = d.getInteger("_id");
	    	   String name = d.getString("name");
	    	   List<Document> scores = (ArrayList<Document>)d.get("scores");
	    	   short delIndex = -1;
	    	   double minScore = 10000d;
	    	   for(short i=0;i<scores.size();i++){
	    		   Document score = scores.get(i);
	    		   String type = score.getString("type");
	    	
	    		   if("homework".equals(type)){
	    			   if(minScore > score.getDouble("score")){
	    				   minScore = score.getDouble("score");
	    				   delIndex = i;
	    			   }
	    		   }
	    			   
	    	  }
	    	   
	    	  scores.remove(delIndex);
	    	  Bson filter = new Document("_id",s_id);
	    	  collection.replaceOne(filter, new Document("name", name).append("scores", scores));
	       }
	       
	       cursor.close();
	}

}
