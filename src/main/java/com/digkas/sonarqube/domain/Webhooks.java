package com.digkas.sonarqube.domain;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "webhooks")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Webhooks.findAll", query = "SELECT w FROM Webhooks w"),
	@NamedQuery(name = "Webhooks.findByUuid", query = "SELECT w FROM Webhooks w WHERE w.uuid = :uuid"),
	@NamedQuery(name = "Webhooks.findByName", query = "SELECT w FROM Webhooks w WHERE w.name = :name"),
	@NamedQuery(name = "Webhooks.findByUrl", query = "SELECT w FROM Webhooks w WHERE w.url = :url"),
	@NamedQuery(name = "Webhooks.findByOrganizationUuid", query = "SELECT w FROM Webhooks w WHERE w.organizationUuid = :organizationUuid"),
	@NamedQuery(name = "Webhooks.findByProjectUuid", query = "SELECT w FROM Webhooks w WHERE w.projectUuid = :projectUuid"),
	@NamedQuery(name = "Webhooks.findByCreatedAt", query = "SELECT w FROM Webhooks w WHERE w.createdAt = :createdAt"),
	@NamedQuery(name = "Webhooks.findByUpdatedAt", query = "SELECT w FROM Webhooks w WHERE w.updatedAt = :updatedAt") })
public class Webhooks implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "uuid")
	private String uuid;
	@Basic(optional = false)
	@Column(name = "name")
	private String name;
	@Basic(optional = false)
	@Column(name = "url")
	private String url;
	@Column(name = "organization_uuid")
	private String organizationUuid;
	@Column(name = "project_uuid")
	private String projectUuid;
	@Basic(optional = false)
	@Column(name = "created_at")
	private long createdAt;
	@Column(name = "updated_at", columnDefinition="int8")
	private BigInteger updatedAt;

	public Webhooks() {
	}

	public Webhooks(String uuid) {
		this.uuid = uuid;
	}

	public Webhooks(String uuid, String name, String url, long createdAt) {
		this.uuid = uuid;
		this.name = name;
		this.url = url;
		this.createdAt = createdAt;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOrganizationUuid() {
		return organizationUuid;
	}

	public void setOrganizationUuid(String organizationUuid) {
		this.organizationUuid = organizationUuid;
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

	public BigInteger getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(BigInteger updatedAt) {
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
		if (!(object instanceof Webhooks)) {
			return false;
		}
		Webhooks other = (Webhooks) object;
		if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.sonarqube.domain.Webhooks[ uuid=" + uuid + " ]";
	}

}
