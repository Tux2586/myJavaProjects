package com.mongodb.M101J;

import java.util.Arrays;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       MongoClient client = new MongoClient(new ServerAddress("localhost",27017));
       MongoDatabase db = client.getDatabase("videogames");
       MongoCollection<Document> collection = db.getCollection("videogames");
       
       
       
       Document uncharted = new Document().append("name", "Uncharted: Drake's Fortune")
    		   							  .append("publisher","SCEI");
       Document wolfenstein = new Document().append("name", "Wolfenstein: The New Order")
				  .append("publisher","Bethesda");
       Document darkSoulsIII = new Document().append("name", "Dark Soul III")
				  .append("publisher","Bandai Namco");
       Document darkSoulsII = new Document().append("name", "Dark Soul II")
				  .append("publisher","Bandai Namco");
       Document tombRaider = new Document().append("name", "tombRaider")
				  .append("publisher","Square Enix");
       Document borderlands = new Document().append("name", "Borderlands 2")
				  .append("publisher","2K");
       Document theLastOfUs = new Document().append("name", "The Last Of Us")
				  .append("publisher","SCEI");
       
     //  collection.insertMany(Arrays.asList(theLastOfUs,uncharted,darkSoulsIII,darkSoulsII,wolfenstein,tombRaider,borderlands));
       
       
       
       Bson filter = new Document("publisher","SCEI");
       
       MongoCursor<Document> cursor = collection.find(filter).iterator();
       
       while(cursor.hasNext()){
    	   System.out.println(cursor.next().getString("name"));
       }
       
       
       collection.replaceOne(com.mongodb.client.model.Filters.eq("name","Uncharted: Drake's Fortune"),new Document("name","Uncharted 2: Among Thieves").append("publisher", "SCEI"));
       cursor.close();
       
       cursor = collection.find(filter).iterator();
       
       while(cursor.hasNext()){
    	   System.out.println(cursor.next().getString("name"));
       }
       cursor.close();
       collection.drop();
   
       
    }
}
