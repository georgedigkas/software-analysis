/**
 * 
 */
package com.digkas.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public final class DetectCharsetEncoding {

	private static String detectedCharset(InputStream input) throws IOException {
		try(BufferedInputStream bis = new BufferedInputStream(input)) {
			CharsetDetector cd = new CharsetDetector();
			cd.setText(bis);
			CharsetMatch cm = cd.detect();

			//Reader reader = null;
			String charset = null;
			if (cm != null) {
				//reader = cm.getReader();
				charset = cm.getName();
			}
			return charset;
		}
	}

	public static String detectedCharset(String name) throws IOException {
		try(FileInputStream input = new FileInputStream(name)) {
			return detectedCharset(input);
		}
	}

}
