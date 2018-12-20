package com.digkas.sonarqube.api.metrics.search;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchMetrics {

	@SerializedName("metrics")
	@Expose
	private List<Metric> metrics = null;
	@SerializedName("total")
	@Expose
	private Integer total;
	@SerializedName("p")
	@Expose
	private Integer p;
	@SerializedName("ps")
	@Expose
	private Integer ps;

	public List<Metric> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<Metric> metrics) {
		this.metrics = metrics;
	}

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

}
