/**
 * 
 */
package com.digkas.sonarqube.scanners;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public class MavenProjectAppDocker extends Project {

	public MavenProjectAppDocker(String hostURL, String owner, String repository) {
		super(hostURL, owner, repository, "java");
	}

	public MavenProjectAppDocker(String hostURL, String owner, String repository, String projectKey, String projectName) {
		super(hostURL, owner, repository, projectKey, projectName, "java");
	}

	protected String getSonarProperties() {
		return " -Dsonar.projectKey=" + getProjectKey()
		+ " -Dsonar.projectName=" + getProjectName()
		+ " -Dsonar.java.binaries=."
		//+ " -Dsonar.sources=."
		+ " -Dsonar.projectVersion=" + getVersion() 
		+ " -Dsonar.language=java"
		+ " -Dsonar.host.url=" + this.getHostURL()
		+ this.sonarLogInToken()
		;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.scanners.Project#getAnalysisCommand()
	 */
	@Override
	public String getAnalysisCommand() {
		return "mvn sonar:sonar" + getSonarProperties()//USED TO BE: mvn clean verify install sonar:sonar
		//+ " -Dmaven.test.skip=true"//USED TO BE: -Dmaven.test.failure.ignore=true
		//+ " -DskipTests"
		;
	}

}
