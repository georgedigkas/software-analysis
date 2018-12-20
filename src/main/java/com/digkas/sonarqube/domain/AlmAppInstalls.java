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
@Table(name = "alm_app_installs")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "AlmAppInstalls.findAll", query = "SELECT a FROM AlmAppInstalls a"),
	@NamedQuery(name = "AlmAppInstalls.findByUuid", query = "SELECT a FROM AlmAppInstalls a WHERE a.uuid = :uuid"),
	@NamedQuery(name = "AlmAppInstalls.findByAlmId", query = "SELECT a FROM AlmAppInstalls a WHERE a.almId = :almId"),
	@NamedQuery(name = "AlmAppInstalls.findByOwnerId", query = "SELECT a FROM AlmAppInstalls a WHERE a.ownerId = :ownerId"),
	@NamedQuery(name = "AlmAppInstalls.findByInstallId", query = "SELECT a FROM AlmAppInstalls a WHERE a.installId = :installId"),
	@NamedQuery(name = "AlmAppInstalls.findByCreatedAt", query = "SELECT a FROM AlmAppInstalls a WHERE a.createdAt = :createdAt"),
	@NamedQuery(name = "AlmAppInstalls.findByUpdatedAt", query = "SELECT a FROM AlmAppInstalls a WHERE a.updatedAt = :updatedAt") })
public class AlmAppInstalls implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "uuid")
	private String uuid;
	@Basic(optional = false)
	@Column(name = "alm_id")
	private String almId;
	@Basic(optional = false)
	@Column(name = "owner_id")
	private String ownerId;
	@Basic(optional = false)
	@Column(name = "install_id")
	private String installId;
	@Basic(optional = false)
	@Column(name = "created_at")
	private long createdAt;
	@Basic(optional = false)
	@Column(name = "updated_at")
	private long updatedAt;

	public AlmAppInstalls() {
	}

	public AlmAppInstalls(String uuid) {
		this.uuid = uuid;
	}

	public AlmAppInstalls(String uuid, String almId, String ownerId, String installId, long createdAt, long updatedAt) {
		this.uuid = uuid;
		this.almId = almId;
		this.ownerId = ownerId;
		this.installId = installId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAlmId() {
		return almId;
	}

	public void setAlmId(String almId) {
		this.almId = almId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getInstallId() {
		return installId;
	}

	public void setInstallId(String installId) {
		this.installId = installId;
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
		if (!(object instanceof AlmAppInstalls)) {
			return false;
		}
		AlmAppInstalls other = (AlmAppInstalls) object;
		if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.sonarqube.domain.AlmAppInstalls[ uuid=" + uuid + " ]";
	}

}
