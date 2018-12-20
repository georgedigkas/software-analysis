/**
 * 
 */
package com.digkas.sonarqube.api.ce.component;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public class Queue {

	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("type")
	@Expose
	private String type;
	@SerializedName("componentId")
	@Expose
	private String componentId;
	@SerializedName("componentKey")
	@Expose
	private String componentKey;
	@SerializedName("componentName")
	@Expose
	private String componentName;
	@SerializedName("componentQualifier")
	@Expose
	private String componentQualifier;
	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("submittedAt")
	@Expose
	private String submittedAt;
	@SerializedName("submitterLogin")
	@Expose
	private String submitterLogin;
	@SerializedName("startedAt")
	@Expose
	private String startedAt;
	@SerializedName("executionTimeMs")
	@Expose
	private Integer executionTimeMs;
	@SerializedName("logs")
	@Expose
	private Boolean logs;
	@SerializedName("organization")
	@Expose
	private String organization;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getComponentKey() {
		return componentKey;
	}

	public void setComponentKey(String componentKey) {
		this.componentKey = componentKey;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getComponentQualifier() {
		return componentQualifier;
	}

	public void setComponentQualifier(String componentQualifier) {
		this.componentQualifier = componentQualifier;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubmittedAt() {
		return submittedAt;
	}

	public void setSubmittedAt(String submittedAt) {
		this.submittedAt = submittedAt;
	}

	public String getSubmitterLogin() {
		return submitterLogin;
	}

	public void setSubmitterLogin(String submitterLogin) {
		this.submitterLogin = submitterLogin;
	}

	public String getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(String startedAt) {
		this.startedAt = startedAt;
	}

	public Integer getExecutionTimeMs() {
		return executionTimeMs;
	}

	public void setExecutionTimeMs(Integer executionTimeMs) {
		this.executionTimeMs = executionTimeMs;
	}

	public Boolean getLogs() {
		return logs;
	}

	public void setLogs(Boolean logs) {
		this.logs = logs;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

}
