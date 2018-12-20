/**
 * 
 */
package com.digkas.git.service;

import java.util.Collection;
import java.util.List;

import com.digkas.git.domain.Project;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public interface ProjectService {

	Collection<Project> findAll();

	Project findOne(Integer id);

	Project create(Project project);

	List<Project> create(List<Project> projects);

	Project update(Project project);

	List<Project> update(List<Project> projects);

	void delete(Integer id);

	Project findByKee(String kee);

	Collection<Project> findWhereProjectUuidNotNull();

}
