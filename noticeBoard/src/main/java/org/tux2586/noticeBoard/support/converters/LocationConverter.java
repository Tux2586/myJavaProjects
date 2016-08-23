package org.tux2586.noticeBoard.support.converters;

import java.text.ParseException;
import java.util.Locale;

import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.tux2586.noticeBoard.beans.Location;

@Component
public class LocationConverter implements Converter<String, Location> {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(LocationConverter.class);

	public Location convert(String arg0) {
		Location location = new Location();
		location.setCity(arg0.split(",")[0]);
		location.setState(arg0.split(",")[1]);
		return location;
	}

	
	

}
