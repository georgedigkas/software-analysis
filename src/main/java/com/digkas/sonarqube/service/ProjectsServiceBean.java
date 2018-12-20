/**
 * 
 */
package com.digkas.sonarqube.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digkas.sonarqube.domain.Projects;
import com.digkas.sonarqube.persistence.ProjectsRepository;

import rx.Observable;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Service
public class ProjectsServiceBean implements ProjectsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProjectsRepository projectsRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.sonarqube.service.ProjectsService#findAll()
	 */
	@Override
	public Collection<Projects> findAll() {
		logger.info("> findAll");

		Collection<Projects> projects = projectsRepository.findAll();

		logger.info("< findAll");
		return projects;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.sonarqube.service.ProjectsService#findOne(
	 * java.lang.Integer)
	 */
	@Override
	public Projects findOne(Integer id) {
		logger.info("> findOne id: {}", id);

		Projects project = projectsRepository.findOne(id);

		logger.info("< findOne id: {}", id);
		return project;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.sonarqube.service.ProjectsService#create(com
	 * .digkas.softwareanalysis.domain.sonarqube.v671.Projects)
	 */
	@Override
	public Projects create(Projects project) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.sonarqube.service.ProjectsService#update(com
	 * .digkas.softwareanalysis.domain.sonarqube.v671.Projects)
	 */
	@Override
	public Projects update(Projects project) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.sonarqube.service.ProjectsService#delete(
	 * java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		logger.info("> delete Projects id: {}", id);

		projectsRepository.delete(id);

		logger.info("< delete Projects id: {}", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digkas.sonarqube.service.ProjectsService#
	 * findProjectByKee(java.lang.String)
	 */
	@Override
	public Projects findProjectByKee(String kee) {//FIXME TEST WHY I USED TO HAVE BREAKPOINTS AND SYSOS
		logger.info("> findProjectByKee kee: {}", kee);

		Projects project = projectsRepository.findProjectByKee(kee);

		logger.info("< findProjectByKee kee: {}", kee);
		return project;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.ProjectsService#findProjectByKees(java.util.List)
	 */
	@Override
	public Collection<Projects> findProjectByKees(List<String> kees) {
		logger.info("> findProjectByKees");

		Collection<Projects> projects = projectsRepository.findProjectByKees(kees);

		logger.info("< findProjectByKees");
		return projects;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.ProjectsService#findByUuids(java.util.List)
	 */
	@Override
	public Collection<Projects> findByUuids(List<String> uuids) {
		logger.info("> findByUuids");

		Collection<Projects> projects = projectsRepository.findByUuids(uuids);

		logger.info("< findByUuids");
		return projects;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.ProjectsService#findByUuidsFilePathMap(java.util.List)
	 */
	@Override
	public Map<String, Projects> findByUuidsFilePathMap(List<String> uuids) {
		logger.info("> findByUuidsMap");

		Map<String, Projects> projectsMap = this.findByUuids(uuids).stream().collect((Collectors.toMap(Projects::getPath, Function.identity())));

		logger.info("< findByUuidsMap");
		return projectsMap;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.ProjectsService#findFilesByProjectUuidPaths(java.lang.String, java.util.List)
	 */
	@Override
	public Collection<Projects> findFilesByProjectUuidPaths(String projectUuid, Collection<String> paths) {
		logger.info("> findFilesByProjectUuidPaths projectUuid: {}", projectUuid);

		Collection<Projects> projects = projectsRepository.findFilesByProjectUuidPaths(projectUuid, paths);

		logger.info("< findFilesByProjectUuidPaths projectUuid: {}", projectUuid);
		return projects;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.ProjectsService#findByUuidsAndProjectUuid(java.util.Collection, java.lang.String)
	 */
	@Override
	public Collection<Projects> findByUuidsAndProjectUuid(Collection<String> uuids, String projectUuid) {
		logger.info("> findByUuidsAndProjectUuid projectUuid: {}", projectUuid);

		Collection<Projects> projects = projectsRepository.findByUuidsAndProjectUuid(uuids, projectUuid);

		logger.info("< findByUuidsAndProjectUuid projectUuid: {}", projectUuid);
		return projects;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.ProjectsService#findByUuidsAndProjectUuidMapUuids(java.util.Collection, java.lang.String)
	 */
	@Override
	public Map<String, Projects> findByUuidsAndProjectUuidMapUuids(Collection<String> uuids, String projectUuid) {
		logger.info("> findByUuidsAndProjectUuidMapUuids projectUuid: {}", projectUuid);

		Map<String, Projects> projectsMap = this.findByUuidsAndProjectUuid(uuids, projectUuid).stream().collect((Collectors.toMap(Projects::getUuid, Function.identity())));

		logger.info("< findByUuidsAndProjectUuidMapUuids projectUuid: {}", projectUuid);
		return projectsMap;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.ProjectsService#findAllProjects()
	 */
	@Override
	public Collection<Projects> findAllProjects() {
		logger.info("> findAllProjects");

		Collection<Projects> projects = projectsRepository.findAllProjects();

		logger.info("< findAllProjects");
		return projects;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.ProjectsService#findModulesByProject(com.digkas.sonarqube.domain.Projects)
	 */
	@Override
	public Collection<Projects> findModulesByProject(Projects project) {
		logger.info("> findModulesByProject project: {}", project);

		Collection<Projects> modules = this.findModulesByProjectUuid(project.getUuid());

		logger.info("< findModulesByProject project: {}", project);
		return modules;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.ProjectsService#findModulesByProjectUuid(java.lang.String)
	 */
	@Override
	public Collection<Projects> findModulesByProjectUuid(String projectUuid) {
		logger.info("> findModulesByProject projectUuid: {}", projectUuid);

		Observable<Projects> modulesObservable = Observable.from(projectsRepository.findModulesByProjectUuid(projectUuid)).distinct(Projects::getId);
		List<Projects> modules = modulesObservable.toList().toBlocking().single();
		//modules = observable.to
		//Set<Projects> modules = projectsRepository.findModulesByProjectUuid(projectUuid)
		//.filter(distinctByKey(Projects::getModuleUuid)).collect(Collectors.toSet());
		//.stream().collect(Collectors.toSet());

		logger.info("< findModulesByProject projectUuid: {}", projectUuid);
		return modules;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.ProjectsService#findModulesByUuids(java.util.Collection)
	 */
	@Override
	public Collection<Projects> findModulesByUuids(Collection<String> modulesUuids) {
		logger.info("> findModulesByUuids");

		Set<Projects> projects = projectsRepository.findModulesByUuids(modulesUuids).stream().collect(Collectors.toSet());

		logger.info("< findModulesByUuids");
		return projects;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.ProjectsService#groupByPath(java.util.Collection)
	 */
	@Override
	public Map<String, Projects> groupByPath(Collection<Projects> projects) {
		logger.info("> groupByPath");

		Map<String, Projects> projectsMap = projects
				.stream()
				.collect((Collectors.toMap(Projects::getPath, Function.identity())));

		logger.info("< groupByPath");
		return projectsMap;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.ProjectsService#groupByKee(java.util.Collection)
	 */
	@Override
	public Map<String, Projects> groupByKee(Collection<Projects> projects) {
		logger.info("> groupByKee");

		Map<String, Projects> projectsMap = projects
				.stream()
				.collect((Collectors.toMap(Projects::getKee, Function.identity())));

		logger.info("< groupByKee");
		return projectsMap;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.ProjectsService#findByProjectUuid(java.lang.String)
	 */
	@Override
	public Collection<Projects> findByProjectUuid(String projectUuid) {
		logger.info("> findByProjectUuid projectUuid: {}", projectUuid);

		Collection<Projects> projects = projectsRepository.findByProjectUuid(projectUuid);

		logger.info("< findByProjectUuid projectUuid: {}", projectUuid);
		return projects;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.ProjectsService#findByScopeQualifierAndProjectUuid(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Collection<Projects> findByScopeQualifierAndProjectUuid(String scope, String qualifier, String projectUuid) {
		logger.info("> findByScopeQualifierAndProjectUuid scope: {}, qualifier: {}, projectUuid: {}", scope, qualifier, projectUuid);

		Collection<Projects> projects = projectsRepository.findByScopeQualifierAndProjectUuid(scope, qualifier, projectUuid);

		logger.info("< findByScopeQualifierAndProjectUuid scope: {}, qualifier: {}, projectUuid: {}", scope, qualifier, projectUuid);
		return projects;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.ProjectsService#findByScopeQualifierAndProject(java.lang.String, java.lang.String, com.digkas.sonarqube.domain.Projects)
	 */
	@Override
	public Collection<Projects> findByScopeQualifierAndProject(String scope, String qualifier, Projects project) {
		logger.info("> findByScopeQualifierAndProjectUuid scope: {}, qualifier: {}, project: {}", scope, qualifier, project);

		Collection<Projects> projects = this.findByScopeQualifierAndProjectUuid(scope, qualifier, project.getUuid());

		logger.info("< findByScopeQualifierAndProjectUuid scope: {}, qualifier: {}, project: {}", scope, qualifier, project);
		return projects;
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}

}
