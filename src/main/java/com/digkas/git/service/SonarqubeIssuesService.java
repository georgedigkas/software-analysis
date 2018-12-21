/**
 * 
 */
package com.digkas.git.service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import com.digkas.git.domain.Commit;
import com.digkas.git.domain.Method;
import com.digkas.git.domain.Project;
import com.digkas.git.domain.SonarqubeIssues;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public interface SonarqubeIssuesService {

	Collection<SonarqubeIssues> findAll();

	SonarqubeIssues findOne(Integer id);

	SonarqubeIssues create(SonarqubeIssues sonarqubeIssue);

	List<SonarqubeIssues> create(List<SonarqubeIssues> sonarqubeIssues);

	SonarqubeIssues update(SonarqubeIssues sonarqubeIssue);

	List<SonarqubeIssues> update(List<SonarqubeIssues> sonarqubeIssues);

	void delete(Integer id);

	void delete(Iterable<SonarqubeIssues> sonarqubeIssues);

	void deleteByCommitId(Commit commitId);

	void deleteByCommitId(int id);

	void deleteByCommitId(String sha);

	int deleteByCommitIdJPQL(Commit commitId);

	int deleteByCommitIdJPQL(int id);

	Collection<SonarqubeIssues> findByIds(Collection<Integer> ids);

	Collection<SonarqubeIssues> findByProject(Project project);

	ConcurrentMap<Commit, List<SonarqubeIssues>> groupByCommit(Collection<SonarqubeIssues> sonarqubeIssues);

	ConcurrentMap<String, List<SonarqubeIssues>> groupBySHA(Collection<SonarqubeIssues> sonarqubeIssues);

	Collection<Commit> findAnalyzedCommitsByProject(Project project);

	Collection<SonarqubeIssues> findByPathAndSHA(String path, String sha);

	Collection<SonarqubeIssues> findByCommitId(int id);

	Collection<SonarqubeIssues> findByCommitId(Commit commitId);

	Collection<SonarqubeIssues> findOpenIssuesByCommitId(Commit commitId);

	Collection<SonarqubeIssues> findOpenSonarqubeIssuesWithNonNullLinesByCommitId(Commit commitId);
	
	Collection<SonarqubeIssues> findOpenSonarqubeIssuesMappedToMethodsWithNonNullLinesByCommitId(Commit commitId);

	Collection<SonarqubeIssues> findOpenSonarqubeIssuesWithNonNullLinesByProject(Project project);

	Collection<SonarqubeIssues> findByStatusANDProjectId(String status, Project projectId);

	Collection<SonarqubeIssues> findOpenIssuesByProjectUuid(Project projectId);

	Collection<SonarqubeIssues> findByMethodIds(Collection<Integer> methodIds);

	Collection<SonarqubeIssues> findByPathANDCommitId(String path, Commit commitId);

	Collection<SonarqubeIssues> findByPathANDCommitIdANDMethodName(String path, Commit commitId, String methodName);

	Collection<SonarqubeIssues> findByMethodId(Method methodId);

	Collection<SonarqubeIssues> findByCommitIdANDkees(Commit commitId, Collection<String> kees);
}
