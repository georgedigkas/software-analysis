/**
 * 
 */
package com.digkas.git.service;

import java.util.Collection;

import com.digkas.sonarqube.domain.Events;
import com.digkas.sonarqube.domain.Projects;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public interface DensityPerCommitService {

	class DensityPerCommit {

		private Events event;
		private Integer tdAdded;
		private Integer linesAdded;
		private Double density;

		public DensityPerCommit(Events event, Integer tdAdded, Integer linesAdded) {
			this.event = event;
			this.tdAdded = (tdAdded == 0) ? null : tdAdded;
			this.linesAdded = (linesAdded == 0) ? null : linesAdded;
			this.density = (tdAdded == 0 || linesAdded == 0) ? null : ((double) this.tdAdded / this.linesAdded);
		}

		public Events getEvent() {
			return event;
		}

		public Integer getTdAdded() {
			return tdAdded;
		}

		public Integer getLinesAdded() {
			return linesAdded;
		}

		public Double getDensity() {
			return density;
		}

	}

	Collection<DensityPerCommit> getDensityPerCommitByProject(Projects project);

}
