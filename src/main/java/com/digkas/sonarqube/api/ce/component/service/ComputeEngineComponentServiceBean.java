/**
 * 
 */
package com.digkas.sonarqube.api.ce.component.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.digkas.sonarqube.api.ce.component.ComputeEngineComponent;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Service
public class ComputeEngineComponentServiceBean extends ComputeEngineComponentAbstract implements ComputeEngineComponentService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public ComputeEngineComponentServiceBean(@Value("${sonarqube.username}") String username, @Value("${sonarqube.password}") String password, @Value("${sonarqube.http.protocol}") String protocol, @Value("${sonarqube.ip}") String ip, @Value("${sonarqube.port}") String port) {
		super(username, password, protocol, ip, port);
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.api.ce.component.service.ComputeEngineComponentService#findComputeEngineComponent(java.lang.String)
	 */
	@Override
	public ComputeEngineComponent findComputeEngineComponent(String component) throws UnirestException {
		logger.info("> findComputeEngineComponent component: {}", component);

		Gson gson = new Gson();
		HttpResponse<JsonNode> httpResponse = Unirest.get(this.getRestURL() + component).basicAuth(this.getUsername(), this.getPassword()).asJson();

		logger.info("< findComputeEngineComponent component: {}", component);
		return gson.fromJson(httpResponse.getBody().toString(), ComputeEngineComponent.class);
	}

	/* (non-Javadoc)
	 * @see com.digkas.sonarqube.api.ce.component.service.ComputeEngineComponentService#isQueueEmpty(java.lang.String)
	 */
	@Override
	public boolean isQueueEmpty(String component) throws UnirestException {
		logger.info("isQueueEmpty component: {}", component);
		return this.findComputeEngineComponent(component).getQueue().isEmpty();
	}

}
