package com.digkas.sonarqube.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity
@Table(name = "project_mappings")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "ProjectMappings.findAll", query = "SELECT p FROM ProjectMappings p"),
	@NamedQuery(name = "ProjectMappings.findByUuid", query = "SELECT p FROM ProjectMappings p WHERE p.uuid = :uuid"),
	@NamedQuery(name = "ProjectMappings.findByKeyType", query = "SELECT p FROM ProjectMappings p WHERE p.keyType = :keyType"),
	@NamedQuery(name = "ProjectMappings.findByKee", query = "SELECT p FROM ProjectMappings p WHERE p.kee = :kee"),
	@NamedQuery(name = "ProjectMappings.findByProjectUuid", query = "SELECT p FROM ProjectMappings p WHERE p.projectUuid = :projectUuid"),
	@NamedQuery(name = "ProjectMappings.findByCreatedAt", query = "SELECT p FROM ProjectMappings p WHERE p.createdAt = :createdAt") })
public class ProjectMappings implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "uuid")
	private String uuid;
	@Basic(optional = false)
	@Column(name = "key_type")
	private String keyType;
	@Basic(optional = false)
	@Column(name = "kee")
	private String kee;
	@Basic(optional = false)
	@Column(name = "project_uuid")
	private String projectUuid;
	@Basic(optional = false)
	@Column(name = "created_at")
	private long createdAt;

	public ProjectMappings() {
	}

	public ProjectMappings(String uuid) {
		this.uuid = uuid;
	}

	public ProjectMappings(String uuid, String keyType, String kee, String projectUuid, long createdAt) {
		this.uuid = uuid;
		this.keyType = keyType;
		this.kee = kee;
		this.projectUuid = projectUuid;
		this.createdAt = createdAt;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getKee() {
		return kee;
	}

	public void setKee(String kee) {
		this.kee = kee;
	}

	public String getProjectUuid() {
		return projectUuid;
	}

	public void setProjectUuid(String projectUuid) {
		this.projectUuid = projectUuid;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (uuid != null ? uuid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ProjectMappings)) {
			return false;
		}
		ProjectMappings other = (ProjectMappings) object;
		if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.sonarqube.domain.ProjectMappings[ uuid=" + uuid + " ]";
	}

}
