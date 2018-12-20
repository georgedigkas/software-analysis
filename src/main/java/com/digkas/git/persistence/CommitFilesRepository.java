/**
 * 
 */
package com.digkas.git.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digkas.git.domain.Commit;
import com.digkas.git.domain.CommitFiles;
import com.digkas.git.domain.Project;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Repository
public interface CommitFilesRepository extends JpaRepository<CommitFiles, Integer> {

	@Query("SELECT c FROM CommitFiles c WHERE c.commitId = :commitId)")
	Collection<CommitFiles> findByCommit(@Param("commitId") Commit commitId);

	@Query("SELECT c FROM CommitFiles c WHERE c.commitId.id IN (:commitIds)")
	Collection<CommitFiles> findByCommitIds(@Param("commitIds") List<Integer> commitIds);

	@Query("SELECT c FROM CommitFiles c WHERE c.commitId.projectId = :projectId")
	Collection<CommitFiles> findByProjectId(@Param("projectId") Project projectId);

	@Query("SELECT c FROM CommitFiles c WHERE c.commitId = :commitId AND c.newFilePath = :newFilePath AND c.changeType = :changeType)")
	CommitFiles findByCommitIdANDNewFilePathANDChangeType(@Param("commitId") Commit commitId, @Param("newFilePath") String newFilePath, @Param("changeType") String changeType);

}
