/**
 * 
 */
package com.digkas.sonarqube.service;

import java.util.Collection;

import com.digkas.sonarqube.domain.Metrics;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public interface MetricsService {

	Collection<Metrics> findAll();

	Metrics findOne(Integer id);

	Metrics create(Metrics metric);

	Metrics update(Metrics metric);

	void delete(Integer id);

	Metrics findByName(String name);

}
