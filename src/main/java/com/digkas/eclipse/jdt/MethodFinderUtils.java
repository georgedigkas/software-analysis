/**
 * 
 */
package com.digkas.eclipse.jdt;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import com.google.common.hash.Hashing;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public class MethodFinderUtils {

	public static final String ONE_OR_MORE_SPACES = "\\s+";
	public static final String NEW_LINE = "\r\n|\r|\n";

	/**
	public static String methodDeclarationToHash(File file, MethodDeclaration methodDeclaration, String sourceString) {
		return methodDeclarationToHash(file, sourceString.substring(methodDeclaration.getStartPosition(), methodDeclaration.getStartPosition() + methodDeclaration.getLength()).replaceAll(ONE_OR_MORE_SPACES, ""));
	}

	public static String methodDeclarationToHash(File file, String methodDeclarationString) {
		return Hashing.sha256().hashString(FilenameUtils.separatorsToUnix(file.getPath()) + methodDeclarationString.replaceAll(ONE_OR_MORE_SPACES, ""), StandardCharsets.UTF_8).toString();
	}
	**/

	public static String methodDeclarationToHash(File file, MethodDeclaration methodDeclaration, String sourceString, List<MethodDeclaration> methodDeclarations) {
		return methodDeclarationToHash(file, getParentMethod(methodDeclaration, methodDeclarations), sourceString.substring(methodDeclaration.getStartPosition(), methodDeclaration.getStartPosition() + methodDeclaration.getLength()).replaceAll(ONE_OR_MORE_SPACES, ""));
	}

	public static String methodDeclarationToHash(File file, String parentMethod, String methodDeclarationString) {
		return Hashing.sha256().hashString(FilenameUtils.separatorsToUnix(file.getPath()) + parentMethod + methodDeclarationString.replaceAll(ONE_OR_MORE_SPACES, ""), StandardCharsets.UTF_8).toString();
	}

	private static String getParentMethod(MethodDeclaration methodDeclaration, List<MethodDeclaration> methodDeclarations) {
		for (MethodDeclaration md : methodDeclarations)
			if (md.getStartPosition() < methodDeclaration.getStartPosition() && (md.getStartPosition() + md.getLength()) > (methodDeclaration.getStartPosition() + methodDeclaration.getLength())) {
				System.out.println("getParentMethod: Parent found - " + md.getName().toString());
				return md.getName().toString();
			}
		return "";
	}

}
