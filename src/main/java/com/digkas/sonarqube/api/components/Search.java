
package com.digkas.sonarqube.api.components;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public class Search {

	@SerializedName("paging")
	@Expose
	private Paging paging;
	@SerializedName("components")
	@Expose
	private List<Component> components = null;

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}

}
