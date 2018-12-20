/**
 * 
 */
package com.digkas.sonarqube.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import com.digkas.sonarqube.domain.Events;
import com.digkas.sonarqube.domain.ProjectMeasures;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public interface ProjectMeasuresService {

	Collection<ProjectMeasures> findAll();

	ProjectMeasures findOne(Long id);

	ProjectMeasures create(ProjectMeasures projectMeasure);

	ProjectMeasures update(ProjectMeasures projectMeasure);

	void delete(Long id);

	Collection<ProjectMeasures> findByComponentUuid(String componentUuid);

	Collection<ProjectMeasures> findByComponentUuidMetricId(String componentUuid, int metricId);

	Collection<ProjectMeasures> findByComponentUuidMetricName(String componentUuid, String metricName);

	Map<String, BigDecimal> projectMeasuresToMapSHAasKey(Collection<ProjectMeasures> projectMeasures, ConcurrentMap<String, Events> eventsGroupedByAnalysisUuid);

	Collection<ProjectMeasures> findByComponentUuidsAndAnalysisUuid(Collection<String> componentUuids, String analysisUuid);

}
