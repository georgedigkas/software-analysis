/**
 * 
 */
package com.digkas.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public class StreamGobbler extends Thread {

	InputStream is;
	String type;
	BuildInfo build;

	StreamGobbler(InputStream is, String type) {
		this.is = is;
		this.type = type;
		this.build = null;
	}

	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(type + ">" + line);
				if (line.contains(BuildInfo.SUCCESS.getBuildText())) build = BuildInfo.SUCCESS;
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public BuildInfo getBuild() {
		return build;
	}

	public void setBuild(BuildInfo build) {
		this.build = build;
	}

}
