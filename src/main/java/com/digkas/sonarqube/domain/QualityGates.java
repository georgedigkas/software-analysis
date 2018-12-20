package com.digkas.sonarqube.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity
@Table(name = "quality_gates")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "QualityGates.findAll", query = "SELECT q FROM QualityGates q"),
	@NamedQuery(name = "QualityGates.findById", query = "SELECT q FROM QualityGates q WHERE q.id = :id"),
	@NamedQuery(name = "QualityGates.findByName", query = "SELECT q FROM QualityGates q WHERE q.name = :name"),
	@NamedQuery(name = "QualityGates.findByCreatedAt", query = "SELECT q FROM QualityGates q WHERE q.createdAt = :createdAt"),
	@NamedQuery(name = "QualityGates.findByUpdatedAt", query = "SELECT q FROM QualityGates q WHERE q.updatedAt = :updatedAt"),
	@NamedQuery(name = "QualityGates.findByIsBuiltIn", query = "SELECT q FROM QualityGates q WHERE q.isBuiltIn = :isBuiltIn"),
	@NamedQuery(name = "QualityGates.findByUuid", query = "SELECT q FROM QualityGates q WHERE q.uuid = :uuid") })
public class QualityGates implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "name")
	private String name;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	@Basic(optional = false)
	@Column(name = "is_built_in")
	private boolean isBuiltIn;
	@Basic(optional = false)
	@Column(name = "uuid")
	private String uuid;

	public QualityGates() {
	}

	public QualityGates(Integer id) {
		this.id = id;
	}

	public QualityGates(Integer id, String name, boolean isBuiltIn, String uuid) {
		this.id = id;
		this.name = name;
		this.isBuiltIn = isBuiltIn;
		this.uuid = uuid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean getIsBuiltIn() {
		return isBuiltIn;
	}

	public void setIsBuiltIn(boolean isBuiltIn) {
		this.isBuiltIn = isBuiltIn;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof QualityGates)) {
			return false;
		}
		QualityGates other = (QualityGates) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.sonarqube.domain.QualityGates[ id=" + id + " ]";
	}

}
