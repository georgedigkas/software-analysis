/**
 * 
 */
package com.digkas.sonarqube.scanners;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

import org.eclipse.jgit.revwalk.RevCommit;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public abstract class Project {

	protected String owner;
	protected String repository;
	protected String projectKey;
	protected String projectName;
	protected RevCommit revCommit;
	protected int commitTime;
	protected String version;
	protected String loginToken;
	protected String hostURL;
	protected String language;
	protected String password;

	public Project(String hostURL, String owner, String repository, String language) {
		this.hostURL = hostURL;
		this.owner = owner;
		this.repository = repository;
		this.projectKey = owner + ":" + repository;
		this.projectName = repository;
		this.language = language;
	}

	public Project(String hostURL, String owner, String repository, String projectKey, String projectName, String language) {
		this.hostURL = hostURL;
		this.owner = owner;
		this.repository = repository;
		this.projectKey = projectKey;
		this.projectName = projectName;
		this.language = language;
	}

	public String getOwner() {
		return owner;
	}

	public String getRepository() {
		return repository;
	}

	public String getProjectKey() {
		return projectKey;
	}

	public Project setProjectKey(String projectKey) {
		this.projectKey = projectKey;
		return this;
	}

	public String getProjectName() {
		return projectName;
	}

	public Project setProjectName(String projectName) {
		this.projectName = projectName;
		return this;
	}

	public RevCommit getRevCommit() {
		return revCommit;
	}

	public Project setRevCommits(RevCommit revCommit) {
		this.revCommit = revCommit;
		this.version = revCommit.getName();
		this.commitTime = revCommit.getCommitTime();
		return this;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(int commitTime) {
		this.commitTime = commitTime;
	}


	public String getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	public String getHostURL() {
		return hostURL;
	}

	public void setHostURL(String hostURL) {
		this.hostURL = hostURL;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	protected String sonarLogInToken() {
		return (Objects.nonNull(this.getLoginToken())) ? " -Dsonar.login=" + this.getLoginToken() : "";
	}

	protected String sonarPassword() {
		return (Objects.nonNull(this.getPassword())) ? " -Dsonar.password=" + this.getPassword() : "";
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	protected String getSonarProperties() {
		return " -Dsonar.projectKey=" + getProjectKey()
		+ " -Dsonar.projectName=" + getProjectName()
		+ " -Dsonar.java.binaries=."
		//+ " -Dsonar.sources=."
		+ " -Dsonar.projectVersion=" + getVersion() 
		+ " -Dsonar.language=" + getLanguage()
		+ " -Dsonar.host.url=" + this.getHostURL()
		+ " -Dsonar.projectDate=" + fixTimeZone(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(Date.from(Instant.ofEpochSecond(getCommitTime()))))
		//+ " -Dsonar.tests=."
		//+ " -Dsonar.test.inclusions=**/*Test*/**"
		//+ " -Dsonar.exclusions=**/*Test*/**"
		+ this.sonarLogInToken()
		+ this.sonarPassword()
		;
	}

	protected String fixTimeZone(String format) {
		if (format.endsWith("Z"))
			return format.replace("Z", "+0000");
		return format.substring(0, format.length() - 3) + "00";
	}

	public abstract String getAnalysisCommand();

}
