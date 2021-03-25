package com.example.demo.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rajkumar Banala 14-May-2019
 *
 * 
 */

@Slf4j
public class JsonUtilLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
	
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	/**
	 * Used JsonSerializer<LocalDateTime> & JsonDeserializer<LocalDateTime> instead of @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	 * Because Serialization & De-Serialization is not working with @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	 * @throws IOException 
	 */
	@Override
	public void serialize(LocalDateTime date, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
		log.debug("serialize()");

		String formattedDate = date.format(dateTimeFormatter);
		log.debug("serialize().formattedDate:" + formattedDate);

		jsonGenerator.writeString(formattedDate);
	}
}
