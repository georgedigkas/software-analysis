/**
 * 
 */
package com.digkas.sonarqube.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digkas.sonarqube.domain.Metrics;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Repository
public interface MetricsRepository extends JpaRepository<Metrics, Integer> {

	@Query("SELECT m FROM Metrics m WHERE m.name = :name")
	Metrics findByName(@Param("name") String name);

}
