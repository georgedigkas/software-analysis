/**
 * 
 */
package com.digkas.sonarqube.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digkas.sonarqube.domain.CeQueue;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Repository
public interface CeQueueRepository extends JpaRepository<CeQueue, Integer> {

}
