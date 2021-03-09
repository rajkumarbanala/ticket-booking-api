package com.example.demo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author Rajkumar Banala 03-Mar-2021
 *
 */

public final class JsonUtil {
	
	private JsonUtil() {
	}
	
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public static final TypeReference<Map<String, Object>> TYPE_REFERENCE_MAP = new TypeReference<Map<String, Object>>() {
	};

	public static final TypeReference<Map<String, String>> TYPE_REFERENCE_MAP_STRING_STRING = new TypeReference<Map<String, String>>() {
	};

	public static final TypeReference<HashMap<String, Object>> TYPE_REFERENCE_HASH_MAP = new TypeReference<HashMap<String, Object>>() {
	};

	public static final TypeReference<HashMap<String, String>> TYPE_REFERENCE_HASH_MAP_STRING_STRING = new TypeReference<HashMap<String, String>>() {
	};

	public static final TypeReference<Hashtable<String, Object>> TYPE_REFERENCE_HASH_TABLE = new TypeReference<Hashtable<String, Object>>() {
	};

	public static final TypeReference<List<String>> TYPE_REFERENCE_LIST = new TypeReference<List<String>>() {
	};

	public static final TypeReference<List<Object>> TYPE_REFERENCE_LIST_OBJECT = new TypeReference<List<Object>>() {
	};
	
	public static final TypeReference<List<Map<String, Object>>> TYPE_REFERENCE_LIST_MAP = new TypeReference<List<Map<String, Object>>>() {
	};
	
	public static final TypeReference<ArrayList<String>> TYPE_REFERENCE_ARRAY_LIST = new TypeReference<ArrayList<String>>() {
	};

	public static final TypeReference<ArrayList<Object>> TYPE_REFERENCE_ARRAY_LIST_OBJECT = new TypeReference<ArrayList<Object>>() {
	};

	public static final TypeReference<LinkedList<String>> TYPE_REFERENCE_LINKED_LIST = new TypeReference<LinkedList<String>>() {
	};

	public static final TypeReference<Vector<String>> TYPE_REFERENCE_VECTOR = new TypeReference<Vector<String>>() {
	};

	public static final TypeReference<Set<String>> TYPE_REFERENCE_SET = new TypeReference<Set<String>>() {
	};

	public static final TypeReference<Set<Object>> TYPE_REFERENCE_SET_OBJECT = new TypeReference<Set<Object>>() {
	};

	public static final TypeReference<HashSet<String>> TYPE_REFERENCE_HASH_SET = new TypeReference<HashSet<String>>() {
	};

	public static final TypeReference<HashSet<Object>> TYPE_REFERENCE_HASH_SET_OBJECT = new TypeReference<HashSet<Object>>() {
	};

	public static final TypeReference<TreeSet<String>> TYPE_REFERENCE_TREE_SET = new TypeReference<TreeSet<String>>() {
	};

	public static final TypeReference<LinkedHashSet<String>> TYPE_REFERENCE_LINKED_HASH_SET = new TypeReference<LinkedHashSet<String>>() {
	};
}
