package com.manoj.multithreading.utils;

import java.io.InputStream;
import java.util.Objects;

public class ResourceUtils {

	/**
	 * 
	 */
	private ResourceUtils() {
	}

	public static InputStream getFileFromResourceAsStream(String fileName) {
		// The class loader that loaded the class
		var classLoader = ResourceUtils.class.getClassLoader();
		var inputStream = classLoader.getResourceAsStream(fileName);

		// the stream holding the file content
		if (Objects.isNull(inputStream)) {
			throw new IllegalArgumentException("file not found! " + fileName);
		} else {
			return inputStream;
		}

	}

}
