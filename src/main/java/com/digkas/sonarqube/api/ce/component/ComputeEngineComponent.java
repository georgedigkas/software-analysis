/**
 * 
 */
package com.digkas.sonarqube.api.ce.component;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public class ComputeEngineComponent {

	@SerializedName("queue")
	@Expose
	private List<Queue> queue = null;
	@SerializedName("current")
	@Expose
	private Current current;

	public List<Queue> getQueue() {
		return queue;
	}

	public void setQueue(List<Queue> queue) {
		this.queue = queue;
	}

	public Current getCurrent() {
		return current;
	}

	public void setCurrent(Current current) {
		this.current = current;
	}

}
