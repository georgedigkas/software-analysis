package com.digkas.git.persistence;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digkas.git.domain.Commit;
import com.digkas.git.domain.Project;

/**
 * 
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Repository
public interface CommitRepository extends JpaRepository<Commit, Integer> {

	@Query("SELECT c FROM Commit c WHERE c.id IN (:ids)")
	Collection<Commit> findByIds(@Param("ids") Collection<Integer> ids);

	@Query("SELECT c FROM Commit c WHERE c.projectId = :projectId")
	Collection<Commit> findByIdProjectId(@Param("projectId") Project projectId);

	@Query("SELECT c FROM Commit c WHERE c.projectId.kee = :kee")
	Collection<Commit> findByIdProjectKee(@Param("kee") String kee);
	
	@Query("SELECT c FROM Commit c WHERE c.projectId = :projectId AND c.sha = :sha")
	Commit findByProjectIdAndSHA(@Param("projectId") Project projectId, @Param("sha") String sha);

}
