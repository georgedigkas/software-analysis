/**
 * 
 */
package com.digkas.sonarqube.api.ce.component.service;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public abstract class ComputeEngineComponentAbstract {

	private static final int PS = 1000;

	protected String username;
	protected String password;
	protected String protocol;
	protected String ip;
	protected String port;
	protected int ps;
	protected String restURL;

	public ComputeEngineComponentAbstract(String username, String password, String protocol, String ip, String port) {
		this.username = username;
		this.password = password;
		this.protocol = protocol;
		this.ip = ip;
		this.port = port;
		this.ps = PS;
		this.restURL = protocol + ip + ":" + port + "/api/ce/component?component=";
	}

	protected String getUsername() {
		return username;
	}

	protected String getPassword() {
		return password;
	}

	protected String getRestURL() {
		return restURL;
	}



}
