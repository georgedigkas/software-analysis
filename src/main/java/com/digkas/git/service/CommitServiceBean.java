/**
 * 
 */
package com.digkas.git.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digkas.git.domain.Commit;
import com.digkas.git.domain.Project;
import com.digkas.git.persistence.CommitRepository;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Service
public class CommitServiceBean implements CommitService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommitRepository commitRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digkas.softwareanalysis.service.git.CommitService#findAll()
	 */
	@Override
	public Collection<Commit> findAll() {
		logger.info("> findAll");

		Collection<Commit> commits = commitRepository.findAll();

		logger.info("< findAll");
		return commits;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digkas.softwareanalysis.service.git.CommitService#findOne(java.lang.
	 * Integer)
	 */
	@Override
	public Commit findOne(Integer id) {
		logger.info("> findOne id: {}", id);

		Commit commit = commitRepository.findOne(id);

		logger.info("< findOne id: {}", id);
		return commit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digkas.softwareanalysis.service.git.CommitService#create(com.digkas.
	 * softwareanalysis.domain.git.Commit)
	 */
	@Override
	public Commit create(Commit commit) {
		logger.info("> create Commit sha: {}", commit.getSha());

		Commit savedCommit = commitRepository.save(commit);

		logger.info("< create Commit sha: {}", commit.getSha());
		return savedCommit;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.git.CommitService#create(java.util.List)
	 */
	@Override
	public List<Commit> create(List<Commit> commits) {
		logger.info("> create Commits");

		List<Commit> savedCommits = commitRepository.save(commits);

		logger.info("< create Commits");
		return savedCommits;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digkas.softwareanalysis.service.git.CommitService#update(com.digkas.
	 * softwareanalysis.domain.git.Commit)
	 */
	@Override
	public Commit update(Commit commit) {
		logger.info("> update Commit id: {}", commit.getId());

		Commit commitToUpdate = findOne(commit.getId());
		if (commitToUpdate == null)
			return null;

		Commit updatedCommit = commitRepository.save(commit);

		logger.info("< update Commit id: {}", commit.getId());
		return updatedCommit;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.git.CommitService#update(java.util.List)
	 */
	@Override
	public List<Commit> update(List<Commit> commits) {
		logger.info("> update Commits");

		List<Integer> commitIDs = commits.stream().map(Commit::getId).collect(Collectors.toList());
		Collection<Commit> commitsThatAlreadyExistInTheDatabase = this.findByIds(commitIDs);
		List<Integer> commitIDsThatAlreadyExistInTheDatabase = commitsThatAlreadyExistInTheDatabase.stream().map(Commit::getId).collect(Collectors.toList());
		List<Commit> commitsToBeUpdated = commits.stream().filter(c -> commitIDsThatAlreadyExistInTheDatabase.contains(c.getId())).collect(Collectors.toList());

		List<Commit> updatedCommits = commitRepository.save(commitsToBeUpdated);

		logger.info("< update Commits");
		return updatedCommits;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digkas.softwareanalysis.service.git.CommitService#delete(java.lang.
	 * Integer)
	 */
	@Override
	public void delete(Integer id) {
		logger.info("> delete id: {}", id);

		commitRepository.delete(id);

		logger.info("< delete id: {}", id);
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitService#delete(java.lang.Iterable)
	 */
	@Override
	public void delete(Iterable<Commit> commits) {
		logger.info("> delete Iterable");

		commitRepository.deleteInBatch(commits);

		logger.info("< delete Iterable");
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitService#findByIds(java.util.Collection)
	 */
	@Override
	public Collection<Commit> findByIds(Collection<Integer> ids) {
		logger.info("> findByIds");

		Collection<Commit> commits = commitRepository.findByIds(ids);

		logger.info("< findByIds");
		return commits;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitService#findByIdProjectId(com.digkas.git.domain.Project)
	 */
	@Override
	public Collection<Commit> findByIdProjectId(Project projectId) {
		logger.info("> findByIdProjectId projectId: {}", projectId);

		Collection<Commit> commits = commitRepository.findByIdProjectId(projectId);

		logger.info("< findByIdProjectId projectId: {}", projectId);
		return commits;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitService#findByIdProjectKee(java.lang.String)
	 */
	@Override
	public Collection<Commit> findByIdProjectKee(String kee) {
		logger.info("> findByIdProjectKee Kee: {}", kee);

		Collection<Commit> commits = commitRepository.findByIdProjectKee(kee);

		logger.info("< findByIdProjectKee Kee: {}", kee);
		return commits;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.CommitService#findByProjectIdAndSHA(com.digkas.git.domain.Project, java.lang.String)
	 */
	@Override
	public Commit findByProjectIdAndSHA(Project projectId, String sha) {
		logger.info("> findByProjectIdAndSHA projectId: {} and SHA: {}", projectId, sha);

		Commit commit = commitRepository.findByProjectIdAndSHA(projectId, sha);

		logger.info("< findByProjectIdAndSHA projectId: {} and SHA: {}", projectId, sha);
		return commit;
	}

}
