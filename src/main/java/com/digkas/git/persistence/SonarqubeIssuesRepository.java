/**
 * 
 */
package com.digkas.git.persistence;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.digkas.git.domain.Commit;
import com.digkas.git.domain.Method;
import com.digkas.git.domain.Project;
import com.digkas.git.domain.SonarqubeIssues;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Repository
public interface SonarqubeIssuesRepository extends JpaRepository<SonarqubeIssues, Integer> {

	@Query("SELECT s FROM SonarqubeIssues s WHERE s.id IN (:ids)")
	Collection<SonarqubeIssues> findByIds(@Param("ids") Collection<Integer> ids);

	@Query("SELECT s FROM SonarqubeIssues s WHERE s.commitId.projectId = :project")
	Collection<SonarqubeIssues> findByProject(@Param("project") Project project);

	@Query("SELECT s FROM SonarqubeIssues s WHERE s.path = :path AND s.commitId.sha = :sha")
	Collection<SonarqubeIssues> findByPathAndSHA(@Param("path") String path, @Param("sha") String sha);

	@Query("SELECT s FROM SonarqubeIssues s WHERE s.commitId.id = :id")
	Collection<SonarqubeIssues> findByCommitId(@Param("id") Integer id);

	@Query("SELECT s FROM SonarqubeIssues s WHERE s.commitId = :commitId")
	Collection<SonarqubeIssues> findByCommitId(@Param("commitId") Commit commitId);

	@Transactional
	@Modifying
	@Query("DELETE FROM SonarqubeIssues s WHERE s.commitId.id = :id")
	int deleteByCommitId(@Param("id") Integer id);

	@Transactional
	@Modifying
	@Query("DELETE FROM SonarqubeIssues s WHERE s.commitId = :commitId")
	int deleteByCommitId(@Param("commitId") Commit commitId);

	@Transactional
	@Modifying
	@Query("DELETE FROM SonarqubeIssues s WHERE s.commitId.sha = :sha")
	int deleteByCommitId(@Param("sha") String sha);

	@Query("SELECT s FROM SonarqubeIssues s WHERE s.status LIKE 'OPEN' AND s.commitId = :commitId")
	Collection<SonarqubeIssues> findOpenIssuesByCommitId(@Param("commitId") Commit commitId);

	@Query("SELECT s FROM SonarqubeIssues s WHERE s.status LIKE 'OPEN' AND s.line IS NOT NULL AND s.commitId = :commitId")
	Collection<SonarqubeIssues> findOpenSonarqubeIssuesWithNonNullLinesByCommitId(@Param("commitId") Commit commitId);
	
	@Query("SELECT s FROM SonarqubeIssues s WHERE s.status LIKE 'OPEN' AND s.methodId IS NOT NULL AND s.line IS NOT NULL AND s.commitId = :commitId")
	Collection<SonarqubeIssues> findOpenSonarqubeIssuesMappedToMethodsWithNonNullLinesByCommitId(@Param("commitId") Commit commitId);

	@Query("SELECT s FROM SonarqubeIssues s WHERE s.status LIKE 'OPEN' AND s.line IS NOT NULL AND s.commitId.projectId = :project")
	Collection<SonarqubeIssues> findOpenSonarqubeIssuesWithNonNullLinesByProject(@Param("project") Project project);

	@Query("SELECT s FROM SonarqubeIssues s WHERE s.status = :status AND s.commitId.projectId = :projectId")
	Collection<SonarqubeIssues> findByStatusANDProjectId(@Param("status") String status, @Param("projectId") Project projectId);

	@Query("SELECT s FROM SonarqubeIssues s WHERE s.methodId IN (:methodIds)")
	Collection<SonarqubeIssues> findByMethodIds(@Param("methodIds") Collection<Integer> methodIds);

	@Query("SELECT s FROM SonarqubeIssues s WHERE s.path = :path AND s.commitId = :commitId")
	Collection<SonarqubeIssues> findByPathANDCommitId(@Param("path") String path, @Param("commitId") Commit commitId);

	@Query("SELECT s FROM SonarqubeIssues s WHERE s.path = :path AND s.commitId = :commitId AND s.methodId.name = :methodName")
	Collection<SonarqubeIssues> findByPathANDCommitIdANDMethodName(@Param("path") String path, @Param("commitId") Commit commitId, @Param("methodName") String methodName);

	@Query("SELECT s FROM SonarqubeIssues s WHERE s.methodId = :methodId")
	Collection<SonarqubeIssues> findByMethodId(@Param("methodId") Method methodId);

	@Query("SELECT s FROM SonarqubeIssues s WHERE s.commitId = :commitId AND s.kee IN (:kees)")
	Collection<SonarqubeIssues> findByCommitIdANDkees(@Param("commitId") Commit commitId, @Param("kees") Collection<String> kees);

}
