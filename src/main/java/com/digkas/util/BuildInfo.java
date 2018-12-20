/**
 * 
 */
package com.digkas.util;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public enum BuildInfo {

	/** BUILD SUCCESS */
	SUCCESS("BUILD SUCCESS"),
	/** BUILD FAILURE */
	FAILURE("BUILD FAILURE");

	private String buildText;

	BuildInfo(String buildText) {
		this.buildText = buildText;
	}

	public String getBuildText() {
		return buildText;
	}

	public void setBuildText(String buildText) {
		this.buildText = buildText;
	}

}
