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
@Table(name = "project_links")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "ProjectLinks.findAll", query = "SELECT p FROM ProjectLinks p"),
	@NamedQuery(name = "ProjectLinks.findByUuid", query = "SELECT p FROM ProjectLinks p WHERE p.uuid = :uuid"),
	@NamedQuery(name = "ProjectLinks.findByProjectUuid", query = "SELECT p FROM ProjectLinks p WHERE p.projectUuid = :projectUuid"),
	@NamedQuery(name = "ProjectLinks.findByLinkType", query = "SELECT p FROM ProjectLinks p WHERE p.linkType = :linkType"),
	@NamedQuery(name = "ProjectLinks.findByName", query = "SELECT p FROM ProjectLinks p WHERE p.name = :name"),
	@NamedQuery(name = "ProjectLinks.findByHref", query = "SELECT p FROM ProjectLinks p WHERE p.href = :href"),
	@NamedQuery(name = "ProjectLinks.findByCreatedAt", query = "SELECT p FROM ProjectLinks p WHERE p.createdAt = :createdAt"),
	@NamedQuery(name = "ProjectLinks.findByUpdatedAt", query = "SELECT p FROM ProjectLinks p WHERE p.updatedAt = :updatedAt") })
public class ProjectLinks implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "uuid")
	private String uuid;
	@Basic(optional = false)
	@Column(name = "project_uuid")
	private String projectUuid;
	@Basic(optional = false)
	@Column(name = "link_type")
	private String linkType;
	@Column(name = "name")
	private String name;
	@Basic(optional = false)
	@Column(name = "href")
	private String href;
	@Basic(optional = false)
	@Column(name = "created_at")
	private long createdAt;
	@Basic(optional = false)
	@Column(name = "updated_at")
	private long updatedAt;

	public ProjectLinks() {
	}

	public ProjectLinks(String uuid) {
		this.uuid = uuid;
	}

	public ProjectLinks(String uuid, String projectUuid, String linkType, String href, long createdAt, long updatedAt) {
		this.uuid = uuid;
		this.projectUuid = projectUuid;
		this.linkType = linkType;
		this.href = href;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getProjectUuid() {
		return projectUuid;
	}

	public void setProjectUuid(String projectUuid) {
		this.projectUuid = projectUuid;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(long updatedAt) {
		this.updatedAt = updatedAt;
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
		if (!(object instanceof ProjectLinks)) {
			return false;
		}
		ProjectLinks other = (ProjectLinks) object;
		if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.sonarqube.domain.ProjectLinks[ uuid=" + uuid + " ]";
	}

}
