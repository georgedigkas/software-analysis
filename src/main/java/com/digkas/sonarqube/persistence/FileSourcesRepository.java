/**
 * 
 */
package com.digkas.sonarqube.persistence;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digkas.sonarqube.domain.FileSources;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Repository
public interface FileSourcesRepository extends JpaRepository<FileSources, Integer> {

	@Query("SELECT f FROM FileSources f WHERE f.projectUuid = :projectUuid")
	Collection<FileSources> findByProjectUuid(@Param("projectUuid") String projectUuid);

}
