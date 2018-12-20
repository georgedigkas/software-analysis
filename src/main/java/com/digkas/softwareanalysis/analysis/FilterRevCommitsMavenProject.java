/**
 * 
 */
package com.digkas.softwareanalysis.analysis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

import com.digkas.softwareanalysis.service.jgit.GitServiceBean;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public class FilterRevCommitsMavenProject {

	private static final String JAVA_FILE = ".java";

	private List<RevCommit> revCommits;
	private Map<String, RevCommit> revCommitsMap;
	private RevCommit firstRevCommit;
	private RevCommit lastRevCommit;
	private GitServiceBean gitServiceBean;
	private Git git;

	public FilterRevCommitsMavenProject(GitServiceBean gitServiceBean, Git git, List<RevCommit> revCommits, RevCommit firstRevCommit, RevCommit lastRevCommit) {
		this.gitServiceBean = gitServiceBean;
		this.git = git;
		this.revCommits = revCommits;
		this.revCommitsMap = this.revCommits.stream().collect(Collectors.toMap(RevCommit::getName, Function.identity()));
		this.firstRevCommit = firstRevCommit;
		this.lastRevCommit = lastRevCommit;
	}

	public FilterRevCommitsMavenProject(GitServiceBean gitServiceBean, Git git, List<RevCommit> revCommits) {
		this(gitServiceBean, git, revCommits, revCommits.get(revCommits.size() - 1), revCommits.get(0));
	}

	public List<RevCommit> getFilteredRevCommits() throws IOException, GitAPIException {
		CanonicalTreeParser newCanonicalTreeParser;
		CanonicalTreeParser oldCanonicalTreeParser;
		List<DiffEntry> diffEntries;
		List<RevCommit> filteredRevCommits = new ArrayList<>();

		if (doesTheFirstCommitContainsJavaFiles())
			filteredRevCommits.add(firstRevCommit);
		revCommits.remove(firstRevCommit);

		for (RevCommit revCommit : revCommits) {
			if (pomXmlExists(revCommit)) {
				//FIXME Now I think only checks if the 1st commit has no parents. Maybe I should check if the edge to the first commit exists
				RevCommit oldRevCommitParent = getParent(revCommit.getParents(), revCommitsMap);
				if (revCommit.getParents().length > 0) {
					oldCanonicalTreeParser = getCanonicalTreeParserFromRevCommit(git.getRepository(), oldRevCommitParent);
					newCanonicalTreeParser = getCanonicalTreeParserFromRevCommit(git.getRepository(), revCommit);
					diffEntries = getDiffEntries(newCanonicalTreeParser, oldCanonicalTreeParser);
					if (diffEntriesContainJavaFiles(diffEntries))
						filteredRevCommits.add(revCommit);
				}
				else if (revCommit.getParents().length == 0)
					filteredRevCommits.add(revCommit);
			}
		}

		return filteredRevCommits;
	}

	private RevCommit getParent(RevCommit[] parents, Map<String, RevCommit> revCommitsMap) {
		for (RevCommit revCommit : parents) {
			RevCommit rc = revCommitsMap.get(revCommit.getName());
			if (Objects.nonNull(rc))
				return rc;
		}
		return null;
	}

	private boolean diffEntriesContainJavaFiles(List<DiffEntry> diffEntries) {
		diffEntries = diffEntries.stream().filter(diffEntry -> diffEntry.getNewPath().endsWith(JAVA_FILE) || diffEntry.getOldPath().endsWith(JAVA_FILE)).collect(Collectors.toList());
		return !diffEntries.isEmpty();
	}

	private List<DiffEntry> getDiffEntries(CanonicalTreeParser newCanonicalTreeParser, CanonicalTreeParser oldCanonicalTreeParser) throws GitAPIException {
		return git.diff().setOldTree(oldCanonicalTreeParser).setNewTree(newCanonicalTreeParser).setShowNameAndStatusOnly(true).call();
	}

	private CanonicalTreeParser getCanonicalTreeParserFromRevCommit(Repository repository, RevCommit revCommit) throws IOException {
		CanonicalTreeParser nCanonicalTreeParser;
		try (RevWalk walk = new RevWalk(repository)) {
			RevCommit commit = walk.parseCommit(revCommit);
			ObjectId treeId = commit.getTree().getId();
			try (ObjectReader reader = repository.newObjectReader()) {
				nCanonicalTreeParser = new CanonicalTreeParser(null, reader, treeId);
			}
		}
		return nCanonicalTreeParser;
	}

	private boolean pomXmlExists(RevCommit revCommit) {
		gitServiceBean.gitCheckout(revCommit);
		return Paths.get(gitServiceBean.getDirectory().getAbsolutePath() + File.separator +"pom.xml").toFile().exists();
	}

	private boolean doesTheFirstCommitContainsJavaFiles() throws IOException {
		Path path = Paths.get(gitServiceBean.getDirectory().getAbsolutePath());
		try (Stream<Path> paths = Files.walk(path)) {
			List<Path> javaFiles = paths.filter(Files::isRegularFile).filter(p -> p.toAbsolutePath().toString().endsWith(JAVA_FILE)).collect(Collectors.toList());
			return !javaFiles.isEmpty();
		}
	}

}
