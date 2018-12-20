/**
 * 
 */
package com.digkas.sonarqube.service;

import java.math.BigInteger;
import java.util.Collection;

import com.digkas.sonarqube.domain.Issues;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public interface IssuesService {

	Collection<Issues> findAll();

	Issues findOne(Long id);

	Issues create(Issues issue);

	Issues update(Issues issue);

	void delete(Long id);

	Collection<Issues> findByProjectUuid(String projectUuid);

	Collection<Issues> findByProjectUuidLineNotNull(String projectUuid);

	Collection<Issues> findClosedByIssueCloseDateProjectUuid(BigInteger issueCloseDate, String projectUuid);

	Collection<Issues> findIssuesCreatedSoFar(BigInteger issueCreationDate, String projectUuid);

	Collection<Issues> findByStatusANDProjectUuid(String status, String projectUuid);

	Collection<Issues> findOpenIssuesByProjectUuid(String projectUuid);

	Collection<Issues> findCloasedIssuesByProjectUuid(String projectUuid);

}
