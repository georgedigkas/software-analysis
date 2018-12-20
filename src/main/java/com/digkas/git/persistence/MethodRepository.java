/**
 * 
 */
package com.digkas.git.persistence;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.digkas.git.domain.Commit;
import com.digkas.git.domain.Method;
import com.digkas.git.domain.Project;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Repository
public interface MethodRepository extends JpaRepository<Method, Integer> {

	@Query("SELECT m FROM Method m WHERE m.commitId.projectId = :projectId")
	Collection<Method> findByIdProjectId(@Param("projectId") Project projectId);

	@Query("SELECT m FROM Method m WHERE m.commitId.projectId.kee = :kee")
	Collection<Method> findByIdProjectKee(@Param("kee") String kee);

	@Query("SELECT m FROM Method m WHERE m.commitId = :commitId")
	Collection<Method> findByCommitId(@Param("commitId") Commit commitId);

	@Query("SELECT m FROM Method m WHERE m.commitId.id = :commitId")
	Collection<Method> findByCommitId(@Param("commitId") Integer commitId);

	@Query(value = "SELECT * FROM method WHERE commit_id = ?1", nativeQuery = true)
	Collection<Method> findByCommitIdNative(Integer commitId);

	@Query("SELECT m FROM Method m WHERE m.commitId.projectId = :projectId AND m.oldMethodId IS NULL")
	Collection<Method> findMethodsWithNullOldMethodIdByProjectId(@Param("projectId") Project projectId);

	@Query("SELECT m FROM Method m WHERE m.commitId = :commitId AND m.oldMethodId IS NULL")
	Collection<Method> findMethodsWithNullOldMethodIdByCommitId(@Param("commitId") Commit commitId);

	@Query("SELECT m FROM Method m WHERE m.commitId = :commitId AND m.oldMethodId IS NOT NULL")
	Collection<Method> findMethodsWithNotNullOldMethodIdByCommitId(@Param("commitId") Commit commitId);

	@Transactional
	@Modifying
	@Query("DELETE FROM Method m WHERE m.commitId = :commitId")
	int deleteByCommitId(@Param("commitId") Commit commitId);

	@Transactional
	@Modifying
	@Query("DELETE FROM Method m WHERE m.commitId.id = :commitId")
	int deleteByCommitId(@Param("commitId") Integer commitId);

	@Transactional
	@Modifying
	@Query("DELETE FROM Method m WHERE m.commitId.sha = :sha")
	int deleteByCommitId(@Param("sha") String sha);

	@Query("SELECT DISTINCT m.oldMethodId.id FROM Method m WHERE m.commitId = :commitId AND m.oldMethodId IS NOT NULL")
	Set<Integer> findNotNullOldMethodIdsByCommitId(@Param("commitId") Commit commitId);
	
	@Query("SELECT DISTINCT s.methodId FROM SonarqubeIssues s WHERE s.commitId = :commitId AND s.kee IN (:kees)")
	Set<Method> findFromSonarqubeIssuesByCommitIdANDkees(@Param("commitId") Commit commitId, @Param("kees") Collection<String> kees);
	
	@Query("SELECT DISTINCT s.methodId FROM SonarqubeIssues s WHERE s.commitId = :commitId AND s.methodId.name = :methodName AND s.kee IN (:kees)")
	Set<Method> findFromSonarqubeIssuesByCommitIdANDMethodNameANDkees(@Param("commitId") Commit commitId, @Param("methodName") String methodName, @Param("kees") Collection<String> kees);
	
	@Query("SELECT DISTINCT s.methodId FROM SonarqubeIssues s WHERE s.commitId = :commitId AND s.methodId.path = :path AND s.kee IN (:kees)")
	Set<Method> findFromSonarqubeIssuesByCommitIdANDPathANDkees(@Param("commitId") Commit commitId, @Param("path") String path, @Param("kees") Collection<String> kees);

}
