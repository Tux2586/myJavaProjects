package finalexam;

import static spark.Spark.setPort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.Cursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;


public class FinalExamSolutions {
	
	protected static MongoCollection<Document> collection = null;
	
	private static void Connect(String mongoURI, String dbName, String collName){
		final MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoURI));
        final MongoDatabase database = mongoClient.getDatabase(dbName);
        
        if(collection != null){
        	collection = null;
        }
        collection = database.getCollection(collName);
 	}
	
	
	public static void main(String[] args) {
	/*ENRON	
		Connect("mongodb://localhost","enron","messages");
		String[] fromList = {"susan.mara@enron.com","susan.mara@enron.com","soblander@carrfut.com", "susan.mara@enron.com", "evelyn.metoyer@enron.com", "susan.mara@enron.com"};
		String[] toList = {"jeff.dasovich@enron.com","richard.shapiro@enron.com","soblander@carrfut.com","james.steffes@enron.com","kate.symes@enron.com","alan.comnes@enron.com"};
		
		for(short i=0;i<fromList.length;i++){
			System.out.println(fromList[i]+ " --> " + toList[i] + " : " + getPairCount(fromList[i], toList[i]));
		};
		
		updateEmailAddress("<8147308.1075851042335.JavaMail.evans@thyme>"); */
		
	//	removeUnTaggedImages();
		animalCount();
		
	}
	
	private static int removeUnTaggedImages(){
		Connect("mongodb://localhost","photoAlbums","albums");
		MongoCursor<Document> cursor = collection.find().iterator();
		Set<Integer> imageIDs = new TreeSet<Integer>();
		while(cursor.hasNext()){
			Document document = cursor.next();
			ArrayList<Integer> images = (ArrayList<Integer>)document.get("images");
			for(Integer imageID:images){
				imageIDs.add(imageID);
			}
		}
		
		Connect("mongodb://localhost","photoAlbums","images");
		Bson filter1 = new Document("tags", new Document("$in",Arrays.asList("kittens")));
		Bson filter2 = new Document("_id", new Document("$in",new ArrayList(imageIDs)));
		cursor = collection.find(Filters.and(filter1, filter2)).iterator();
		int count = 0;
		
		while(cursor.hasNext()){
			count++;
			cursor.next();
		}
		System.out.println(count);
		return -1;
	}
	private static void animalCount(){
		Connect("mongodb://localhost","test","animals");
        Document animal = new Document("animal", "monkey");

         collection.insertOne(animal);
         animal.remove("animal");
         animal.append("animal", "cat");
         collection.insertOne(animal);
         animal.remove("animal");
         animal.append("animal", "lion");
         collection.insertOne(animal);
         
	}
	
	
	private static void updateEmailAddress(String messageID){
		Bson filterDocument = new Document("headers.Message-ID",messageID);
		Bson updateDocument = new Document("$push",new Document("headers.To","mrpotatohead@mongodb.com"));
		collection.findOneAndUpdate(filterDocument, updateDocument);
	}
	
	private static int getPairCount(String email1, String email2){
		Bson senderFilter = new Document("headers.From",email1);
		Bson toFilter = Filters.in("headers.To", Arrays.asList(email2));
		MongoCursor<Document> cursor = collection.find(Filters.and(senderFilter,toFilter)).iterator();
		int count1 = 0, count2 = 0;
		String logger = "";
		while(cursor.hasNext()){
			cursor.next();
			count1++;
			
		}
		logger += "("+count1+",";
		cursor.close();
		
		senderFilter = new Document("headers.From",email2);
		toFilter = Filters.in("headers.To", Arrays.asList(email1));
		cursor = collection.find(Filters.and(senderFilter,toFilter)).iterator();
		
		while(cursor.hasNext()){
			count2++;
			cursor.next();
		}
		logger += count2+")";
		cursor.close();
		System.out.print(logger);
		return count1;
	}
	

}
