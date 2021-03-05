package com.example.demo.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @author Rajkumar Banala 03-Mar-2021
 *
 */

public class JpaJsonDataListLocalDateTimeConverter implements AttributeConverter<Set<LocalDateTime>, String> {

	private static final Logger LOG = LoggerFactory.getLogger(JpaJsonDataListLocalDateTimeConverter.class);
	
	public static TypeReference<Set<LocalDateTime>> TYPE_REFERENCE_LIST_LOCAL_DATE_TIME = new TypeReference<Set<LocalDateTime>>() {
	};

	@Override
	public String convertToDatabaseColumn(Set<LocalDateTime> meta) {
		LOG.debug("convertToDatabaseColumn()");

		String jsonString = "";

		try {

			// convert list of POJO to json
			jsonString = JsonUtil.OBJECT_MAPPER.writeValueAsString(meta);

		} catch (JsonProcessingException e) {
			LOG.error(e.getMessage(), e);
		}

		return jsonString;
	}

	@Override
	public Set<LocalDateTime> convertToEntityAttribute(String dbData) {
		LOG.debug("convertToEntityAttribute()");

		Set<LocalDateTime> list = new HashSet<>();

		try {

			// convert json to list
			list = JsonUtil.OBJECT_MAPPER.readValue(dbData, TYPE_REFERENCE_LIST_LOCAL_DATE_TIME);

		} catch (JsonParseException e) {
			LOG.error(e.getMessage(), e);

		} catch (JsonMappingException e) {
			LOG.error(e.getMessage(), e);

		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return list;
	}
}
