/**
 * 
 */
package com.digkas.util;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.digkas.git.domain.CommitFiles;
import com.digkas.sonarqube.domain.Projects;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public final class SonarQubeUtils {

	public static String getPath(Projects component, Projects module) {
		String componentLongName = null;

		if (Objects.isNull(module) || Objects.isNull(module.getPath()))
			componentLongName = component.getLongName();
		else
			componentLongName = component.getKee().replace((module.getKee() + ":"), (module.getPath() + "/"));

		return componentLongName;
	}

	private static List<CommitFiles> filterOutTestFiles(List<CommitFiles> commitFiles, Set<String> sonarqubeNonTestFiles) {
		return commitFiles.stream().filter(commitFile -> sonarqubeNonTestFiles.contains(commitFile.getNewFilePath())).collect(Collectors.toList());
	}
}
