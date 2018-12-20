package com.digkas.sonarqube.domain;

import java.io.Serializable;
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
@Table(name = "users")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
	@NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
	@NamedQuery(name = "Users.findByLogin", query = "SELECT u FROM Users u WHERE u.login = :login"),
	@NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
	@NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
	@NamedQuery(name = "Users.findByCryptedPassword", query = "SELECT u FROM Users u WHERE u.cryptedPassword = :cryptedPassword"),
	@NamedQuery(name = "Users.findBySalt", query = "SELECT u FROM Users u WHERE u.salt = :salt"),
	@NamedQuery(name = "Users.findByActive", query = "SELECT u FROM Users u WHERE u.active = :active"),
	@NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt"),
	@NamedQuery(name = "Users.findByUpdatedAt", query = "SELECT u FROM Users u WHERE u.updatedAt = :updatedAt"),
	@NamedQuery(name = "Users.findByScmAccounts", query = "SELECT u FROM Users u WHERE u.scmAccounts = :scmAccounts"),
	@NamedQuery(name = "Users.findByExternalLogin", query = "SELECT u FROM Users u WHERE u.externalLogin = :externalLogin"),
	@NamedQuery(name = "Users.findByExternalIdentityProvider", query = "SELECT u FROM Users u WHERE u.externalIdentityProvider = :externalIdentityProvider"),
	@NamedQuery(name = "Users.findByUserLocal", query = "SELECT u FROM Users u WHERE u.userLocal = :userLocal"),
	@NamedQuery(name = "Users.findByIsRoot", query = "SELECT u FROM Users u WHERE u.isRoot = :isRoot"),
	@NamedQuery(name = "Users.findByOnboarded", query = "SELECT u FROM Users u WHERE u.onboarded = :onboarded"),
	@NamedQuery(name = "Users.findByHomepageType", query = "SELECT u FROM Users u WHERE u.homepageType = :homepageType"),
	@NamedQuery(name = "Users.findByHomepageParameter", query = "SELECT u FROM Users u WHERE u.homepageParameter = :homepageParameter"),
	@NamedQuery(name = "Users.findByHashMethod", query = "SELECT u FROM Users u WHERE u.hashMethod = :hashMethod"),
	@NamedQuery(name = "Users.findByUuid", query = "SELECT u FROM Users u WHERE u.uuid = :uuid"),
	@NamedQuery(name = "Users.findByExternalId", query = "SELECT u FROM Users u WHERE u.externalId = :externalId"),
	@NamedQuery(name = "Users.findByOrganizationUuid", query = "SELECT u FROM Users u WHERE u.organizationUuid = :organizationUuid") })
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "login")
	private String login;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "crypted_password")
	private String cryptedPassword;
	@Column(name = "salt")
	private String salt;
	@Column(name = "active")
	private Boolean active;
	@Column(name = "created_at", columnDefinition="int8")
	private BigInteger createdAt;
	@Column(name = "updated_at", columnDefinition="int8")
	private BigInteger updatedAt;
	@Column(name = "scm_accounts")
	private String scmAccounts;
	@Basic(optional = false)
	@Column(name = "external_login")
	private String externalLogin;
	@Basic(optional = false)
	@Column(name = "external_identity_provider")
	private String externalIdentityProvider;
	@Column(name = "user_local")
	private Boolean userLocal;
	@Basic(optional = false)
	@Column(name = "is_root")
	private boolean isRoot;
	@Basic(optional = false)
	@Column(name = "onboarded")
	private boolean onboarded;
	@Column(name = "homepage_type")
	private String homepageType;
	@Column(name = "homepage_parameter")
	private String homepageParameter;
	@Column(name = "hash_method")
	private String hashMethod;
	@Basic(optional = false)
	@Column(name = "uuid")
	private String uuid;
	@Basic(optional = false)
	@Column(name = "external_id")
	private String externalId;
	@Column(name = "organization_uuid")
	private String organizationUuid;

	public Users() {
	}

	public Users(Integer id) {
		this.id = id;
	}

	public Users(Integer id, String login, String externalLogin, String externalIdentityProvider, boolean isRoot,
			boolean onboarded, String uuid, String externalId) {
		this.id = id;
		this.login = login;
		this.externalLogin = externalLogin;
		this.externalIdentityProvider = externalIdentityProvider;
		this.isRoot = isRoot;
		this.onboarded = onboarded;
		this.uuid = uuid;
		this.externalId = externalId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCryptedPassword() {
		return cryptedPassword;
	}

	public void setCryptedPassword(String cryptedPassword) {
		this.cryptedPassword = cryptedPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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

	public String getScmAccounts() {
		return scmAccounts;
	}

	public void setScmAccounts(String scmAccounts) {
		this.scmAccounts = scmAccounts;
	}

	public String getExternalLogin() {
		return externalLogin;
	}

	public void setExternalLogin(String externalLogin) {
		this.externalLogin = externalLogin;
	}

	public String getExternalIdentityProvider() {
		return externalIdentityProvider;
	}

	public void setExternalIdentityProvider(String externalIdentityProvider) {
		this.externalIdentityProvider = externalIdentityProvider;
	}

	public Boolean getUserLocal() {
		return userLocal;
	}

	public void setUserLocal(Boolean userLocal) {
		this.userLocal = userLocal;
	}

	public boolean getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public boolean getOnboarded() {
		return onboarded;
	}

	public void setOnboarded(boolean onboarded) {
		this.onboarded = onboarded;
	}

	public String getHomepageType() {
		return homepageType;
	}

	public void setHomepageType(String homepageType) {
		this.homepageType = homepageType;
	}

	public String getHomepageParameter() {
		return homepageParameter;
	}

	public void setHomepageParameter(String homepageParameter) {
		this.homepageParameter = homepageParameter;
	}

	public String getHashMethod() {
		return hashMethod;
	}

	public void setHashMethod(String hashMethod) {
		this.hashMethod = hashMethod;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getOrganizationUuid() {
		return organizationUuid;
	}

	public void setOrganizationUuid(String organizationUuid) {
		this.organizationUuid = organizationUuid;
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
		if (!(object instanceof Users)) {
			return false;
		}
		Users other = (Users) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.sonarqube.domain.Users[ id=" + id + " ]";
	}

}
