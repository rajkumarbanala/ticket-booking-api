package com.example.demo.util;

import java.util.UUID;

/**
 * @author Rajkumar Banala 03-Mar-2021
 *
 */

public interface GeneralUtil {

	public static String getNewUUID() {
		return UUID.randomUUID().toString();
	}
}
