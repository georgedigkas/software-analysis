
package com.digkas.sonarqube.api.issues.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Component {

	@SerializedName("organization")
	@Expose
	private String organization;
	@SerializedName("key")
	@Expose
	private String key;
	@SerializedName("uuid")
	@Expose
	private String uuid;
	@SerializedName("enabled")
	@Expose
	private Boolean enabled;
	@SerializedName("qualifier")
	@Expose
	private String qualifier;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("longName")
	@Expose
	private String longName;
	@SerializedName("path")
	@Expose
	private String path;

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
