/**
 * 
 */
package com.digkas.sonarqube.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.digkas.sonarqube.domain.Projects;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public interface ProjectsService {

	Collection<Projects> findAll();

	Projects findOne(Integer id);

	Projects create(Projects project);

	Projects update(Projects project);

	void delete(Integer id);

	Projects findProjectByKee(String kee);

	Collection<Projects> findProjectByKees(List<String> kees);

	Collection<Projects> findByUuids(List<String> uuids);

	Map<String, Projects> findByUuidsFilePathMap(List<String> uuids);

	Collection<Projects> findFilesByProjectUuidPaths(String projectUuid, Collection<String> paths);

	Collection<Projects> findByUuidsAndProjectUuid(Collection<String> uuids, String projectUuid);

	Map<String, Projects> findByUuidsAndProjectUuidMapUuids(Collection<String> uuids, String projectUuid);

	Collection<Projects> findAllProjects();

	Collection<Projects> findModulesByProject(Projects project);

	Collection<Projects> findModulesByProjectUuid(String projectUuid);

	Collection<Projects> findModulesByUuids(Collection<String> modulesUuids);

	Map<String, Projects> groupByPath(Collection<Projects> projects);

	Map<String, Projects> groupByKee(Collection<Projects> projects);

	Collection<Projects> findByProjectUuid(String projectUuid);

	Collection<Projects> findByScopeQualifierAndProjectUuid(String scope, String qualifier, String projectUuid);

	Collection<Projects> findByScopeQualifierAndProject(String scope, String qualifier, Projects project);

}
