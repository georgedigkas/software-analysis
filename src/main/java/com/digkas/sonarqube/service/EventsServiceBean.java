/**
 * 
 */
package com.digkas.sonarqube.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digkas.sonarqube.domain.Events;
import com.digkas.sonarqube.domain.ProjectMeasures;
import com.digkas.sonarqube.domain.Projects;
import com.digkas.sonarqube.persistence.EventsRepository;
import com.digkas.sonarqube.persistence.ProjectMeasuresRepository;
import com.digkas.sonarqube.persistence.ProjectsRepository;
import com.google.common.collect.Iterables;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Service
public class EventsServiceBean implements EventsService {

	private static final int JDBC_PARAMETERS_LIMIT = 32767;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EventsRepository eventsRepository;

	@Autowired
	private ProjectsRepository projectsRepository;

	@Autowired
	private ProjectMeasuresRepository projectMeasuresRepository;

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.EventsService#findAll()
	 */
	@Override
	public Collection<Events> findAll() {
		logger.info("> findAll");

		Collection<Events> events = eventsRepository.findAll();

		logger.info("< findAll");
		return events;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.EventsService#findOne(java.lang.Integer)
	 */
	@Override
	public Events findOne(Integer id) {
		logger.info("> findOne id: {}", id);

		Events event = eventsRepository.findOne(id);

		logger.info("< findOne id: {}", id);
		return event;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.EventsService#create(com.digkas.softwareanalysis.domain.sonarqube.v671.Events)
	 */
	@Override
	public Events create(Events event) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.EventsService#update(com.digkas.softwareanalysis.domain.sonarqube.v671.Events)
	 */
	@Override
	public Events update(Events event) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.EventsService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		logger.info("> delete Event id: {}", id);

		eventsRepository.delete(id);

		logger.info("< delete Event id: {}", id);
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.EventsService#findVersionsByAnalysisUuid(java.util.Collection)
	 */
	@Override
	public Collection<Events> findVersionsByAnalysisUuid(Collection<String> analysisUuids) {
		logger.info("> findVersionsByAnalysisUuid");

		Collection<Events> events = eventsRepository.findVersionsByAnalysisUuid(analysisUuids);

		logger.info("< findVersionsByAnalysisUuid");
		return events;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.EventsService#findAllVersionsByProjectKee(java.lang.String)
	 */
	@Override
	public Collection<Events> findAllVersionsByProjectKee(String projectKee) {
		Set<Events> eventsSet = new HashSet<>();
		List<Events> analyzedVersions = new ArrayList<>(); 

		logger.info("> findAllVersionsByProjectKee: {}", projectKee);

		Projects project = projectsRepository.findProjectByKee(projectKee);

		if (Objects.nonNull(project)) {
			Collection<ProjectMeasures> projectMeasures = projectMeasuresRepository.findByComponentUuid(project.getUuid());
			List<String> projectMeasuresAnalysisUuids = projectMeasures.stream().map(ProjectMeasures::getAnalysisUuid).collect(Collectors.toList());

			for (int i = 0; i < projectMeasuresAnalysisUuids.size(); i += JDBC_PARAMETERS_LIMIT)
				eventsSet.addAll(this.findVersionsByAnalysisUuid(projectMeasuresAnalysisUuids.subList(i, Math.min(projectMeasuresAnalysisUuids.size(), i + JDBC_PARAMETERS_LIMIT))));

			analyzedVersions = eventsSet.stream().sorted(Comparator.comparing(Events::getEventDate)).collect(Collectors.toList());
		}

		logger.info("< findAllVersionsByProjectKee: {}", projectKee);
		return analyzedVersions;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.EventsService#findAllNotAnalyzedVersionsByProjectKees(java.lang.String, java.lang.String)
	 */
	@Override
	public Set<String> findAllNotAnalyzedVersionsByProjectKees(String projectKeeA, String projectKeeB) {
		Set<String> projectKeeAVersionsSet = this.findAllVersionsByProjectKee(projectKeeA).stream().map(Events::getName).collect(Collectors.toSet());
		Set<String> projectKeeBVersionsSet = this.findAllVersionsByProjectKee(projectKeeB).stream().map(Events::getName).collect(Collectors.toSet());
		projectKeeAVersionsSet.removeAll(projectKeeBVersionsSet);
		return projectKeeAVersionsSet;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.EventsService#filterOutEvents(java.util.Collection, java.util.Set)
	 */
	@Override
	public Collection<Events> filterOutEvents(Collection<Events> events, Set<String> shas) {
		logger.info("> filterOutEvents");

		events = events.stream().filter(event -> !shas.contains(event.getName())).collect(Collectors.toSet());

		logger.info("< filterOutEvents");
		return events;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.EventsService#findLastAnalyzedVersionByProjectKee(java.lang.String)
	 */
	@Override
	public Events findLastAnalyzedVersionByProjectKee(String projectKee) {
		logger.info("> findLastAnalyzedVersionsByProjectKee: {}", projectKee);

		Events lastAnalyzedVersion = Iterables.getLast(this.findAllVersionsByProjectKee(projectKee));

		logger.info("< findLastAnalyzedVersionsByProjectKee: {}", projectKee);
		return lastAnalyzedVersion;
	}

}
