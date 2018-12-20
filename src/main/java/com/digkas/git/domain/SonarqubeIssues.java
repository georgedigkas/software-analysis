package com.digkas.git.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.digkas.sonarqube.domain.Issues;
import com.digkas.sonarqube.domain.Rules;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Entity
@Table(name = "sonarqube_issues")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "SonarqubeIssues.findAll", query = "SELECT s FROM SonarqubeIssues s"),
	@NamedQuery(name = "SonarqubeIssues.findById", query = "SELECT s FROM SonarqubeIssues s WHERE s.id = :id"),
	@NamedQuery(name = "SonarqubeIssues.findByKee", query = "SELECT s FROM SonarqubeIssues s WHERE s.kee = :kee"),
	@NamedQuery(name = "SonarqubeIssues.findBySeverity", query = "SELECT s FROM SonarqubeIssues s WHERE s.severity = :severity"),
	@NamedQuery(name = "SonarqubeIssues.findByLine", query = "SELECT s FROM SonarqubeIssues s WHERE s.line = :line"),
	@NamedQuery(name = "SonarqubeIssues.findByGap", query = "SELECT s FROM SonarqubeIssues s WHERE s.gap = :gap"),
	@NamedQuery(name = "SonarqubeIssues.findByStatus", query = "SELECT s FROM SonarqubeIssues s WHERE s.status = :status"),
	@NamedQuery(name = "SonarqubeIssues.findByResolution", query = "SELECT s FROM SonarqubeIssues s WHERE s.resolution = :resolution"),
	@NamedQuery(name = "SonarqubeIssues.findByEffort", query = "SELECT s FROM SonarqubeIssues s WHERE s.effort = :effort"),
	@NamedQuery(name = "SonarqubeIssues.findByCreatedAt", query = "SELECT s FROM SonarqubeIssues s WHERE s.createdAt = :createdAt"),
	@NamedQuery(name = "SonarqubeIssues.findByUpdatedAt", query = "SELECT s FROM SonarqubeIssues s WHERE s.updatedAt = :updatedAt"),
	@NamedQuery(name = "SonarqubeIssues.findByIssueCreationDate", query = "SELECT s FROM SonarqubeIssues s WHERE s.issueCreationDate = :issueCreationDate"),
	@NamedQuery(name = "SonarqubeIssues.findByIssueUpdateDate", query = "SELECT s FROM SonarqubeIssues s WHERE s.issueUpdateDate = :issueUpdateDate"),
	@NamedQuery(name = "SonarqubeIssues.findByIssueCloseDate", query = "SELECT s FROM SonarqubeIssues s WHERE s.issueCloseDate = :issueCloseDate"),
	@NamedQuery(name = "SonarqubeIssues.findByTags", query = "SELECT s FROM SonarqubeIssues s WHERE s.tags = :tags"),
	@NamedQuery(name = "SonarqubeIssues.findByIssueType", query = "SELECT s FROM SonarqubeIssues s WHERE s.issueType = :issueType"),
	@NamedQuery(name = "SonarqubeIssues.findByPath", query = "SELECT s FROM SonarqubeIssues s WHERE s.path = :path"),
	@NamedQuery(name = "SonarqubeIssues.findByScope", query = "SELECT s FROM SonarqubeIssues s WHERE s.scope = :scope"),
	@NamedQuery(name = "SonarqubeIssues.findByQualifier", query = "SELECT s FROM SonarqubeIssues s WHERE s.qualifier = :qualifier"),
	@NamedQuery(name = "SonarqubeIssues.findByMethodId", query = "SELECT s FROM SonarqubeIssues s WHERE s.methodId = :methodId") })
public class SonarqubeIssues implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "kee")
	private String kee;
	@Column(name = "severity")
	private String severity;
	@Column(name = "line")
	private Integer line;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "gap")
	private BigDecimal gap;
	@Column(name = "status")
	private String status;
	@Column(name = "resolution")
	private String resolution;
	@Column(name = "effort")
	private Integer effort;
	@Column(name = "created_at", columnDefinition = "int8")
	private BigInteger createdAt;
	@Column(name = "updated_at", columnDefinition = "int8")
	private BigInteger updatedAt;
	@Column(name = "issue_creation_date", columnDefinition = "int8")
	private BigInteger issueCreationDate;
	@Column(name = "issue_update_date", columnDefinition = "int8")
	private BigInteger issueUpdateDate;
	@Column(name = "issue_close_date", columnDefinition = "int8")
	private BigInteger issueCloseDate;
	@Column(name = "tags")
	private String tags;
	@Column(name = "issue_type")
	private Short issueType;
	@Column(name = "path")
	private String path;
	@Basic(optional = false)
	@Column(name = "scope")
	private String scope;
	@Basic(optional = false)
	@Column(name = "qualifier")
	private String qualifier;
	@JoinColumn(name = "method_id", referencedColumnName = "id")
	@ManyToOne
	private Method methodId;
	@JoinColumn(name = "commit_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch=FetchType.LAZY)
	private Commit commitId;
	@JoinColumn(name = "rule_id", referencedColumnName = "id")
	@ManyToOne(fetch=FetchType.LAZY)
	private Rules ruleId;

	public SonarqubeIssues() {
	}

	public SonarqubeIssues(Integer id) {
		this.id = id;
	}

	public SonarqubeIssues(Integer id, String kee, String scope, String qualifier) {
		this.id = id;
		this.kee = kee;
		this.scope = scope;
		this.qualifier = qualifier;
	}

	public SonarqubeIssues(Issues issue, String path, Commit commit, Rules rule, String scope, String qualifier) {
		this.severity = issue.getSeverity();
		this.line = issue.getLine();
		this.gap = issue.getGap();
		this.status = issue.getStatus();
		this.resolution = issue.getResolution();
		this.effort = issue.getEffort();
		this.createdAt = issue.getCreatedAt();
		this.updatedAt = issue.getUpdatedAt();
		this.issueCreationDate = issue.getIssueCreationDate();
		this.issueUpdateDate = issue.getIssueUpdateDate();
		this.issueCloseDate = issue.getIssueCloseDate();
		this.tags = issue.getTags();
		this.issueType = issue.getIssueType();
		this.path = path;
		this.commitId = commit;
		this.ruleId = rule;
		this.kee = issue.getKee();
		this.scope = scope;
		this.qualifier = qualifier;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKee() {
		return kee;
	}

	public void setKee(String kee) {
		this.kee = kee;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public Integer getLine() {
		return line;
	}

	public void setLine(Integer line) {
		this.line = line;
	}

	public BigDecimal getGap() {
		return gap;
	}

	public void setGap(BigDecimal gap) {
		this.gap = gap;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public Integer getEffort() {
		return effort;
	}

	public void setEffort(Integer effort) {
		this.effort = effort;
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

	public BigInteger getIssueCreationDate() {
		return issueCreationDate;
	}

	public void setIssueCreationDate(BigInteger issueCreationDate) {
		this.issueCreationDate = issueCreationDate;
	}

	public BigInteger getIssueUpdateDate() {
		return issueUpdateDate;
	}

	public void setIssueUpdateDate(BigInteger issueUpdateDate) {
		this.issueUpdateDate = issueUpdateDate;
	}

	public BigInteger getIssueCloseDate() {
		return issueCloseDate;
	}

	public void setIssueCloseDate(BigInteger issueCloseDate) {
		this.issueCloseDate = issueCloseDate;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Short getIssueType() {
		return issueType;
	}

	public void setIssueType(Short issueType) {
		this.issueType = issueType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public Method getMethodId() {
		return methodId;
	}

	public void setMethodId(Method methodId) {
		this.methodId = methodId;
	}

	public Commit getCommitId() {
		return commitId;
	}

	public void setCommitId(Commit commitId) {
		this.commitId = commitId;
	}

	public Rules getRuleId() {
		return ruleId;
	}

	public void setRuleId(Rules ruleId) {
		this.ruleId = ruleId;
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
		if (!(object instanceof SonarqubeIssues)) {
			return false;
		}
		SonarqubeIssues other = (SonarqubeIssues) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.git.domain.SonarqubeIssues[ id=" + id + " ]";
	}

}
