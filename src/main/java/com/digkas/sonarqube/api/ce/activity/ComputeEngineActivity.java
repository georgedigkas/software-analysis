/**
 * 
 */
package com.digkas.sonarqube.api.ce.activity;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public class ComputeEngineActivity {

	@SerializedName("tasks")
	@Expose
	private List<Task> tasks = null;

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
