
package com.digkas.sonarqube.api.components;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public class Component {

	@SerializedName("organization")
	@Expose
	private String organization;
	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("key")
	@Expose
	private String key;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("qualifier")
	@Expose
	private String qualifier;
	@SerializedName("language")
	@Expose
	private String language;
	@SerializedName("project")
	@Expose
	private String project;

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

}
