package com.digkas.sonarqube.api.metrics.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metric {

	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("key")
	@Expose
	private String key;
	@SerializedName("type")
	@Expose
	private String type;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("description")
	@Expose
	private String description;
	@SerializedName("domain")
	@Expose
	private String domain;
	@SerializedName("direction")
	@Expose
	private Integer direction;
	@SerializedName("qualitative")
	@Expose
	private Boolean qualitative;
	@SerializedName("hidden")
	@Expose
	private Boolean hidden;
	@SerializedName("custom")
	@Expose
	private Boolean custom;
	@SerializedName("decimalScale")
	@Expose
	private Integer decimalScale;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Boolean getQualitative() {
		return qualitative;
	}

	public void setQualitative(Boolean qualitative) {
		this.qualitative = qualitative;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public Boolean getCustom() {
		return custom;
	}

	public void setCustom(Boolean custom) {
		this.custom = custom;
	}

	public Integer getDecimalScale() {
		return decimalScale;
	}

	public void setDecimalScale(Integer decimalScale) {
		this.decimalScale = decimalScale;
	}

	@Override
	public String toString() {
		return id + "\t" + key + "\t" + name + "\t" + description + "\t" + domain;
	}

}
