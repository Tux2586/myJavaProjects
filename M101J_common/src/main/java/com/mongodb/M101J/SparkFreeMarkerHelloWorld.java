package com.mongodb.M101J;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkFreeMarkerHelloWorld {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(FreeMarkerHelloWorld.class, "/templates");
		
		Spark.get(new Route("/"){
			@Override
			public Object handle(final Request request, 
								 final Response response){
				StringWriter str = new StringWriter();
				try {
					Template helloWorldTemplate =  configuration.getTemplate("helloworld.ftl");
					
					Map<String, Object> helloMap = new HashMap<String, Object>();
					helloMap.put("name", "BloodBorne");
					
					helloWorldTemplate.process(helloMap, str);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return str;
			}
		});
	}

}
