/**
 * 
 */
package com.digkas.git.service;

import java.util.Collection;
import java.util.List;

import com.digkas.git.domain.Commit;
import com.digkas.git.domain.CommitFiles;
import com.digkas.git.domain.Project;
import com.google.common.collect.Table;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public interface CommitFilesService {

	Collection<CommitFiles> findAll();

	CommitFiles findOne(Integer id);

	CommitFiles create(CommitFiles commitFile);

	List<CommitFiles> create(List<CommitFiles> commitFiles);

	CommitFiles update(CommitFiles commitFile);

	List<CommitFiles> update(List<CommitFiles> commitFiles);

	void delete(Integer id);

	void delete(Iterable<CommitFiles> commitFiles);

	Collection<CommitFiles> findByCommitIds(List<Integer> commitIds);

	Collection<CommitFiles> findByProjectId(int projectId);

	Collection<CommitFiles> findByProjectKee(String kee);

	Collection<CommitFiles> findByProject(Project project);

	Table<String, String, CommitFiles> commitFilesTable(Collection<CommitFiles> commitFiles);

	Collection<CommitFiles> filterByChangeType(Collection<CommitFiles> commitFiles, String... changeTypes);

	Collection<CommitFiles> findByProjectId(Project projectId);

	Collection<CommitFiles> findByCommit(Commit commitId);

	CommitFiles findByCommitIdANDNewFilePathANDChangeType(Commit commitId, String newFilePath, String changeType);

}
