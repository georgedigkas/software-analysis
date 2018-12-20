/**
 * 
 */
package com.digkas.sonarqube.service;

import java.util.Collection;
import java.util.Map;

import com.digkas.sonarqube.domain.Rules;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public interface RulesService {

	Collection<Rules> findAll();

	Rules findOne(Integer id);

	Rules create(Rules rule);

	Rules update(Rules rule);

	void delete(Integer id);
	
	Map<Integer, Rules> findAllMap();

}
