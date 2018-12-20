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
public class CommitFilesServicess implements CommitFilesService {

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#findAll()
	 */
	@Override
	public Collection<CommitFiles> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#findOne(java.lang.Integer)
	 */
	@Override
	public CommitFiles findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#create(com.digkas.git.domain.CommitFiles)
	 */
	@Override
	public CommitFiles create(CommitFiles commitFile) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#create(java.util.List)
	 */
	@Override
	public List<CommitFiles> create(List<CommitFiles> commitFiles) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#update(com.digkas.git.domain.CommitFiles)
	 */
	@Override
	public CommitFiles update(CommitFiles commitFile) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#update(java.util.List)
	 */
	@Override
	public List<CommitFiles> update(List<CommitFiles> commitFiles) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#delete(java.lang.Iterable)
	 */
	@Override
	public void delete(Iterable<CommitFiles> commitFiles) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#findByCommitIds(java.util.List)
	 */
	@Override
	public Collection<CommitFiles> findByCommitIds(List<Integer> commitIds) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#findByProjectId(int)
	 */
	@Override
	public Collection<CommitFiles> findByProjectId(int projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#findByProjectKee(java.lang.String)
	 */
	@Override
	public Collection<CommitFiles> findByProjectKee(String kee) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#findByProject(com.digkas.git.domain.Project)
	 */
	@Override
	public Collection<CommitFiles> findByProject(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#commitFilesTable(java.util.Collection)
	 */
	@Override
	public Table<String, String, CommitFiles> commitFilesTable(Collection<CommitFiles> commitFiles) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#filterByChangeType(java.util.Collection, java.lang.String[])
	 */
	@Override
	public Collection<CommitFiles> filterByChangeType(Collection<CommitFiles> commitFiles, String... changeTypes) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#findByProjectId(com.digkas.git.domain.Project)
	 */
	@Override
	public Collection<CommitFiles> findByProjectId(Project projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#findByCommit(com.digkas.git.domain.Commit)
	 */
	@Override
	public Collection<CommitFiles> findByCommit(Commit commitId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#findByCommitIdANDNewFilePathANDChangeType(com.digkas.git.domain.Commit, java.lang.String, java.lang.String)
	 */
	@Override
	public CommitFiles findByCommitIdANDNewFilePathANDChangeType(Commit commitId, String newFilePath,
			String changeType) {
		// TODO Auto-generated method stub
		return null;
	}

}
