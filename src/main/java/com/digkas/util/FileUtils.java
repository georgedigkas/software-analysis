/**
 * 
 */
package com.digkas.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public final class FileUtils {

	public static List<Path> getAllFilesForAPath(Path path) throws IOException { 
		return Files.walk(path)
				.collect(Collectors.toList());
	}

	public static List<Path> getAllJavaFilesForAPath(Path path) throws IOException { 
		return Files.walk(path)
				.filter(s -> s.toString().endsWith(".java"))
				.collect(Collectors.toList());
	}

}
