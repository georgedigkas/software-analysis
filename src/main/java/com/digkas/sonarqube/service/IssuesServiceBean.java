/**
 * 
 */
package com.digkas.sonarqube.service;

import java.math.BigInteger;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digkas.sonarqube.domain.Issues;
import com.digkas.sonarqube.persistence.IssuesRepository;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Service
public class IssuesServiceBean implements IssuesService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IssuesRepository issuesRepository;

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.IssuesService#findAll()
	 */
	@Override
	public Collection<Issues> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.IssuesService#findOne(java.lang.Long)
	 */
	@Override
	public Issues findOne(Long id) {
		logger.info("> findOne id: {}", id);

		Issues issue = issuesRepository.findOne(id);

		logger.info("< findOne id: {}", id);
		return issue;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.IssuesService#create(com.digkas.softwareanalysis.domain.sonarqube.v671.Issues)
	 */
	@Override
	public Issues create(Issues issue) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.IssuesService#update(com.digkas.softwareanalysis.domain.sonarqube.v671.Issues)
	 */
	@Override
	public Issues update(Issues issue) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.IssuesService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.IssuesService#findByProjectUuid(java.lang.String)
	 */
	@Override
	public Collection<Issues> findByProjectUuid(String projectUuid) {
		logger.info("> findByProjectUuid projectUuid: {}", projectUuid);

		Collection<Issues> issues = issuesRepository.findByProjectUuid(projectUuid);

		logger.info("< findByProjectUuid projectUuid: {}", projectUuid);
		return issues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.IssuesService#findByProjectUuidLineNotNull(java.lang.String)
	 */
	@Override
	public Collection<Issues> findByProjectUuidLineNotNull(String projectUuid) {
		logger.info("> findByProjectUuidLineNotNull projectUuid: {}", projectUuid);

		Collection<Issues> issues = issuesRepository.findByProjectUuidLineNotNull(projectUuid);

		logger.info("< findByProjectUuidLineNotNull projectUuid: {}", projectUuid);
		return issues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.IssuesService#findClosedByIssueCloseDateProjectUuid(java.math.BigInteger, java.lang.String)
	 */
	@Override
	public Collection<Issues> findClosedByIssueCloseDateProjectUuid(BigInteger issueCloseDate, String projectUuid) {
		logger.info("> findClosedByIssueCloseDateProjectUuid issueCloseDate: {}, projectUuid: {}", issueCloseDate, projectUuid);

		Collection<Issues> issues = issuesRepository.findClosedByIssueCloseDateProjectUuid(issueCloseDate, projectUuid);

		logger.info("< findClosedByIssueCloseDateProjectUuid issueCloseDate: {}, projectUuid: {}", issueCloseDate, projectUuid);
		return issues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.IssuesService#findIssuesCreatedSoFar(java.math.BigInteger, java.lang.String)
	 */
	@Override
	public Collection<Issues> findIssuesCreatedSoFar(BigInteger issueCreationDate, String projectUuid) {
		logger.info("> findIssuesCreatedSoFar issueCreationDate: {}, projectUuid: {}", issueCreationDate, projectUuid);

		Collection<Issues> issues = issuesRepository.findIssuesCreatedSoFar(issueCreationDate, projectUuid);

		logger.info("< findIssuesCreatedSoFar issueCreationDate: {}, projectUuid: {}", issueCreationDate, projectUuid);
		return issues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.IssuesService#findByStatusANDProjectUuid(java.lang.String, java.lang.String)
	 */
	@Override
	public Collection<Issues> findByStatusANDProjectUuid(String status, String projectUuid) {
		logger.info("> findByStatusANDProjectUuid status: {}, projectUuid: {}", status, projectUuid);

		Collection<Issues> issues = issuesRepository.findByStatusANDProjectUuid(status, projectUuid);

		logger.info("< findByStatusANDProjectUuid status: {}, projectUuid: {}", status, projectUuid);

		return issues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.IssuesService#findOpenIssuesByProjectUuid(java.lang.String)
	 */
	@Override
	public Collection<Issues> findOpenIssuesByProjectUuid(String projectUuid) {
		logger.info("> findOpenIssuesByProjectUuid status: {}", projectUuid);

		Collection<Issues> issues = this.findByStatusANDProjectUuid("OPEN", projectUuid);

		logger.info("< findOpenIssuesByProjectUuid status: {}", projectUuid);
		return issues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.IssuesService#findCloasedIssuesByProjectUuid(java.lang.String)
	 */
	@Override
	public Collection<Issues> findCloasedIssuesByProjectUuid(String projectUuid) {
		logger.info("> findCloasedIssuesByProjectUuid status: {}", projectUuid);

		Collection<Issues> issues = this.findByStatusANDProjectUuid("CLOSED", projectUuid);

		logger.info("< findCloasedIssuesByProjectUuid status: {}", projectUuid);
		return issues;
	}

}
