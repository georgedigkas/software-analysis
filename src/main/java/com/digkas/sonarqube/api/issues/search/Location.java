
package com.digkas.sonarqube.api.issues.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

	@SerializedName("textRange")
	@Expose
	private TextRange_ textRange;
	@SerializedName("msg")
	@Expose
	private String msg;

	public TextRange_ getTextRange() {
		return textRange;
	}

	public void setTextRange(TextRange_ textRange) {
		this.textRange = textRange;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
