/**
 * 
 */
package com.digkas.git.persistence;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digkas.git.domain.Project;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
	@Query("SELECT p FROM Project p WHERE p.kee = :kee")
	Project findByKee(@Param("kee") String kee);

	@Query("SELECT p FROM Project p WHERE p.projectUuid IS NOT NULL")
	Collection<Project> findWhereProjectUuidNotNull();

}
