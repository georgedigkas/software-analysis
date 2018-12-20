/**
 * 
 */
package com.digkas.sonarqube.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digkas.sonarqube.domain.Projects;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Integer> {

	@Query("SELECT p FROM Projects p WHERE p.kee = :kee AND p.scope = 'PRJ'")
	Projects findProjectByKee(@Param("kee") String kee);

	@Query("SELECT p FROM Projects p WHERE p.kee IN (:kees) AND p.scope = 'PRJ'")
	Collection<Projects> findProjectByKees(@Param("kees") List<String> kees);

	@Query("SELECT p FROM Projects p WHERE p.uuid IN (:uuids)")
	Collection<Projects> findByUuids(@Param("uuids") List<String> uuids);

	@Query("SELECT p FROM Projects p WHERE p.projectUuid = :projectUuid AND p.scope = 'FIL' AND p.path IN (:paths)")
	Collection<Projects> findFilesByProjectUuidPaths(@Param("projectUuid") String projectUuid, @Param("paths") Collection<String> paths);

	@Query("SELECT p FROM Projects p WHERE p.projectUuid = :projectUuid AND p.uuid IN (:uuids)")
	Collection<Projects> findByUuidsAndProjectUuid(@Param("uuids") Collection<String> uuids, @Param("projectUuid") String projectUuid);

	@Query("SELECT p FROM Projects p WHERE p.scope = 'PRJ' AND p.qualifier = 'TRK' AND p.enabled = true")
	Collection<Projects> findAllProjects();

	@Query("SELECT p FROM Projects p WHERE p.language IS NULL AND p.scope = 'PRJ' AND p.qualifier = 'BRC' AND p.moduleUuid IS NOT NULL AND p.projectUuid = :projectUuid")
	Collection<Projects> findModulesByProjectUuid(@Param("projectUuid") String projectUuid);

	//TODO maybe I have to add qualifier and scope
	@Query("SELECT p FROM Projects p WHERE p.uuid IN (:modulesUuids)")
	Collection<Projects> findModulesByUuids(@Param("modulesUuids") Collection<String> modulesUuids);

	@Query("SELECT p FROM Projects p WHERE p.projectUuid = :projectUuid")
	Collection<Projects> findByProjectUuid(@Param("projectUuid") String projectUuid);

	@Query("SELECT p FROM Projects p WHERE p.scope = :scope AND p.qualifier = :qualifier AND p.projectUuid = :projectUuid")
	Collection<Projects> findByScopeQualifierAndProjectUuid(@Param("scope") String scope, @Param("qualifier") String qualifier, @Param("projectUuid") String projectUuid);

}
