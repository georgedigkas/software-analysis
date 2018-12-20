/**
 * 
 */
package com.digkas.sonarqube.service;

import java.util.Collection;
import java.util.Set;

import com.digkas.sonarqube.domain.Events;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public interface EventsService {

	Collection<Events> findAll();

	Events findOne(Integer id);

	Events create(Events event);

	Events update(Events event);

	void delete(Integer id);

	Collection<Events> findVersionsByAnalysisUuid(Collection<String> analysisUuids);

	Collection<Events> findAllVersionsByProjectKee(String projectKee);

	Set<String> findAllNotAnalyzedVersionsByProjectKees(String projectKeeA, String projectKeeB);

	Collection<Events> filterOutEvents(Collection<Events> events, Set<String> shas);

	Events findLastAnalyzedVersionByProjectKee(String projectKee);

}
