/**
 * 
 */
package com.digkas.softwareanalysis.service.jgit;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
// @Service
public class GitServiceBean implements GitService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private File directory;
	private Git git;

	public GitServiceBean(File directory) {
		this.directory = directory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digkas.softwareanalysis.service.jgit.GitService#gitClone(java.lang.
	 * String)
	 */
	@Override
	public Git gitClone(String uri) {
		logger.info("gitClone uri: {}", uri);
		deleteGitRepoDirectory();
		createGitRepoDirectory();
		try {
			git = Git.cloneRepository().setURI(uri).setDirectory(directory).call();
			return git;
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.jgit.GitService#getAllCommitsIterable()
	 */
	@Override
	public Iterable<RevCommit> getAllCommitsIterable() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.jgit.GitService#gitCheckout(java.lang.
	 * String)
	 */
	@Override
	public Ref gitCheckout(String sha) {
		logger.info("gitCheckout sha: {}", sha);
		try {
			return git.checkout().setName(sha).call();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.jgit.GitService#gitCheckout(org.eclipse.
	 * jgit.revwalk.RevCommit)
	 */
	@Override
	public Ref gitCheckout(RevCommit revCommit) {
		logger.info("gitCheckout revCommit: {}", revCommit);
		return gitCheckout(revCommit.getName());
	}

	private boolean deleteGitRepoDirectory() {
		return FileUtils.deleteQuietly(directory);
	}

	private void createGitRepoDirectory() {
		if (!directory.exists())
			if (directory.mkdirs())
				logger.info(directory.getAbsolutePath() + " created!");
			else
				logger.error(directory.getAbsolutePath() + " fail to be created..");
	}

	public File getDirectory() {
		return directory;
	}

	public void setDirectory(File directory) {
		this.directory = directory;
	}

	public Git getGit() {
		return git;
	}

	public void setGit(Git git) {
		this.git = git;
	}

}
