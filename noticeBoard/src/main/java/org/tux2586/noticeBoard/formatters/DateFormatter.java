package org.tux2586.noticeBoard.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import org.tux2586.noticeBoard.beans.Location;

@Component
public class DateFormatter implements Formatter<Location> {

	public String print(Location arg0, Locale arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Location parse(String locationString, Locale arg1) throws ParseException {
		
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
