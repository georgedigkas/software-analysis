/**
 * 
 */
package com.digkas.sonarqube.persistence;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digkas.sonarqube.domain.Events;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Repository
public interface EventsRepository extends JpaRepository<Events, Integer> {

	@Query("SELECT e FROM Events e WHERE e.category = 'Version' AND e.analysisUuid IN :analysisUuids")
	Collection<Events> findVersionsByAnalysisUuid(@Param("analysisUuids") Collection<String> analysisUuids);

}
