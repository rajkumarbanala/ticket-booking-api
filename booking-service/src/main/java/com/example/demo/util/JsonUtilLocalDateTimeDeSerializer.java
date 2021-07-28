package com.example.demo.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rajkumar Banala 14-May-2019
 *
 * 
 */

@Slf4j
public class JsonUtilLocalDateTimeDeSerializer extends JsonDeserializer<LocalDateTime> {
	
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	
	/**
	 * Used JsonSerializer<LocalDateTime> & JsonDeserializer<LocalDateTime> instead of @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	 * Because Serialization & De-Serialization is not working with @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	 * @throws IOException 
	 */
	@Override
	public LocalDateTime deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
		log.debug("deserialize()");
		
		String date = jsonparser.getText();
		log.debug("deserialize().date:" + date);
		
		return LocalDateTime.parse(date, dateTimeFormatter);
	}
}