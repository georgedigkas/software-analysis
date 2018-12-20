/**
 * 
 */
package com.digkas.git.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digkas.git.domain.Commit;
import com.digkas.git.domain.CommitFiles;
import com.digkas.git.domain.Project;
import com.digkas.git.persistence.CommitFilesRepository;
import com.digkas.git.persistence.ProjectRepository;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Service
public class CommitFilesServiceBean implements CommitFilesService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommitFilesRepository commitFilesRepository;

	@Autowired
	private ProjectRepository projectRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digkas.softwareanalysis.service.git.CommitFilesService#findAll()
	 */
	@Override
	public Collection<CommitFiles> findAll() {
		logger.info("> findAll");

		Collection<CommitFiles> commitFiles = commitFilesRepository.findAll();

		logger.info("< findAll");
		return commitFiles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.git.CommitFilesService#findOne(java.lang.
	 * Integer)
	 */
	@Override
	public CommitFiles findOne(Integer id) {
		logger.info("> findOne id:{}", id);

		CommitFiles commitFile = commitFilesRepository.findOne(id);

		logger.info("< findOne id:{}", id);
		return commitFile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.git.CommitFilesService#create(com.digkas.
	 * softwareanalysis.domain.git.CommitFiles)
	 */
	@Override
	public CommitFiles create(CommitFiles commitFile) {
		logger.info("> create CommitFile for Commit:{}", commitFile.getCommitId().getSha());

		CommitFiles savedCommitFiles = commitFilesRepository.save(commitFile);

		logger.info("< create CommitFile for Commit:{}", commitFile.getCommitId().getSha());
		return savedCommitFiles;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.git.CommitFilesService#create(java.util.List)
	 */
	@Override
	public List<CommitFiles> create(List<CommitFiles> commitFiles) {
		logger.info("> create CommitFiles");

		List<CommitFiles> savedCommitFiles = commitFilesRepository.save(commitFiles);

		logger.info("< create CommitFiles");
		return savedCommitFiles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.git.CommitFilesService#update(com.digkas.
	 * softwareanalysis.domain.git.CommitFiles)
	 */
	@Override
	public CommitFiles update(CommitFiles commitFile) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.git.CommitFilesService#update(java.util.List)
	 */
	@Override
	public List<CommitFiles> update(List<CommitFiles> commitFiles) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.git.CommitFilesService#delete(java.lang.
	 * Integer)
	 */
	@Override
	public void delete(Integer id) {
		logger.info("> delete id: {}", id);

		commitFilesRepository.delete(id);

		logger.info("< delete id: {}", id);
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#delete(java.lang.Iterable)
	 */
	@Override
	public void delete(Iterable<CommitFiles> commitFiles) {
		logger.info("> delete Iterable");

		commitFilesRepository.deleteInBatch(commitFiles);

		logger.info("< delete Iterable");
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.git.CommitFilesService#findByCommitIds(java.util.List)
	 */
	@Override
	public Collection<CommitFiles> findByCommitIds(List<Integer> commitIds) {
		logger.info("> findByCommitIds");

		Collection<CommitFiles> commitFiles = commitFilesRepository.findByCommitIds(commitIds);

		logger.info("< findByCommitIds");
		return commitFiles;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.git.CommitFilesService#findByProjectId(int)
	 */
	@Override
	public Collection<CommitFiles> findByProjectId(int projectId) {
		logger.info("> findByProjectId id: {}", projectId);

		Project project = projectRepository.findOne(projectId);
		ArrayList<Commit> projectCommits = new ArrayList<Commit>(project.getCommitCollection());
		List<Integer> projectCommitIDs = projectCommits.stream().map(Commit::getId).collect(Collectors.toList());

		logger.info("< findByProjectId id: {}", projectId);
		return (this.findByCommitIds(projectCommitIDs));
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#findByProjectKee(java.lang.String)
	 */
	@Override
	public Collection<CommitFiles> findByProjectKee(String kee) {
		logger.info("> findByProjectKee kee{}:", kee);

		Project project = projectRepository.findByKee(kee);

		logger.info("< findByProjectKee kee{}:", kee);
		return this.findByProjectId(project.getId());
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#findByProject(com.digkas.git.domain.Project)
	 */
	@Override
	public Collection<CommitFiles> findByProject(Project project) {
		logger.info("> findByProject");

		logger.info("< findByProject");
		return this.findByProjectId(project.getId());
	}

	//TODO TEST it
	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#commitFilesTable(java.util.Collection)
	 */
	@Override
	public Table<String, String, CommitFiles> commitFilesTable(Collection<CommitFiles> commitFiles) {
		logger.info("> commitFilesTable");

		Table<String, String, CommitFiles> commitFilesTable = HashBasedTable.create();
		//TODO filter out CommitFiles with NULL sha and newPath

		for (CommitFiles commitFile : commitFiles)
			commitFilesTable.put(commitFile.getCommitId().getSha(), commitFile.getNewFilePath(), commitFile);

		logger.info("< commitFilesTable");
		return commitFilesTable;
	}

	//TODO TEST it
	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#filterByChangeType(java.util.Collection, java.lang.String[])
	 */
	@Override
	public Collection<CommitFiles> filterByChangeType(Collection<CommitFiles> commitFiles, String... changeTypes) {
		logger.info("> filterByChangeType");

		commitFiles = commitFiles.stream().filter(cf -> belongsToChangeType(cf, changeTypes)).collect(Collectors.toList());

		logger.info("< filterByChangeType");
		return commitFiles;
	}

	private boolean belongsToChangeType(CommitFiles cf, String[] changeTypes) {
		for (String changeType : changeTypes)
			if (cf.getChangeType().equals(changeType))
				return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#findByProjectId(com.digkas.git.domain.Project)
	 */
	@Override
	public Collection<CommitFiles> findByProjectId(Project projectId) {
		logger.info("> findByProjectId projectId: {}", projectId);

		Collection<CommitFiles> commitFiles = commitFilesRepository.findByProjectId(projectId);

		logger.info("< findByProjectId projectId: {}", projectId);
		return commitFiles;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#findByCommit(com.digkas.git.domain.Commit)
	 */
	@Override
	public Collection<CommitFiles> findByCommit(Commit commitId) {
		logger.info("> findByCommit: {}", commitId);

		Collection<CommitFiles> commitFiles = commitFilesRepository.findByCommit(commitId);

		logger.info("< findByCommit: {}", commitId);
		return commitFiles;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitFilesService#findByCommitIdANDNewFilePathANDChangeType(com.digkas.git.domain.Commit, java.lang.String, java.lang.String)
	 */
	@Override
	public CommitFiles findByCommitIdANDNewFilePathANDChangeType(Commit commitId, String newFilePath, String changeType) {
		logger.info("> findByCommitIdANDNewFilePathANDChangeType: {}, {}, {}", commitId, newFilePath, changeType);

		CommitFiles commitFile = commitFilesRepository.findByCommitIdANDNewFilePathANDChangeType(commitId, newFilePath, changeType);

		logger.info("< findByCommitIdANDNewFilePathANDChangeType: {}, {}, {}", commitId, newFilePath, changeType);
		return commitFile;
	}

}
