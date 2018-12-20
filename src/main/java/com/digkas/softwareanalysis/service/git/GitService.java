/**
 * 
 */
package com.digkas.softwareanalysis.service.git;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public interface GitService {

	void gitClone(String uri);

	void gitCheckout(String sha);

}
