/**
 * 
 */
package com.digkas.sonarqube.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digkas.sonarqube.domain.Events;
import com.digkas.sonarqube.domain.ProjectMeasures;
import com.digkas.sonarqube.domain.Projects;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Service
public class ComparisonBetweenTwoComponentUuidsBean implements ComparisonBetweenTwoComponentUuids {

	@Autowired
	private EventsService eventsService;
	@Autowired
	private ProjectsService projectsService;
	@Autowired
	private ProjectMeasuresService projectMeasuresService;

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.ComparisonBetweenTwoComponentUuids#printComparisonBetweenTwoComponentUuids(java.lang.String, java.lang.String, int)
	 */
	@Override
	public void printComparisonBetweenTwoComponentUuids(String projectKeeA, String projectKeeB, int metricId) {
		Projects projectsA = projectsService.findProjectByKee(projectKeeA);
		Projects projectsB = projectsService.findProjectByKee(projectKeeB);

		Collection<Events> projectsAEvents = eventsService.findAllVersionsByProjectKee(projectKeeA);
		Collection<Events> projectsBEvents = eventsService.findAllVersionsByProjectKee(projectKeeB);

		Map<String, String> projectsAVersionsMap = projectsAEvents.stream().collect(Collectors.toMap(Events::getName, Events::getAnalysisUuid));
		Map<String, String> projectsBVersionsMap = projectsBEvents.stream().collect(Collectors.toMap(Events::getName, Events::getAnalysisUuid));

		Map<String, BigDecimal> projectsAMeasuresMap = projectMeasuresService.findByComponentUuidMetricId(projectsA.getUuid(), metricId).stream().collect(Collectors.toMap(ProjectMeasures::getAnalysisUuid, ProjectMeasures::getValue));
		Map<String, BigDecimal> commonsIOModifiedMeasures = projectMeasuresService.findByComponentUuidMetricId(projectsB.getUuid(), metricId).stream().collect(Collectors.toMap(ProjectMeasures::getAnalysisUuid, ProjectMeasures::getValue));

		for (Events event : projectsAEvents)
			System.out.println(event.getName() + "\t" + projectsAMeasuresMap.get(projectsAVersionsMap.get(event.getName())) + "\t" + commonsIOModifiedMeasures.get(projectsBVersionsMap.get(event.getName())));
	}

}
