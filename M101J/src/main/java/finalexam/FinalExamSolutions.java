package finalexam;

import static spark.Spark.setPort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.Cursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import course.SessionDAO;
 

public class FinalExamSolutions {
	
	protected static MongoCollection<Document> collection = null;
	
	private static void Connect(String mongoURI, String dbName){
		final MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoURI));
        final MongoDatabase database = mongoClient.getDatabase(dbName);
        collection = database.getCollection("messages");
        
	}
	
	
	public static void main(String[] args) {
		
		Connect("mongodb://localhost","enron");
		String[] fromList = {"susan.mara@enron.com","susan.mara@enron.com","soblander@carrfut.com", "susan.mara@enron.com", "evelyn.metoyer@enron.com", "susan.mara@enron.com"};
		String[] toList = {"jeff.dasovich@enron.com","richard.shapiro@enron.com","soblander@carrfut.com","james.steffes@enron.com","kate.symes@enron.com","alan.comnes@enron.com"};
		
		for(short i=0;i<1/*fromList.length*/;i++){
			System.out.println(fromList[i]+ " <--> " + toList[i] + " : " + getPairCount(fromList[i], toList[i]));
		}
		;
		
	}
	
	private static int getPairCount(String email1, String email2){
		Bson senderFilter = new Document("headers.from",email1);
		Bson toFilter = Filters.in("headers.to", new ArrayList().add(email2));
		MongoCursor<Document> cursor = collection.find(Filters.and(senderFilter,toFilter)).iterator();
		int count = 0;
		
		while(cursor.hasNext()){
			cursor.next();
			count++;
		}
		
		cursor.close();
		
		senderFilter = new Document("headers.from",email2);
		toFilter = Filters.in("headers.to", Arrays.asList(email1));
		cursor = collection.find(Filters.and(senderFilter,toFilter)).iterator();
		
		while(cursor.hasNext()){
			count++;
		}
		
		cursor.close();
		
		return count;
	}
	

}
