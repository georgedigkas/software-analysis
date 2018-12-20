/**
 * 
 */
package com.digkas.git.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digkas.git.domain.Project;
import com.digkas.git.persistence.ProjectRepository;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Service
public class ProjectServiceBean implements ProjectService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProjectRepository projectRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digkas.softwareanalysis.service.git.ProjectService#findAll()
	 */
	@Override
	public Collection<Project> findAll() {
		logger.info("> findAll");

		Collection<Project> projects = projectRepository.findAll();

		logger.info("< findAll");
		return projects;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.git.ProjectService#findOne(java.lang.
	 * Integer)
	 */
	@Override
	public Project findOne(Integer id) {
		logger.info("> findOne id:{}", id);

		Project project = projectRepository.findOne(id);

		logger.info("< findOne id:{}", id);
		return project;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.git.ProjectService#create(com.digkas.
	 * softwareanalysis.domain.git.Project)
	 */
	@Override
	public Project create(Project project) {
		logger.info("> create Project name:{}", project.getName());

		Project savedProject = projectRepository.save(project);

		logger.info("< create Project id:{}", project.getId());
		return savedProject;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.git.ProjectService#create(java.util.List)
	 */
	@Override
	public List<Project> create(List<Project> projects) {
		logger.info("> create Projects");

		List<Project> savedProjects = projectRepository.save(projects);

		logger.info("< create Projects");
		return savedProjects;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.git.ProjectService#update(com.digkas.
	 * softwareanalysis.domain.git.Project)
	 */
	@Override
	public Project update(Project project) {
		logger.info("> update Project id:{}", project.getId());

		Project projectToUpdate = findOne(project.getId());
		if (projectToUpdate == null)
			return null;

		Project updatedProject = projectRepository.save(project);

		logger.info("< update Project id:{}", project.getId());
		return updatedProject;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.git.ProjectService#update(java.util.List)
	 */
	@Override
	public List<Project> update(List<Project> projects) {
		logger.info("> update Projects");

		List<Project> projectsToBeUpdated = new ArrayList<>();

		for (Project project : projects) {
			Project projectToUpdate = findOne(project.getId());
			if (Objects.nonNull(projectToUpdate))
				projectsToBeUpdated.add(project);
		}

		List<Project> updatedProjects = projectRepository.save(projectsToBeUpdated);

		logger.info("< update Projects");
		return updatedProjects;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digkas.softwareanalysis.service.git.ProjectService#delete(java.lang.
	 * Integer)
	 */
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.git.ProjectService#findByKee(java.lang.String)
	 */
	@Override
	public Project findByKee(String kee) {
		logger.info("> findByKee: {}", kee);

		Project project = projectRepository.findByKee(kee);

		logger.info("< findByKee: {}", kee);
		return project;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.git.ProjectService#findWhereProjectUuidNotNull()
	 */
	@Override
	public Collection<Project> findWhereProjectUuidNotNull() {
		logger.info("> findWhereProjectUuidNotNull");

		Collection<Project> projects = projectRepository.findWhereProjectUuidNotNull();

		logger.info("< findWhereProjectUuidNotNull");
		return projects;
	}

}
