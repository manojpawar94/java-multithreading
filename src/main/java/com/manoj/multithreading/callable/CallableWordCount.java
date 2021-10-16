/**
 * 
 */
package com.manoj.multithreading.callable;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.manoj.multithreading.utils.ResourceUtils;

/**
 * @author manojpawar
 *
 */
public class CallableWordCount implements Callable<Map<String, Integer>> {

	private String resourceName;

	/**
	 * @param resourceName
	 */
	public CallableWordCount(String resourceName) {
		this.resourceName = resourceName;
	}

	@Override
	public Map<String, Integer> call() throws Exception {
		var inputStream = ResourceUtils.getFileFromResourceAsStream(resourceName);
		var fileText = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

		return Stream.of(fileText).map(text -> text.replaceAll("[.?#'\"]", "")).map(text -> text.split("\\s+"))
				.flatMap(Arrays::stream).collect(Collectors.toMap(String::toLowerCase, w -> 1, Integer::sum));
	}

}
