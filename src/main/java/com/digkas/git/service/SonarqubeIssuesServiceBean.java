/**
 * 
 */
package com.digkas.git.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digkas.git.domain.Commit;
import com.digkas.git.domain.Method;
import com.digkas.git.domain.Project;
import com.digkas.git.domain.SonarqubeIssues;
import com.digkas.git.persistence.SonarqubeIssuesRepository;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Service
public class SonarqubeIssuesServiceBean implements SonarqubeIssuesService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SonarqubeIssuesRepository sonarqubeIssuesRepository;

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findAll()
	 */
	@Override
	public Collection<SonarqubeIssues> findAll() {
		logger.info("> findAll");

		Collection<SonarqubeIssues> sonarqubeIssues = sonarqubeIssuesRepository.findAll();

		logger.info("< findAll");
		return sonarqubeIssues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findOne(java.lang.Integer)
	 */
	@Override
	public SonarqubeIssues findOne(Integer id) {
		logger.info("> findOne id:{}", id);

		SonarqubeIssues sonarqubeIssue = sonarqubeIssuesRepository.findOne(id);

		logger.info("< findOne id:{}", id);
		return sonarqubeIssue;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#create(com.digkas.git.domain.SonarqubeIssues)
	 */
	@Override
	public SonarqubeIssues create(SonarqubeIssues sonarqubeIssue) {
		logger.info("> create SonarqubeIssues");

		SonarqubeIssues savedSonarqubeIssue = sonarqubeIssuesRepository.save(sonarqubeIssue);

		logger.info("< create SonarqubeIssues");
		return savedSonarqubeIssue;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#create(java.util.List)
	 */
	@Override
	public List<SonarqubeIssues> create(List<SonarqubeIssues> sonarqubeIssues) {
		logger.info("> create SonarqubeIssues");

		List<SonarqubeIssues> savedSonarqubeIssues = sonarqubeIssuesRepository.save(sonarqubeIssues);

		logger.info("< create SonarqubeIssues");
		return savedSonarqubeIssues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#update(com.digkas.git.domain.SonarqubeIssues)
	 */
	@Override
	public SonarqubeIssues update(SonarqubeIssues sonarqubeIssue) {
		logger.info("> update SonarqubeIssue id: {}", sonarqubeIssue.getId());

		SonarqubeIssues sonarqubeIssueToUpdate = findOne(sonarqubeIssue.getId());
		if (Objects.isNull(sonarqubeIssueToUpdate))
			return null;

		SonarqubeIssues updatedSonarqubeIssue = sonarqubeIssuesRepository.save(sonarqubeIssue);

		logger.info("< update SonarqubeIssue id: {}", sonarqubeIssue.getId());
		return updatedSonarqubeIssue;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#update(java.util.List)
	 */
	@Override
	public List<SonarqubeIssues> update(List<SonarqubeIssues> sonarqubeIssues) {
		logger.info("> update SonarqubeIssuess");

		List<Integer> sonarqubeIssueIDs = sonarqubeIssues.stream().map(SonarqubeIssues::getId).collect(Collectors.toList());
		Collection<SonarqubeIssues> sonarqubeIssuesThatAlreadyExistInTheDatabase = this.findByIds(sonarqubeIssueIDs);
		List<Integer> sonarqubeIssuesIDsThatAlreadyExistInTheDatabase = sonarqubeIssuesThatAlreadyExistInTheDatabase.stream().map(SonarqubeIssues::getId).collect(Collectors.toList());
		List<SonarqubeIssues> sonarqubeIssuesToBeUpdated = sonarqubeIssues.parallelStream().filter(c -> sonarqubeIssuesIDsThatAlreadyExistInTheDatabase.contains(c.getId())).collect(Collectors.toList());
		System.out.println("After sonarqubeIssuesToBeUpdated");
		List<SonarqubeIssues> updatedSonarqubeIssuess = sonarqubeIssuesRepository.save(sonarqubeIssuesToBeUpdated);

		logger.info("< update SonarqubeIssuess");
		return updatedSonarqubeIssuess;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		logger.info("> delete id: {}", id);

		sonarqubeIssuesRepository.delete(id);

		logger.info("< delete id: {}", id);
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#delete(java.lang.Iterable)
	 */
	@Override
	public void delete(Iterable<SonarqubeIssues> sonarqubeIssues) {
		logger.info("> delete Iterable");

		sonarqubeIssuesRepository.deleteInBatch(sonarqubeIssues);

		logger.info("< delete Iterable");
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#deleteByCommitId(com.digkas.git.domain.Commit)
	 */
	@Override
	public void deleteByCommitId(Commit commitId) {
		logger.info("> deleteByCommitId: {}", commitId);

		this.delete(this.findByCommitId(commitId));

		logger.info("< deleteByCommitId: {}", commitId);
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#deleteByCommitId(int)
	 */
	@Override
	public void deleteByCommitId(int id) {
		logger.info("> deleteByCommitId id: {}", id);

		this.delete(this.findByCommitId(id));

		logger.info("< deleteByCommitId id: {}", id);
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#deleteByCommitId(java.lang.String)
	 */
	@Override
	public void deleteByCommitId(String sha) {
		logger.info("> deleteByCommitId sha: {}", sha);

		sonarqubeIssuesRepository.deleteByCommitId(sha);

		logger.info("< deleteByCommitId sha: {}", sha);
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#deleteByCommitIdJPQL(com.digkas.git.domain.Commit)
	 */
	@Override
	public int deleteByCommitIdJPQL(Commit commitId) {
		logger.info("> deleteByCommitIdJPQL: {}", commitId);

		int deletedCount = sonarqubeIssuesRepository.deleteByCommitId(commitId);

		logger.info("< deleteByCommitIdJPQL: {}", commitId);
		return deletedCount;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#deleteByCommitIdJPQL(int)
	 */
	@Override
	public int deleteByCommitIdJPQL(int id) {
		logger.info("> deleteByCommitIdJPQL: {}", id);

		int deletedCount = sonarqubeIssuesRepository.deleteByCommitId(id);

		logger.info("< deleteByCommitIdJPQL: {}", id);
		return deletedCount;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findByIds(java.util.Collection)
	 */
	@Override
	public Collection<SonarqubeIssues> findByIds(Collection<Integer> ids) {
		logger.info("> findByIds");

		Collection<SonarqubeIssues> sonarqubeIssues = sonarqubeIssuesRepository.findByIds(ids);

		logger.info("< findByIds");
		return sonarqubeIssues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findByProject(com.digkas.git.domain.Project)
	 */
	@Override
	public Collection<SonarqubeIssues> findByProject(Project project) {
		logger.info("> findByProject projectId: {}", project);

		Collection<SonarqubeIssues> sonarqubeIssues = sonarqubeIssuesRepository.findByProject(project);

		logger.info("< findByProject projectId: {}", project);
		return sonarqubeIssues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#groupByCommit(java.util.Collection)
	 */
	@Override
	public ConcurrentMap<Commit, List<SonarqubeIssues>> groupByCommit(Collection<SonarqubeIssues> sonarqubeIssues) {
		logger.info("> groupByCommit");

		ConcurrentMap<Commit, List<SonarqubeIssues>> sonarqubeIssuesMap = sonarqubeIssues.parallelStream().collect(Collectors.groupingByConcurrent(SonarqubeIssues::getCommitId));

		logger.info("< groupByCommit");
		return sonarqubeIssuesMap;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#groupBySHA(java.util.Collection)
	 */
	@Override
	public ConcurrentMap<String, List<SonarqubeIssues>> groupBySHA(Collection<SonarqubeIssues> sonarqubeIssues) {
		logger.info("> groupBySHA");

		ConcurrentMap<String, List<SonarqubeIssues>> sonarqubeIssuesMap = sonarqubeIssues.parallelStream().collect(Collectors.groupingByConcurrent(sonarqubeIssue -> sonarqubeIssue.getCommitId().getSha()));

		logger.info("< groupBySHA");
		return sonarqubeIssuesMap;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findAnalyzedCommitsByProject(com.digkas.git.domain.Project)
	 */
	@Override
	public Collection<Commit> findAnalyzedCommitsByProject(Project project) {
		logger.info("> findAnalyzedCommitsByProject projectId: {}", project);

		Set<Commit> commits = sonarqubeIssuesRepository.findByProject(project).stream().map(SonarqubeIssues::getCommitId).collect(Collectors.toSet());

		logger.info("< findAnalyzedCommitsByProject projectId: {}", project);
		return commits;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findByPathAndSHA(java.lang.String, java.lang.String)
	 */
	@Override
	public Collection<SonarqubeIssues> findByPathAndSHA(String path, String sha) {
		logger.info("> findByPathAndSHA path: {} AND sha: {}", path, sha);

		Collection<SonarqubeIssues> sonarqubeIssues = sonarqubeIssuesRepository.findByPathAndSHA(path, sha);

		logger.info("< findByPathAndSHA path: {} AND sha: {}", path, sha);
		return sonarqubeIssues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findByCommitId(int)
	 */
	@Override
	public Collection<SonarqubeIssues> findByCommitId(int id) {
		logger.info("> findByCommitId id: {}", id);

		Collection<SonarqubeIssues> sonarqubeIssues = sonarqubeIssuesRepository.findByCommitId(id);

		logger.info("< findByCommitId id: {}", id);
		return sonarqubeIssues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findByCommitId(com.digkas.git.domain.Commit)
	 */
	@Override
	public Collection<SonarqubeIssues> findByCommitId(Commit commitId) {
		logger.info("> findByCommitId: {}", commitId);

		Collection<SonarqubeIssues> sonarqubeIssues = sonarqubeIssuesRepository.findByCommitId(commitId);

		logger.info("< findByCommitId: {}", commitId);
		return sonarqubeIssues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findOpenIssuesByCommitId(com.digkas.git.domain.Commit)
	 */
	@Override
	public Collection<SonarqubeIssues> findOpenIssuesByCommitId(Commit commitId) {
		logger.info("> findOpenIssuesByCommitId: {}", commitId);

		Collection<SonarqubeIssues> sonarqubeIssues = sonarqubeIssuesRepository.findOpenIssuesByCommitId(commitId);

		logger.info("< findOpenIssuesByCommitId: {}", commitId);
		return sonarqubeIssues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findOpenSonarqubeIssuesWithNonNullLinesByCommitId(com.digkas.git.domain.Commit)
	 */
	@Override
	public Collection<SonarqubeIssues> findOpenSonarqubeIssuesWithNonNullLinesByCommitId(Commit commitId) {
		logger.info("> findOpenSonarqubeIssuesWithNonNullLinesByCommitId: {}", commitId);

		Collection<SonarqubeIssues> sonarqubeIssues = sonarqubeIssuesRepository.findOpenSonarqubeIssuesWithNonNullLinesByCommitId(commitId);

		logger.info("< findOpenSonarqubeIssuesWithNonNullLinesByCommitId: {}", commitId);
		return sonarqubeIssues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findOpenSonarqubeIssuesMappedToMethodsWithNonNullLinesByCommitId(com.digkas.git.domain.Commit)
	 */
	@Override
	public Collection<SonarqubeIssues> findOpenSonarqubeIssuesMappedToMethodsWithNonNullLinesByCommitId(Commit commitId) {
		logger.info("> findOpenSonarqubeIssuesMappedToMethodsWithNonNullLinesByCommitId: {}", commitId);

		Collection<SonarqubeIssues> sonarqubeIssues = sonarqubeIssuesRepository.findOpenSonarqubeIssuesMappedToMethodsWithNonNullLinesByCommitId(commitId);

		logger.info("< findOpenSonarqubeIssuesMappedToMethodsWithNonNullLinesByCommitId: {}", commitId);
		return sonarqubeIssues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findOpenSonarqubeIssuesWithNonNullLinesByProject(com.digkas.git.domain.Project)
	 */
	@Override
	public Collection<SonarqubeIssues> findOpenSonarqubeIssuesWithNonNullLinesByProject(Project project) {
		logger.info("> findOpenSonarqubeIssuesWithNonNullLinesByProject: {}", project);

		Collection<SonarqubeIssues> sonarqubeIssues = sonarqubeIssuesRepository.findOpenSonarqubeIssuesWithNonNullLinesByProject(project);

		logger.info("< findOpenSonarqubeIssuesWithNonNullLinesByProject: {}", project);
		return sonarqubeIssues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findByStatusANDProjectId(java.lang.String, com.digkas.git.domain.Project)
	 */
	@Override
	public Collection<SonarqubeIssues> findByStatusANDProjectId(String status, Project projectId) {
		logger.info("> findByStatusANDProjectId status: {}, projectId: {}", status, projectId);

		Collection<SonarqubeIssues> sonarqubeIssues = sonarqubeIssuesRepository.findByStatusANDProjectId(status, projectId);

		logger.info("< findByStatusANDProjectId status: {}, projectId: {}", status, projectId);
		return sonarqubeIssues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findOpenIssuesByProjectUuid(com.digkas.git.domain.Project)
	 */
	@Override
	public Collection<SonarqubeIssues> findOpenIssuesByProjectUuid(Project projectId) {
		logger.info("> findOpenIssuesByProjectUuid projectId: {}", projectId);

		Collection<SonarqubeIssues> sonarqubeIssues = this.findByStatusANDProjectId("OPEN", projectId);

		logger.info("< findOpenIssuesByProjectUuid projectId: {}", projectId);
		return sonarqubeIssues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findSonarqubeIssuesByMethodId(java.util.Collection)
	 */
	@Override
	public Collection<SonarqubeIssues> findByMethodIds(Collection<Integer> methodIds) {
		logger.info("> findByMethodIds");

		Collection<SonarqubeIssues> sonarqubeIssues = sonarqubeIssuesRepository.findByMethodIds(methodIds);

		logger.info("< findByMethodIds");
		return sonarqubeIssues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findByPathANDCommitId(java.lang.String, com.digkas.git.domain.Commit)
	 */
	@Override
	public Collection<SonarqubeIssues> findByPathANDCommitId(String path, Commit commitId) {
		logger.info("> findByPathANDCommitId path: {}, commit: {}", path, commitId.getSha());

		Collection<SonarqubeIssues> sonarqubeIssues = sonarqubeIssuesRepository.findByPathANDCommitId(path, commitId);

		logger.info("< findByPathANDCommitId path: {}, commit: {}", path, commitId.getSha());
		return sonarqubeIssues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findByPathANDCommitIdANDMethodName(java.lang.String, com.digkas.git.domain.Commit, java.lang.String)
	 */
	@Override
	public Collection<SonarqubeIssues> findByPathANDCommitIdANDMethodName(String path, Commit commitId, String methodName) {
		logger.info("> findByPathANDCommitIdANDMethodName path: {}, commit: {}, method: {}", path, commitId.getSha(), methodName);

		Collection<SonarqubeIssues> sonarqubeIssues = sonarqubeIssuesRepository.findByPathANDCommitIdANDMethodName(path, commitId, methodName);

		logger.info("< findByPathANDCommitIdANDMethodName path: {}, commit: {}, method: {}", path, commitId.getSha(), methodName);
		return sonarqubeIssues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findByMethodId(com.digkas.git.domain.Method)
	 */
	@Override
	public Collection<SonarqubeIssues> findByMethodId(Method methodId) {
		logger.info("> findByMethodId: {}", methodId);

		Collection<SonarqubeIssues> sonarqubeIssues = sonarqubeIssuesRepository.findByMethodId(methodId);

		logger.info("< findByMethodId: {}", methodId);
		return sonarqubeIssues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.git.service.SonarqubeIssuesService#findByCommitIdANDkees(com.digkas.git.domain.Commit, java.util.Collection)
	 */
	@Override
	public Collection<SonarqubeIssues> findByCommitIdANDkees(Commit commitId, Collection<String> kees) {
		logger.info("> findByCommitIdANDkees: {} AND ", commitId, kees);

		Collection<SonarqubeIssues> sonarqubeIssues = sonarqubeIssuesRepository.findByCommitIdANDkees(commitId, kees);

		logger.info("< findByCommitIdANDkees: {} AND ", commitId, kees);
		return sonarqubeIssues;
	}

}
