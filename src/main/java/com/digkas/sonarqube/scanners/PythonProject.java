/**
 * 
 */
package com.digkas.sonarqube.scanners;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public class PythonProject extends Project {

	public PythonProject(String hostURL, String owner, String repository, String projectKey, String projectName) {
		super(hostURL, owner, repository, projectKey, projectName, "py");
	}

	protected String getSonarProperties() {
		return " -Dsonar.projectKey=" + getProjectKey()
		+ " -Dsonar.projectName=" + getProjectName()
		+ " -Dsonar.sources=."
		+ " -Dsonar.projectVersion=" + getVersion() 
		+ " -Dsonar.language=" + getLanguage()
		+ " -Dsonar.host.url=" + this.getHostURL()
		+ " -Dsonar.projectDate=" + fixTimeZone(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(Date.from(Instant.ofEpochSecond(getCommitTime()))))
		+ this.sonarLogInToken()
		;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.scanners.Project#getAnalysisCommand()
	 */
	@Override
	public String getAnalysisCommand() {
		return getSonarProperties();
	}

}
