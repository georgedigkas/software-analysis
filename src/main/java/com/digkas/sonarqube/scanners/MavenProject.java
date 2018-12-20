/**
 * 
 */
package com.digkas.sonarqube.scanners;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public class MavenProject extends Project {

	public MavenProject(String hostURL, String owner, String repository) {
		super(hostURL, owner, repository, "java");
	}

	public MavenProject(String hostURL, String owner, String repository, String projectKey, String projectName) {
		super(hostURL, owner, repository, projectKey, projectName, "java");
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.Project#getAnalysisCommand()
	 */
	@Override
	public String getAnalysisCommand() {
		return "mvn"
				//+ " clean install"//USED TO BE: mvn clean verify install sonar:sonar
				+ " sonar:sonar"
				+ getSonarProperties()
				+ " -Dmaven.test.skip=true"//USED TO BE: -Dmaven.test.failure.ignore=true
				+ " -Dmaven.test.failure.ignore=true"
				+ " -DskipTests=true"
				;
	}

}
