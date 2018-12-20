/**
 * 
 */
package com.digkas.eclipse.jdt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import com.digkas.git.domain.Commit;
import com.digkas.git.domain.Method;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public class MethodFinderUnchanged extends MethodFinder {

	private Map<Integer, Method> previousMethodsMap;

	public MethodFinderUnchanged(File homeDirectory, File filePath, Commit commit) {
		super(homeDirectory, filePath, commit);
	}

	public MethodFinderUnchanged(File homeDirectory, File filePath, Commit commit, List<Method> previousMethods) {
		super(homeDirectory, filePath, commit);
		this.previousMethodsMap = previousMethods.stream().filter(method -> !method.getClassifier().endsWith("Delete")).collect(Collectors.toMap(Method::getEndPos, Function.identity()));
	}

	@Override
	public List<Method> getMethods() throws IOException {
		Path sourcePath = Paths.get(resource.toURI());
		String sourceString = new String(Files.readAllBytes(sourcePath));

		//startLine: it also includes the comments (if there are any) on top of the method
		List<MethodDeclaration> methodDeclarations = this.getMethodDeclarations();
		return methodDeclarations
				.stream()
				.map(methodDeclaration -> new Method(
						FilenameUtils.separatorsToUnix(filePath.getPath()), 
						methodDeclaration.getName().toString(),
						methodDeclaration.getStartPosition(),
						methodDeclaration.getStartPosition() + methodDeclaration.getLength(),
						sourceString.substring(0, methodDeclaration.getStartPosition()).split(MethodFinderUtils.NEW_LINE).length,
						sourceString.substring(0, methodDeclaration.getStartPosition() + methodDeclaration.getLength()).split(MethodFinderUtils.NEW_LINE).length, 
						commit,
						Objects.isNull(methodDeclaration.getBody()) ? 0 : (methodDeclaration.getBody().toString().split(MethodFinderUtils.NEW_LINE).length - 2),
								//Hashing.sha256().hashString(FilenameUtils.separatorsToUnix(filePath.getPath()) + sourceString.substring(methodDeclaration.getStartPosition(), methodDeclaration.getStartPosition() + methodDeclaration.getLength()).replaceAll(MethodFinderUtils.ONE_OR_MORE_SPACES, ""), StandardCharsets.UTF_8).toString(),
								MethodFinderUtils.methodDeclarationToHash(filePath, methodDeclaration, sourceString, methodDeclarations),
								getPreviousMethod(methodDeclaration)
						)).collect(Collectors.toList());
	}

	private Method getPreviousMethod(MethodDeclaration methodDeclaration) {
		if (Objects.nonNull(previousMethodsMap.get(methodDeclaration.getStartPosition() + methodDeclaration.getLength())))
			return previousMethodsMap.get(methodDeclaration.getStartPosition() + methodDeclaration.getLength());
		else if (Objects.nonNull(previousMethodsMap.get(methodDeclaration.getStartPosition() + methodDeclaration.getLength() - 1)))
			return previousMethodsMap.get(methodDeclaration.getStartPosition() + methodDeclaration.getLength() - 1);
		else if (Objects.nonNull(previousMethodsMap.get(methodDeclaration.getStartPosition() + methodDeclaration.getLength() + 1)))
			return previousMethodsMap.get(methodDeclaration.getStartPosition() + methodDeclaration.getLength() + 1);
		return null;
	}

}
