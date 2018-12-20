/**
 * 
 */
package com.digkas.softwareanalysis.service.git;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public class GitServiceBean implements GitService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private File directory;
	private String owner;
	private String repo;
	private String host;
	private String uri;

	public GitServiceBean(File directory) {
		this.directory = directory;
	}

	public GitServiceBean(File directory, String owner, String repo, String host) {
		super();
		this.directory = directory;
		this.owner = owner;
		this.repo = repo;
		this.host = host;
		this.uri = this.host + "/" + this.owner + "/" + this.repo;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.git.GitService#gitClone(java.lang.String)
	 */
	@Override
	public void gitClone(String uri) {
		logger.info("gitClone uri: {}", uri);
		deleteGitRepoDirectory();
		createGitRepoDirectory();

		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "cd \"" + directory.getAbsolutePath() + "\" && git clone " + uri);
		builder.redirectErrorStream(true);
		Process p;
		try {
			p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while (true) {
				line = r.readLine();
				if (line == null) { break; }
				//System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void gitClone() {
		this.gitClone(uri);
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.git.GitService#gitCheckout(java.lang.String)
	 */
	@Override
	public void gitCheckout(String sha) {
		logger.info("gitCheckout sha: {}", sha);
		
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "cd \"" + directory.getAbsolutePath() + File.separator + repo + "\" && git checkout " + sha);
		builder.redirectErrorStream(true);
		Process p;
		try {
			p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while (true) {
				line = r.readLine();
				if (line == null) { break; }
				//System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

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

}
