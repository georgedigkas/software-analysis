/**
 * 
 */
package com.digkas.sonarqube.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digkas.sonarqube.domain.Events;
import com.digkas.sonarqube.domain.Metrics;
import com.digkas.sonarqube.domain.ProjectMeasures;
import com.digkas.sonarqube.persistence.ProjectMeasuresRepository;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Service
public class ProjectMeasuresServiceBean implements ProjectMeasuresService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProjectMeasuresRepository projectMeasuresRepository;

	@Autowired
	private MetricsService metricsService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.sonarqube.v671.ProjectMeasuresService#
	 * findAll()
	 */
	@Override
	public Collection<ProjectMeasures> findAll() {
		logger.info("> findAll");

		Collection<ProjectMeasures> projectMeasures = projectMeasuresRepository.findAll();

		logger.info("< findAll");
		return projectMeasures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.sonarqube.v671.ProjectMeasuresService#
	 * findOne(java.lang.Long)
	 */
	@Override
	public ProjectMeasures findOne(Long id) {
		logger.info("> findOne id: {}", id);

		ProjectMeasures projectMeasure = projectMeasuresRepository.findOne(id);

		logger.info("< findOne id: {}", id);
		return projectMeasure;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.sonarqube.v671.ProjectMeasuresService#
	 * create(com.digkas.softwareanalysis.domain.sonarqube.v671.ProjectMeasures)
	 */
	@Override
	public ProjectMeasures create(ProjectMeasures projectMeasure) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.sonarqube.v671.ProjectMeasuresService#
	 * update(com.digkas.softwareanalysis.domain.sonarqube.v671.ProjectMeasures)
	 */
	@Override
	public ProjectMeasures update(ProjectMeasures projectMeasure) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.sonarqube.v671.ProjectMeasuresService#
	 * delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		logger.info("> delete ProjectMeasure id: {}", id);

		projectMeasuresRepository.delete(id);

		logger.info("< delete ProjectMeasure id: {}", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.sonarqube.v671.ProjectMeasuresService#
	 * findByComponentUuid(java.lang.String)
	 */
	@Override
	public Collection<ProjectMeasures> findByComponentUuid(String componentUuid) {
		logger.info("> findByComponentUuid componentUuid: {}", componentUuid);

		Collection<ProjectMeasures> projectMeasures = projectMeasuresRepository.findByComponentUuid(componentUuid);

		logger.info("< findByComponentUuid componentUuid: {}", componentUuid);
		return projectMeasures;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.ProjectMeasuresService#findByComponentUuidMetricId(java.lang.String, int)
	 */
	@Override
	public Collection<ProjectMeasures> findByComponentUuidMetricId(String componentUuid, int metricId) {
		logger.info("> findByComponentUuidMetricId componentUuid: {}, metricId: {}", componentUuid, metricId);

		Collection<ProjectMeasures> projectMeasures = projectMeasuresRepository.findByComponentUuidMetricId(componentUuid, metricId);

		logger.info("< findByComponentUuidMetricId componentUuid: {}, metricId: {}", componentUuid, metricId);
		return projectMeasures;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.ProjectMeasuresService#findByComponentUuidMetricName(java.lang.String, java.lang.String)
	 */
	@Override
	public Collection<ProjectMeasures> findByComponentUuidMetricName(String componentUuid, String metricName) {
		logger.info("> findByComponentUuidMetricName componentUuid: {}, metricName: {}", componentUuid, metricName);

		Metrics metric = metricsService.findByName(metricName);

		logger.info("< findByComponentUuidMetricName componentUuid: {}, metricName: {}", componentUuid, metricName);
		return Objects.nonNull(metric) ? this.findByComponentUuidMetricId(componentUuid, metric.getId()) : null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.ProjectMeasuresService#projectMeasuresToMapSHAasKey(java.util.Collection, java.util.concurrent.ConcurrentMap)
	 */
	@Override
	public Map<String, BigDecimal> projectMeasuresToMapSHAasKey(Collection<ProjectMeasures> projectMeasures, ConcurrentMap<String, Events> eventsGroupedByAnalysisUuid) {
		logger.info("> projectMeasuresToMapSHAasKey");

		Map<String, BigDecimal> projectMeasuresMap = new HashMap<>();
		projectMeasures.forEach(pm -> {projectMeasuresMap.put(eventsGroupedByAnalysisUuid.get(pm.getAnalysisUuid()).getName(), pm.getValue());});

		logger.info("< projectMeasuresToMapSHAasKey");
		return projectMeasuresMap;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.ProjectMeasuresService#findByComponentUuidsAndAnalysisUuid(java.util.Collection, java.lang.String)
	 */
	@Override
	public Collection<ProjectMeasures> findByComponentUuidsAndAnalysisUuid(Collection<String> componentUuids, String analysisUuid) {
		logger.info("> findByComponentUuidsAndAnalysisUuid");

		Collection<ProjectMeasures> projectMeasures = projectMeasuresRepository.findByComponentUuidsAndAnalysisUuid(componentUuids, analysisUuid);

		logger.info("< findByComponentUuidsAndAnalysisUuid");
		return projectMeasures;
	}

}
