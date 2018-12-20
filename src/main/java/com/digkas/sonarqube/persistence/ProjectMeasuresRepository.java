package com.digkas.sonarqube.persistence;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digkas.sonarqube.domain.ProjectMeasures;

/**
 * @author George Digkas <digasgeo@gmail.com>
 * 
 */
@Repository
public interface ProjectMeasuresRepository extends JpaRepository<ProjectMeasures, Long> {

	@Query("SELECT p FROM ProjectMeasures p WHERE p.componentUuid = :componentUuid")
	Collection<ProjectMeasures> findByComponentUuid(@Param("componentUuid") String componentUuid);

	@Query("SELECT p FROM ProjectMeasures p WHERE p.componentUuid = :componentUuid AND p.metricId = :metricId")
	Collection<ProjectMeasures> findByComponentUuidMetricId(@Param("componentUuid") String componentUuid, @Param("metricId") int metricId);

	@Query("SELECT p FROM ProjectMeasures p WHERE p.componentUuid IN :componentUuids AND p.analysisUuid = :analysisUuid")
	Collection<ProjectMeasures> findByComponentUuidsAndAnalysisUuid(@Param("componentUuids") Collection<String> componentUuids, @Param("analysisUuid") String analysisUuid);

}
