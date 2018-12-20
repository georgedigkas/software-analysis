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
@Table(name = "org_quality_gates")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "OrgQualityGates.findAll", query = "SELECT o FROM OrgQualityGates o"),
	@NamedQuery(name = "OrgQualityGates.findByUuid", query = "SELECT o FROM OrgQualityGates o WHERE o.uuid = :uuid"),
	@NamedQuery(name = "OrgQualityGates.findByOrganizationUuid", query = "SELECT o FROM OrgQualityGates o WHERE o.organizationUuid = :organizationUuid"),
	@NamedQuery(name = "OrgQualityGates.findByQualityGateUuid", query = "SELECT o FROM OrgQualityGates o WHERE o.qualityGateUuid = :qualityGateUuid") })
public class OrgQualityGates implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "uuid")
	private String uuid;
	@Basic(optional = false)
	@Column(name = "organization_uuid")
	private String organizationUuid;
	@Basic(optional = false)
	@Column(name = "quality_gate_uuid")
	private String qualityGateUuid;

	public OrgQualityGates() {
	}

	public OrgQualityGates(String uuid) {
		this.uuid = uuid;
	}

	public OrgQualityGates(String uuid, String organizationUuid, String qualityGateUuid) {
		this.uuid = uuid;
		this.organizationUuid = organizationUuid;
		this.qualityGateUuid = qualityGateUuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOrganizationUuid() {
		return organizationUuid;
	}

	public void setOrganizationUuid(String organizationUuid) {
		this.organizationUuid = organizationUuid;
	}

	public String getQualityGateUuid() {
		return qualityGateUuid;
	}

	public void setQualityGateUuid(String qualityGateUuid) {
		this.qualityGateUuid = qualityGateUuid;
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
		if (!(object instanceof OrgQualityGates)) {
			return false;
		}
		OrgQualityGates other = (OrgQualityGates) object;
		if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.sonarqube.domain.OrgQualityGates[ uuid=" + uuid + " ]";
	}

}
