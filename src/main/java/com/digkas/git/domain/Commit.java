package com.digkas.git.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.jgit.revwalk.RevCommit;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Entity
@Table(name = "commit")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Commit.findAll", query = "SELECT c FROM Commit c"),
	@NamedQuery(name = "Commit.findById", query = "SELECT c FROM Commit c WHERE c.id = :id"),
	@NamedQuery(name = "Commit.findBySha", query = "SELECT c FROM Commit c WHERE c.sha = :sha"),
	@NamedQuery(name = "Commit.findByCommitTime", query = "SELECT c FROM Commit c WHERE c.commitTime = :commitTime") })
public class Commit implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "sha")
	private String sha;
	@Basic(optional = false)
	@Column(name = "commit_time")
	private int commitTime;
	@JoinTable(name = "commits_history", joinColumns = {
			@JoinColumn(name = "commit_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "previous_commit_id", referencedColumnName = "id") })
	@ManyToMany
	private Collection<Commit> commitCollection;
	@ManyToMany(mappedBy = "commitCollection")
	private Collection<Commit> commitCollection1;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "commitId")//, fetch=FetchType.LAZY
	private Collection<SonarqubeIssues> sonarqubeIssuesCollection;
	@JoinColumn(name = "project_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Project projectId;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "commitId")//, fetch=FetchType.EAGER
	//@Fetch(value = FetchMode.SUBSELECT)
	private Collection<Method> methodCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "commitId")
	private Collection<CommitFiles> commitFilesCollection;

	public Commit() {
	}

	public Commit(Integer id) {
		this.id = id;
	}

	public Commit(Integer id, String sha, int commitTime) {
		this.id = id;
		this.sha = sha;
		this.commitTime = commitTime;
	}

	public Commit(String sha, int commitTime, Project projectId) {
		this.sha = sha;
		this.commitTime = commitTime;
		this.projectId = projectId;
	}

	public Commit(RevCommit rc, Project gitProject) {
		this.sha = rc.getName();
		this.commitTime = rc.getCommitTime();
		this.projectId = gitProject;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}

	public int getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(int commitTime) {
		this.commitTime = commitTime;
	}

	@XmlTransient
	public Collection<Commit> getCommitCollection() {
		return commitCollection;
	}

	public void setCommitCollection(Collection<Commit> commitCollection) {
		this.commitCollection = commitCollection;
	}

	@XmlTransient
	public Collection<Commit> getCommitCollection1() {
		return commitCollection1;
	}

	public void setCommitCollection1(Collection<Commit> commitCollection1) {
		this.commitCollection1 = commitCollection1;
	}

	@XmlTransient
	public Collection<SonarqubeIssues> getSonarqubeIssuesCollection() {
		return sonarqubeIssuesCollection;
	}

	public void setSonarqubeIssuesCollection(Collection<SonarqubeIssues> sonarqubeIssuesCollection) {
		this.sonarqubeIssuesCollection = sonarqubeIssuesCollection;
	}

	public Project getProjectId() {
		return projectId;
	}

	public void setProjectId(Project projectId) {
		this.projectId = projectId;
	}

	@XmlTransient
	public Collection<Method> getMethodCollection() {
		return methodCollection;
	}

	public void setMethodCollection(Collection<Method> methodCollection) {
		this.methodCollection = methodCollection;
	}

	@XmlTransient
	public Collection<CommitFiles> getCommitFilesCollection() {
		return commitFilesCollection;
	}

	public void setCommitFilesCollection(Collection<CommitFiles> commitFilesCollection) {
		this.commitFilesCollection = commitFilesCollection;
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
		if (!(object instanceof Commit)) {
			return false;
		}
		Commit other = (Commit) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.git.domain.Commit[ id=" + id + " ]";
	}

}
