package com.mongodb.M101J;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class MongoDBSparkFreeMarkerHelloWorld {

	public static void main(String[] args) {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(FreeMarkerHelloWorld.class, "/templates");
		MongoClient client = new MongoClient(new ServerAddress("localhost",27017));
	    MongoDatabase db = client.getDatabase("course");
	    final MongoCollection<Document> collection = db.getCollection("course");
	    collection.insertOne(new Document("name","JanardanIsACoolGuy"));
		
		Spark.get(new Route("/"){
			@Override
			public Object handle(final Request request, 
								 final Response response){
				StringWriter str = new StringWriter();
				try {
					
					Document doc = collection.find().first();
					
					
					Template helloWorldTemplate =  configuration.getTemplate("helloworld.ftl");
					
					Map<String, Object> helloMap = new HashMap<String, Object>();
					helloMap.put("name", doc.getString("name"));
					
					helloWorldTemplate.process(doc, str);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return str;
			}
		});

	}

}
