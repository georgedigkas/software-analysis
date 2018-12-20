/**
 * 
 */
package com.digkas.sonarqube.api.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Component
public class DeleteProject {

	private static final String PROTOCOL = "http://";
	private static final String IP = "localhost";
	private static final String PORT = "9000";
	private static final String WEB_API = "/api/projects/delete?project=";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";

	private String protocol;
	private String ip;
	private String port;
	private String restUrl;
	private String username;
	private String password;

	public DeleteProject(@Value("#{sonarqube.http.protocol}") String protocol, @Value("#{sonarqube.ip}") String ip, @Value("#{sonarqube.port}") String port) {
		this.protocol = protocol;
		this.ip = ip;
		this.port = port;
		this.restUrl = this.protocol + this.ip + ":" + this.port + WEB_API;
	}

	public DeleteProject(@Value("#{sonarqube.ip}") String ip, @Value("#{sonarqube.port}") String port) {
		this.protocol = PROTOCOL;
		this.ip = ip;
		this.port = port;
		this.restUrl = this.protocol + this.ip + ":" + this.port + WEB_API;
	}

	public DeleteProject(@Value("#{sonarqube.ip}") String ip) {
		this.protocol = PROTOCOL;
		this.ip = ip;
		this.port = PORT;
		this.restUrl = this.protocol + this.ip + ":" + this.port + WEB_API;
	}

	@Autowired
	public DeleteProject(Environment env) {
		this.protocol = env.getRequiredProperty("sonarqube.http.protocol");
		this.ip = env.getRequiredProperty("sonarqube.ip");
		this.port = env.getRequiredProperty("sonarqube.port");
		this.restUrl = this.protocol + this.ip + ":" + this.port + WEB_API;
	}

	public String getUsername() {
		return username;
	}

	public DeleteProject setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public DeleteProject setPassword(String password) {
		this.password = password;
		return this;
	}

	//



	//
	public HttpResponse<JsonNode> deleteProject(String project) throws UnirestException {
		return Unirest.post(restUrl + project).basicAuth(getUsername(), getPassword()).asJson();
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getRestUrl() {
		return restUrl;
	}

	public void setRestUrl(String restUrl) {
		this.restUrl = restUrl;
	}


}
