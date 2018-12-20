/**
 * 
 */
package com.digkas.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public final class Utils {

	protected static final Logger logger = LoggerFactory.getLogger(Utils.class);

	// 1.000 millis = 1 second
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String executeCommand(String command) throws IOException, InterruptedException {
		return executeCommand(command, null);
	}

	public static String executeCommand(String command, File directory) throws IOException {
		// https://www.javaworld.com/article/2071275/core-java/when-runtime-exec---won-t.html
		Process p = null;
		StreamGobbler errorGobbler = null;
		StreamGobbler outputGobbler = null;

		logger.info("command: {}, directory: {}", command, directory.getAbsolutePath());

		try {
			p = Runtime.getRuntime().exec(command, null, directory);
			// any error message?
			//StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), "ERROR");
			errorGobbler = new StreamGobbler(p.getErrorStream(), "ERROR");

			// any output?
			//StreamGobbler outputGobbler = new StreamGobbler(p.getInputStream(), "OUTPUT");
			outputGobbler = new StreamGobbler(p.getInputStream(), "OUTPUT");

			// kick them off
			errorGobbler.start();
			outputGobbler.start();

			// any error???
			int exitVal = p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (p != null) {
				p.getOutputStream().close();
				p.getInputStream().close();
				p.getErrorStream().close();
			}
		}
		return Objects.nonNull(outputGobbler.getBuild()) ? outputGobbler.getBuild().getBuildText() : "";
	}

	public static String executeShellScript(File shPath, String... parameters) {
		StringBuilder output = new StringBuilder();

		String[] cmd = { "bash", "-c", shPath.getAbsolutePath() + " " + parametersToString(parameters) };
		try {
			Process p = Runtime.getRuntime().exec(cmd);

			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

			p.destroyForcibly();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return output.toString();
	}

	public static String parametersToString(String... parameters) {
		return stringArrayToString(" ", parameters);
	}

	public static String stringArrayToString(CharSequence delimiter, String... strings) {
		return String.join(delimiter, strings);
	}

	public static boolean serverListening(String host, int port) {
		try (Socket s = new Socket(host, port);) {
			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static <T> T transformJSONResponseToPOJO(HttpResponse<JsonNode> response, Class<T> classOfT) {
		if (response != null) {
			Gson gson = new Gson();
			return gson.fromJson(response.getBody().toString(), classOfT);
		}
		return null;
	}

	public static <T> List<T> reverseList(List<T> list) {
		return Lists.reverse(list);
	}

	public static String getHash(String string) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(string.getBytes());
			return new String(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
