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
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.digkas.git.domain.Commit;
import com.digkas.git.domain.Method;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public class MethodFinder {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	protected File resource;
	protected File filePath;
	protected Commit commit;

	public MethodFinder(File homeDirectory, File filePath, Commit commit) {
		this.resource = new File(homeDirectory + File.separator + filePath);
		this.filePath = filePath;
		this.commit = commit;
	}

	//For Testing
	public MethodFinder(File homeDirectory, File filePath) {
		this.resource = new File(homeDirectory + File.separator + filePath);
		this.filePath = filePath;
	}

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
								MethodFinderUtils.methodDeclarationToHash(filePath, methodDeclaration, sourceString, methodDeclarations)
						)).collect(Collectors.toList());
	}

	public List<MethodDeclaration> getMethodDeclarations() throws IOException {
		ASTParser parser = ASTParser.newParser(AST.JLS10);
		parser.setResolveBindings(true);
		parser.setStatementsRecovery(true);
		parser.setBindingsRecovery(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		Path sourcePath = Paths.get(resource.toURI());
		String sourceString = new String(Files.readAllBytes(sourcePath));
		char[] source = sourceString.toCharArray();
		parser.setSource(source);
		parser.setUnitName(sourcePath.toAbsolutePath().toString());
		CompilationUnit astRoot = (CompilationUnit) parser.createAST(null);

		ASTNode node = astRoot.getRoot();
		MethodDeclarationFinder finder = new MethodDeclarationFinder();
		node.accept(finder);

		return finder.getMethods();
	}

}