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
@Table(name = "deprecated_rule_keys")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "DeprecatedRuleKeys.findAll", query = "SELECT d FROM DeprecatedRuleKeys d"),
	@NamedQuery(name = "DeprecatedRuleKeys.findByUuid", query = "SELECT d FROM DeprecatedRuleKeys d WHERE d.uuid = :uuid"),
	@NamedQuery(name = "DeprecatedRuleKeys.findByRuleId", query = "SELECT d FROM DeprecatedRuleKeys d WHERE d.ruleId = :ruleId"),
	@NamedQuery(name = "DeprecatedRuleKeys.findByOldRepositoryKey", query = "SELECT d FROM DeprecatedRuleKeys d WHERE d.oldRepositoryKey = :oldRepositoryKey"),
	@NamedQuery(name = "DeprecatedRuleKeys.findByOldRuleKey", query = "SELECT d FROM DeprecatedRuleKeys d WHERE d.oldRuleKey = :oldRuleKey"),
	@NamedQuery(name = "DeprecatedRuleKeys.findByCreatedAt", query = "SELECT d FROM DeprecatedRuleKeys d WHERE d.createdAt = :createdAt") })
public class DeprecatedRuleKeys implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "uuid")
	private String uuid;
	@Basic(optional = false)
	@Column(name = "rule_id")
	private int ruleId;
	@Basic(optional = false)
	@Column(name = "old_repository_key")
	private String oldRepositoryKey;
	@Basic(optional = false)
	@Column(name = "old_rule_key")
	private String oldRuleKey;
	@Basic(optional = false)
	@Column(name = "created_at")
	private long createdAt;

	public DeprecatedRuleKeys() {
	}

	public DeprecatedRuleKeys(String uuid) {
		this.uuid = uuid;
	}

	public DeprecatedRuleKeys(String uuid, int ruleId, String oldRepositoryKey, String oldRuleKey, long createdAt) {
		this.uuid = uuid;
		this.ruleId = ruleId;
		this.oldRepositoryKey = oldRepositoryKey;
		this.oldRuleKey = oldRuleKey;
		this.createdAt = createdAt;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public String getOldRepositoryKey() {
		return oldRepositoryKey;
	}

	public void setOldRepositoryKey(String oldRepositoryKey) {
		this.oldRepositoryKey = oldRepositoryKey;
	}

	public String getOldRuleKey() {
		return oldRuleKey;
	}

	public void setOldRuleKey(String oldRuleKey) {
		this.oldRuleKey = oldRuleKey;
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
		if (!(object instanceof DeprecatedRuleKeys)) {
			return false;
		}
		DeprecatedRuleKeys other = (DeprecatedRuleKeys) object;
		if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.sonarqube.domain.DeprecatedRuleKeys[ uuid=" + uuid + " ]";
	}

}
