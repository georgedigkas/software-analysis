/**
 * 
 */
package com.digkas.sonarqube.persistence;

import java.math.BigInteger;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digkas.sonarqube.domain.Issues;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Repository
public interface IssuesRepository extends JpaRepository<Issues, Long> {

	@Query("SELECT i FROM Issues i WHERE i.projectUuid = :projectUuid")
	Collection<Issues> findByProjectUuid(@Param("projectUuid") String projectUuid);

	@Query("SELECT i FROM Issues i WHERE i.line IS NOT NULL AND i.projectUuid = :projectUuid")
	Collection<Issues> findByProjectUuidLineNotNull(@Param("projectUuid") String projectUuid);

	@Query("SELECT i FROM Issues i WHERE i.issueCloseDate = :issueCloseDate AND i.projectUuid = :projectUuid")
	Collection<Issues> findClosedByIssueCloseDateProjectUuid(@Param("issueCloseDate") BigInteger issueCloseDate, @Param("projectUuid") String projectUuid);

	@Query("SELECT i FROM Issues i WHERE i.issueCreationDate <= :issueCreationDate AND i.projectUuid = :projectUuid")
	Collection<Issues> findIssuesCreatedSoFar(@Param("issueCreationDate") BigInteger issueCreationDate, @Param("projectUuid") String projectUuid);

	@Query("SELECT i FROM Issues i WHERE i.status = :status AND i.projectUuid = :projectUuid ")
	Collection<Issues> findByStatusANDProjectUuid(@Param("status") String status, @Param("projectUuid") String projectUuid);

}
