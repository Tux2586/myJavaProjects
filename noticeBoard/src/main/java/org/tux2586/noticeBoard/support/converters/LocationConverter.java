package org.tux2586.noticeBoard.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import org.tux2586.noticeBoard.beans.Location;
import org.tux2586.noticeBoard.controllers.UserController;

@Component
public class LocationFormatter implements Formatter<Location> {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(LocationFormatter.class);

	
	public String print(Location arg0, Locale arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Location parse(String locationString, Locale arg1) throws ParseException {
		
		logger.debug("Here " + locationString);
		Location location = new Location();
		String[] string = locationString.split(",");
		if(string.length == 0){

		}else if(string.length == 1){
			location.setCity(string[0]);
			return location;
		}else if(string.length == 2){
			location.setCity(string[0]);
			location.setState(string[1]);
			return location;
		}
		return location;
		
	}

}
