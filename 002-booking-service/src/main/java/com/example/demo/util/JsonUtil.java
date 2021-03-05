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

public interface JsonUtil {
	
	public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public static TypeReference<Map<String, Object>> TYPE_REFERENCE_MAP = new TypeReference<Map<String, Object>>() {
	};

	public static TypeReference<Map<String, String>> TYPE_REFERENCE_MAP_STRING_STRING = new TypeReference<Map<String, String>>() {
	};

	public static TypeReference<HashMap<String, Object>> TYPE_REFERENCE_HASH_MAP = new TypeReference<HashMap<String, Object>>() {
	};

	public static TypeReference<HashMap<String, String>> TYPE_REFERENCE_HASH_MAP_STRING_STRING = new TypeReference<HashMap<String, String>>() {
	};

	public static TypeReference<Hashtable<String, Object>> TYPE_REFERENCE_HASH_TABLE = new TypeReference<Hashtable<String, Object>>() {
	};

	public static TypeReference<List<String>> TYPE_REFERENCE_LIST = new TypeReference<List<String>>() {
	};

	public static TypeReference<List<Object>> TYPE_REFERENCE_LIST_OBJECT = new TypeReference<List<Object>>() {
	};
	
	public static TypeReference<List<Map<String, Object>>> TYPE_REFERENCE_LIST_MAP = new TypeReference<List<Map<String, Object>>>() {
	};
	
	public static TypeReference<ArrayList<String>> TYPE_REFERENCE_ARRAY_LIST = new TypeReference<ArrayList<String>>() {
	};

	public static TypeReference<ArrayList<Object>> TYPE_REFERENCE_ARRAY_LIST_OBJECT = new TypeReference<ArrayList<Object>>() {
	};

	public static TypeReference<LinkedList<String>> TYPE_REFERENCE_LINKED_LIST = new TypeReference<LinkedList<String>>() {
	};

	public static TypeReference<Vector<String>> TYPE_REFERENCE_VECTOR = new TypeReference<Vector<String>>() {
	};

	public static TypeReference<Set<String>> TYPE_REFERENCE_SET = new TypeReference<Set<String>>() {
	};

	public static TypeReference<Set<Object>> TYPE_REFERENCE_SET_OBJECT = new TypeReference<Set<Object>>() {
	};

	public static TypeReference<HashSet<String>> TYPE_REFERENCE_HASH_SET = new TypeReference<HashSet<String>>() {
	};

	public static TypeReference<HashSet<Object>> TYPE_REFERENCE_HASH_SET_OBJECT = new TypeReference<HashSet<Object>>() {
	};

	public static TypeReference<TreeSet<String>> TYPE_REFERENCE_TREE_SET = new TypeReference<TreeSet<String>>() {
	};

	public static TypeReference<LinkedHashSet<String>> TYPE_REFERENCE_LINKED_HASH_SET = new TypeReference<LinkedHashSet<String>>() {
	};
}
