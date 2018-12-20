/**
 * 
 */
package com.digkas.sonarqube.api.ce.component.service;

import com.digkas.sonarqube.api.ce.component.ComputeEngineComponent;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public interface ComputeEngineComponentService {

	ComputeEngineComponent findComputeEngineComponent(String component) throws UnirestException;

	boolean isQueueEmpty(String component) throws UnirestException;

}
