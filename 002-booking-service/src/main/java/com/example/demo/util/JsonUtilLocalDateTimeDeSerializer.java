package com.example.demo.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author Rajkumar Banala 14-May-2019
 *
 * 
 */

public class JsonUtilLocalDateTimeDeSerializer extends JsonDeserializer<LocalDateTime> {
	
	private static final Logger LOG = LoggerFactory.getLogger(JsonUtilLocalDateTimeDeSerializer.class);
	
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	
	/**
	 * Used JsonSerializer<LocalDateTime> & JsonDeserializer<LocalDateTime> instead of @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	 * Because Serialization & De-Serialization is not working with @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	 * @throws IOException 
	 */
	@Override
	public LocalDateTime deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
		LOG.debug("deserialize()");
		
		String date = jsonparser.getText();
		LOG.debug("deserialize().date:" + date);
		
		return LocalDateTime.parse(date, dateTimeFormatter);
	}
}