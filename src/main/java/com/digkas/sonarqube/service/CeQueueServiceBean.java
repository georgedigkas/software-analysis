/**
 * 
 */
package com.digkas.sonarqube.service;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digkas.sonarqube.domain.CeQueue;
import com.digkas.sonarqube.persistence.CeQueueRepository;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Service
public class CeQueueServiceBean implements CeQueueService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CeQueueRepository ceQueueRepository;

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.CeQueueService#findAll()
	 */
	@Override
	public Collection<CeQueue> findAll() {
		logger.info("> findAll");

		List<CeQueue> ceQueues = ceQueueRepository.findAll();

		logger.info("< findAll");
		return ceQueues;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.CeQueueService#findOne(java.lang.Integer)
	 */
	@Override
	public CeQueue findOne(Integer id) {
		logger.info("> findOne id: {}", id);

		CeQueue ceQueue = ceQueueRepository.findOne(id);

		logger.info("< findOne id: {}", id);
		return ceQueue;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.CeQueueService#create(com.digkas.sonarqube.domain.CeQueue)
	 */
	@Override
	public CeQueue create(CeQueue ceQueue) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.CeQueueService#update(com.digkas.sonarqube.domain.CeQueue)
	 */
	@Override
	public CeQueue update(CeQueue ceQueue) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.service.CeQueueService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

}
