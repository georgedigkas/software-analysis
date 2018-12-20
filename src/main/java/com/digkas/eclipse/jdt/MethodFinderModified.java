/**
 * 
 */
package com.digkas.eclipse.jdt;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.digkas.git.domain.Commit;
import com.digkas.git.domain.Method;
import com.github.gumtreediff.actions.model.Action;
import com.github.gumtreediff.actions.model.Delete;
import com.github.gumtreediff.actions.model.Insert;
import com.github.gumtreediff.actions.model.Update;

import gumtree.spoon.AstComparator;
import gumtree.spoon.builder.SpoonGumTreeBuilder;
import gumtree.spoon.diff.Diff;
import gumtree.spoon.diff.operations.DeleteOperation;
import gumtree.spoon.diff.operations.InsertOperation;
import gumtree.spoon.diff.operations.Operation;
import gumtree.spoon.diff.operations.UpdateOperation;
import spoon.reflect.declaration.CtElement;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public class MethodFinderModified {

	private static final String METHOD_NODE_TYPE = "Method";

	private static final String ONE_OR_MORE_SPACES = "\\s+";
	private static final String NEW_LINE = "\r\n|\r|\n";

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private File homeDirectoryFL;
	private File homeDirectoryFR;
	private File fl;
	private File fr;
	private File resourceFL;
	private File resourceFR;
	private MethodFinder oldFileMethodFinder;
	private MethodFinder newFileMethodFinder;
	private List<Method> previousMethods;
	private Commit commit;

	public MethodFinderModified(File homeDirectoryFL, File fl, File homeDirectoryFR, File fr, Commit commit) {
		this.homeDirectoryFL = homeDirectoryFL;
		this.fl = fl;
		this.resourceFL = new File(homeDirectoryFL + File.separator + fl);
		this.oldFileMethodFinder = new MethodFinder(homeDirectoryFL, fl, commit);

		this.homeDirectoryFR = homeDirectoryFR;
		this.fr = fr;
		this.resourceFR = new File(homeDirectoryFR + File.separator + fr);
		this.newFileMethodFinder = new MethodFinder(homeDirectoryFR, fr, commit);

		this.commit = commit;
	}

	public MethodFinderModified(File homeDirectoryFL, File fl, File homeDirectoryFR, File fr, Commit commit, List<Method> previousMethods) {
		this.homeDirectoryFL = homeDirectoryFL;
		this.fl = fl;
		this.resourceFL = new File(homeDirectoryFL + File.separator + fl);
		this.oldFileMethodFinder = new MethodFinder(homeDirectoryFL, fl, commit);

		this.homeDirectoryFR = homeDirectoryFR;
		this.fr = fr;
		this.resourceFR = new File(homeDirectoryFR + File.separator + fr);
		this.newFileMethodFinder = new MethodFinder(homeDirectoryFR, fr, commit);

		this.previousMethods = previousMethods;
		this.commit = commit;
	}

	public List<Method> getMethods() throws Exception {
		logger.info("fl: {} fr: {}", resourceFL, resourceFR);

		String sourceStringFL = new String(Files.readAllBytes(Paths.get(resourceFL.toURI())));
		String sourceStringFR = new String(Files.readAllBytes(Paths.get(resourceFR.toURI())));

		List<Method> oldMethods = oldFileMethodFinder.getMethods();
		List<Method> newMethods = newFileMethodFinder.getMethods();

		Map<String, List<Method>> previousMethodsGrouppedByHashCode = previousMethods.stream().collect(Collectors.groupingBy(Method::getHashCode));

		newMethods.forEach(method -> method.setClassifier("Unchanged"));
		Set<Method> deletedMethodsHashSet = new HashSet<>();
		for (Method method : newMethods)
			if (Objects.nonNull(previousMethodsGrouppedByHashCode.get(method.getHashCode())) && previousMethodsGrouppedByHashCode.get(method.getHashCode()).size() == 1)
				method.setOldMethodId(previousMethodsGrouppedByHashCode.get(method.getHashCode()).get(0));

		List<Method> methods = newMethods;
		Diff result = new AstComparator().compare(resourceFL, resourceFR);
		List<Operation> operations = result.getAllOperations();

		for (Operation operation : operations) {
			CtElement element = (CtElement) operation.getAction().getNode().getMetadata(SpoonGumTreeBuilder.SPOON_OBJECT);
			String nodeType = element.getClass().getSimpleName();
			nodeType = nodeType.substring(2, nodeType.length() - 4);

			if (Objects.equals(nodeType, METHOD_NODE_TYPE)) {
				if (operation.getAction() instanceof Insert)
					setClassifier(operation.getAction(), methods, "Insert");
				else if (operation.getAction() instanceof Delete)
					deletedMethodsHashSet.add(getDeletedMethod(new DeleteOperation((Delete) operation.getAction()), oldMethods));
				else if (operation.getAction() instanceof Update)
					setClassifier(operation.getAction(), methods, "Update");
			}
		}
		//Set OldMethod to DeletedMethods
		for (Method method : deletedMethodsHashSet)
			method.setOldMethodId(getOldDeletedMethod(method));
		methods.addAll(deletedMethodsHashSet);

		Set<Method> alreadyMappedMethodsSet = methods.stream().filter(method -> Objects.nonNull(method.getOldMethodId())).map(Method::getOldMethodId).collect(Collectors.toSet());
		Map<String, List<Method>> previousMethodsHaveNotMappedGrouppedByName = previousMethods
				.stream()
				.filter(method -> !alreadyMappedMethodsSet.contains(method))
				.collect(Collectors.groupingBy(Method::getName));

		for (Method method : methods) {
			if (Objects.isNull(method.getOldMethodId()) && Objects.equals(method.getClassifier(), "Unchanged")) {
				//List<Method> oldMethodId = previousMethodsGrouppedByHashCode.get(method.getHashCode());
				List<Method> previousMethodsWithTheSameName = previousMethodsHaveNotMappedGrouppedByName.get(method.getName());
				if (Objects.nonNull(previousMethodsWithTheSameName) && previousMethodsWithTheSameName.size() == 1)
					method.setOldMethodId(previousMethodsWithTheSameName.get(0));
			}
		}

		return methods;
	}

	private Method getOldDeletedMethod(Method method) {
		for (Method previousMethod : previousMethods) {
			//XXX NOT SURE: Have also to check start pos!
			if (Objects.equals(method.getName(), previousMethod.getName()) && (method.getEndPos() == previousMethod.getEndPos() || method.getEndPos() == (previousMethod.getEndPos() - 1) || method.getEndPos() == (previousMethod.getEndPos() + 1))) {
				System.out.println("getOldMethod(Method method): " + method.getStartPos() +"-"+ previousMethod.getStartPos() +" == "+ method.getEndPos() +"-"+ previousMethod.getEndPos());
				return previousMethod;
			}
		}
		return null;
	}

	private Method getDeletedMethod(DeleteOperation deleteOperation, List<Method> methods) {
		for (Method method : methods)
			if (deleteOperation.getSrcNode().getPosition().getSourceStart() <= method.getStartPos() && deleteOperation.getSrcNode().getPosition().getSourceEnd() == (method.getEndPos() - 1)) {
				method.setClassifier("Delete");
				return method;
			}
		return null;
	}

	private void setClassifier(Action action, List<Method> methods, String classifier) {
		int dstNodeStartPos = 0;
		int dstNodeEndPos = 0;
		int srcNodeStartPos = 0;
		int srcNodeEndPos = 0;

		List<Method> tmpPreviousMethods = previousMethods.stream().filter(method -> Objects.equals(FilenameUtils.separatorsToUnix(fr.getPath()), method.getPath())).collect(Collectors.toList());

		if (action instanceof Insert) {
			dstNodeStartPos = new InsertOperation((Insert) action).getSrcNode().getPosition().getSourceStart();
			dstNodeEndPos = new InsertOperation((Insert) action).getSrcNode().getPosition().getSourceEnd();
		} else if (action instanceof Update) {
			dstNodeStartPos = new UpdateOperation((Update) action).getDstNode().getPosition().getSourceStart();
			dstNodeEndPos = new UpdateOperation((Update) action).getDstNode().getPosition().getSourceEnd();

			srcNodeStartPos = new UpdateOperation((Update) action).getSrcNode().getPosition().getSourceStart();
			srcNodeEndPos = new UpdateOperation((Update) action).getSrcNode().getPosition().getSourceEnd();
		}
		for (Method method : methods) {
			if (dstNodeStartPos <= method.getStartPos() && dstNodeEndPos == (method.getEndPos() - 1)) {
				method.setClassifier(classifier);
				if (srcNodeStartPos != 0 && srcNodeEndPos != 0)
					for (Method previousMethod : tmpPreviousMethods)
						if (((previousMethod.getEndPos() == srcNodeEndPos) || (previousMethod.getEndPos() - 1) == srcNodeEndPos) && srcNodeStartPos <= previousMethod.getStartPos())
							method.setOldMethodId(previousMethod);
			}
		}
	}

	private MethodDeclaration getMethodDeclaration(CtElement element, List<MethodDeclaration> methodDeclarations) {
		for (MethodDeclaration methodDeclaration : methodDeclarations)
			if (element.getPosition().getSourceStart() <= methodDeclaration.getStartPosition() && element.getPosition().getSourceEnd() == (methodDeclaration.getStartPosition() + methodDeclaration.getLength() - 1))
				return methodDeclaration;
		logger.error("NULL MethodDeclaration returned!");
		System.exit(0);
		return null;
	}

}
