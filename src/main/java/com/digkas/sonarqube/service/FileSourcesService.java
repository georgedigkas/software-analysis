/**
 * 
 */
package com.digkas.sonarqube.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.digkas.sonarqube.domain.FileSources;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public interface FileSourcesService {

	Collection<FileSources> findAll();

	FileSources findOne(Integer id);

	FileSources create(FileSources fileSource);

	FileSources update(FileSources fileSource);

	void delete(Integer id);

	Collection<FileSources> findByProjectUuid(String projectUuid);

	public Map<Optional<String>, List<FileSources>> findByProjectUuidGroupedByRevision(String projectUuid);

}
