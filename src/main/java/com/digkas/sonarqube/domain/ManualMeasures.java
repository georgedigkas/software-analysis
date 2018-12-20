package com.digkas.sonarqube.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "manual_measures")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "ManualMeasures.findAll", query = "SELECT m FROM ManualMeasures m"),
	@NamedQuery(name = "ManualMeasures.findById", query = "SELECT m FROM ManualMeasures m WHERE m.id = :id"),
	@NamedQuery(name = "ManualMeasures.findByMetricId", query = "SELECT m FROM ManualMeasures m WHERE m.metricId = :metricId"),
	@NamedQuery(name = "ManualMeasures.findByValue", query = "SELECT m FROM ManualMeasures m WHERE m.value = :value"),
	@NamedQuery(name = "ManualMeasures.findByTextValue", query = "SELECT m FROM ManualMeasures m WHERE m.textValue = :textValue"),
	@NamedQuery(name = "ManualMeasures.findByUserUuid", query = "SELECT m FROM ManualMeasures m WHERE m.userUuid = :userUuid"),
	@NamedQuery(name = "ManualMeasures.findByDescription", query = "SELECT m FROM ManualMeasures m WHERE m.description = :description"),
	@NamedQuery(name = "ManualMeasures.findByCreatedAt", query = "SELECT m FROM ManualMeasures m WHERE m.createdAt = :createdAt"),
	@NamedQuery(name = "ManualMeasures.findByUpdatedAt", query = "SELECT m FROM ManualMeasures m WHERE m.updatedAt = :updatedAt"),
	@NamedQuery(name = "ManualMeasures.findByComponentUuid", query = "SELECT m FROM ManualMeasures m WHERE m.componentUuid = :componentUuid") })
public class ManualMeasures implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	@Basic(optional = false)
	@Column(name = "metric_id")
	private int metricId;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "value")
	private BigDecimal value;
	@Column(name = "text_value")
	private String textValue;
	@Column(name = "user_uuid")
	private String userUuid;
	@Column(name = "description")
	private String description;
	@Column(name = "created_at", columnDefinition="int8")
	private BigInteger createdAt;
	@Column(name = "updated_at", columnDefinition="int8")
	private BigInteger updatedAt;
	@Basic(optional = false)
	@Column(name = "component_uuid")
	private String componentUuid;

	public ManualMeasures() {
	}

	public ManualMeasures(Long id) {
		this.id = id;
	}

	public ManualMeasures(Long id, int metricId, String componentUuid) {
		this.id = id;
		this.metricId = metricId;
		this.componentUuid = componentUuid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMetricId() {
		return metricId;
	}

	public void setMetricId(int metricId) {
		this.metricId = metricId;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getTextValue() {
		return textValue;
	}

	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigInteger getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(BigInteger createdAt) {
		this.createdAt = createdAt;
	}

	public BigInteger getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(BigInteger updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getComponentUuid() {
		return componentUuid;
	}

	public void setComponentUuid(String componentUuid) {
		this.componentUuid = componentUuid;
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
		if (!(object instanceof ManualMeasures)) {
			return false;
		}
		ManualMeasures other = (ManualMeasures) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.sonarqube.domain.ManualMeasures[ id=" + id + " ]";
	}

}
