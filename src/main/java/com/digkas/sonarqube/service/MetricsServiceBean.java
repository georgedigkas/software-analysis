/**
 * 
 */
package com.digkas.sonarqube.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digkas.sonarqube.domain.Metrics;
import com.digkas.sonarqube.persistence.MetricsRepository;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Service
public class MetricsServiceBean implements MetricsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MetricsRepository metricsRepository;

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.MetricsService#findAll()
	 */
	@Override
	public Collection<Metrics> findAll() {
		logger.info("> findAll");

		Collection<Metrics> metrics = metricsRepository.findAll();

		logger.info("< findAll");
		return metrics;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.MetricsService#findOne(java.lang.Integer)
	 */
	@Override
	public Metrics findOne(Integer id) {
		logger.info("> findOne id: {}", id);

		Metrics metric = metricsRepository.findOne(id);

		logger.info("< findOne id: {}", id);
		return metric;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.MetricsService#create(com.digkas.softwareanalysis.domain.sonarqube.v671.Metrics)
	 */
	@Override
	public Metrics create(Metrics metric) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.MetricsService#update(com.digkas.softwareanalysis.domain.sonarqube.v671.Metrics)
	 */
	@Override
	public Metrics update(Metrics metric) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.MetricsService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		logger.info("> delete Metric id: {}", id);

		metricsRepository.delete(id);

		logger.info("< delete Metric id: {}", id);
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.MetricsService#findByName(java.lang.String)
	 */
	@Override
	public Metrics findByName(String name) {
		logger.info("> findByName name: {}", name);

		Metrics metric = metricsRepository.findByName(name);

		logger.info("< findByName name: {}", name);
		return metric;
	}

}
