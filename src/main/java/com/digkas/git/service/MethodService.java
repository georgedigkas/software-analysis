/**
 * 
 */
package com.digkas.git.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.digkas.git.domain.Commit;
import com.digkas.git.domain.Method;
import com.digkas.git.domain.Project;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public interface MethodService {

	Collection<Method> findAll();

	Method findOne(Integer id);

	Method create(Method method);

	List<Method> create(List<Method> methods);

	Method update(Method method);

	List<Method> update(List<Method> methods);

	void delete(Integer id);

	void delete(Iterable<Method> methods);

	Collection<Method> findByIdProjectId(Project projectId);

	Collection<Method> findByIdProjectKee(String kee);

	Collection<Method> findByCommitId(Commit commitId);

	Collection<Method> findByCommitId(Integer commitId);

	Collection<Method> findByCommitIdNative(Integer commitId);

	Collection<Method> findMethodsWithNullOldMethodIdByProjectId(Project projectId);

	Collection<Method> findMethodsWithNullOldMethodIdByCommitId(Commit commitId);

	Collection<Method> findMethodsWithNotNullOldMethodIdByCommitId(Commit commitId);

	Collection<Method> findUnchangedMethodsWithNullOldMethodIdByCommitId(Commit commitId);

	int deleteByCommitId(Commit commitId);

	int deleteByCommitId(Integer commitId);

	int deleteByCommitId(String sha);

	Set<Integer> findNotNullOldMethodIdsByCommitId(Commit commitId);
	
	Set<Method> findFromSonarqubeIssuesByCommitIdANDkees(Commit commitId, Collection<String> kees);

	Set<Method> findFromSonarqubeIssuesByCommitIdANDMethodNameANDkees(Commit commitId, String methodName, Collection<String> kees);
	
	Set<Method> findFromSonarqubeIssuesByCommitIdANDPathANDkees(Commit commitId, String path, Collection<String> kees);

}