/**
 * 
 */
package com.digkas.git.service;

import java.util.Collection;
import java.util.List;

import com.digkas.git.domain.Commit;
import com.digkas.git.domain.Project;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public interface CommitService {

	Collection<Commit> findAll();

	Commit findOne(Integer id);

	Commit create(Commit commit);

	List<Commit> create(List<Commit> commits);

	Commit update(Commit commit);

	List<Commit> update(List<Commit> commits);

	void delete(Integer id);

	void delete(Iterable<Commit> commits);

	Collection<Commit> findByIds(Collection<Integer> ids);

	Collection<Commit> findByIdProjectId(Project projectId);

	Collection<Commit> findByIdProjectKee(String kee);
	
	Commit findByProjectIdAndSHA(Project projectId, String sha);

}
