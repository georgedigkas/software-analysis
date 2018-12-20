/**
 * 
 */
package com.digkas.sonarqube.service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digkas.sonarqube.domain.Rules;
import com.digkas.sonarqube.persistence.RulesRepository;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Service
public class RulesServiceBean implements RulesService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	RulesRepository rulesRepository;

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.RulesService#findAll()
	 */
	@Override
	public Collection<Rules> findAll() {
		logger.info("> findAll");

		Collection<Rules> rules = rulesRepository.findAll();

		logger.info("< findAll");
		return rules;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.RulesService#findOne(java.lang.Integer)
	 */
	@Override
	public Rules findOne(Integer id) {
		logger.info("> findOne id: {}", id);

		Rules rule = rulesRepository.findOne(id);

		logger.info("< findOne id: {}", id);
		return rule;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.RulesService#create(com.digkas.softwareanalysis.domain.sonarqube.v671.Rules)
	 */
	@Override
	public Rules create(Rules rule) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.RulesService#update(com.digkas.softwareanalysis.domain.sonarqube.v671.Rules)
	 */
	@Override
	public Rules update(Rules rule) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.RulesService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.sonarqube.v671.RulesService#findAllMap()
	 */
	@Override
	public Map<Integer, Rules> findAllMap() {
		logger.info("> findAllMap");

		Map<Integer, Rules> rulesMap = this.findAll().stream().collect(Collectors.toMap(Rules::getId, Function.identity()));

		logger.info("< findAllMap");
		return rulesMap;
	}

}
