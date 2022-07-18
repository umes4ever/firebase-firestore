package com.umes4ever.firestoreserver.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class DataGenerator {

	public static String getRandomString(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}
	
	public static int getRandomNumber(int length) {
		return Integer.parseInt(RandomStringUtils.randomNumeric(length));
	}
	
	private DataGenerator() {
		//private constructor
	}
}