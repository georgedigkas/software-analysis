
package com.digkas.sonarqube.api.issues.search;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchIssues {

	@SerializedName("total")
	@Expose
	private Integer total;
	@SerializedName("p")
	@Expose
	private Integer p;
	@SerializedName("ps")
	@Expose
	private Integer ps;
	@SerializedName("paging")
	@Expose
	private Paging paging;
	@SerializedName("issues")
	@Expose
	private List<Issue> issues = null;
	@SerializedName("components")
	@Expose
	private List<Component> components = null;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getP() {
		return p;
	}

	public void setP(Integer p) {
		this.p = p;
	}

	public Integer getPs() {
		return ps;
	}

	public void setPs(Integer ps) {
		this.ps = ps;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}

}
