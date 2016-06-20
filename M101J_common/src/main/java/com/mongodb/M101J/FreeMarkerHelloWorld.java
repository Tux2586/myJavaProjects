package com.mongodb.M101J;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerHelloWorld {
	
	public static void main(String[]  args){
		Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(FreeMarkerHelloWorld.class, "/templates");
		
		try {
			Template helloWorldTemplate =  configuration.getTemplate("helloworld.ftl");
			StringWriter str = new StringWriter();
			Map<String, Object> helloMap = new HashMap<String, Object>();
			helloMap.put("name", "BloodBorne");
			
			helloWorldTemplate.process(helloMap, str);
			System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
