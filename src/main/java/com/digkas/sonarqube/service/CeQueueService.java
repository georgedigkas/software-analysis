/**
 * 
 */
package com.digkas.sonarqube.service;

import java.util.Collection;

import com.digkas.sonarqube.domain.CeQueue;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public interface CeQueueService {

	Collection<CeQueue> findAll();

	CeQueue findOne(Integer id);

	CeQueue create(CeQueue ceQueue);

	CeQueue update(CeQueue ceQueue);

	void delete(Integer id);

}
