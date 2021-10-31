package com.proje.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@NamedQueries({
		@NamedQuery(name = "Advertisement.findById", query = "SELECT a FROM Advertisement a LEFT JOIN FETCH a.educations WHERE a.advertisementId = :advertisementId"),
		@NamedQuery(name = "Advertisement.findByUsername", query = "SELECT a FROM User u LEFT JOIN u.userDetail ud LEFT JOIN ud.advertisements a WHERE u.username= :username"),
		@NamedQuery(name = "Advertisement.findAdvertisements", query = "SELECT a FROM Advertisement a") })
public class Advertisement implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer advertisementId;

	private String title;

	@Lob
	private String workDefinition;

	@Lob
	private String criteria;

	private Date addDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	private boolean enabled = false;// Paylaþým onaylandý mý

	private Date removeDate;

	@ManyToMany
	@JoinTable(name = "advertisement_education", joinColumns = @JoinColumn(name = "advertisementId"), inverseJoinColumns = @JoinColumn(name = "educationId"))
	private List<Education> educations = new ArrayList<Education>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userDetailId")
	private UserDetail userDetail;

	public Advertisement() {
	}

	public Advertisement(String title, String workDefinition, String criteria) {
		this.title = title;
		this.workDefinition = workDefinition;
		this.criteria = criteria;
	}
	public Integer getAdvertisementId() {
		return advertisementId;
	}
	public void setAdvertisementId(Integer advertisementId) {
		this.advertisementId = advertisementId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWorkDefinition() {
		return workDefinition;
	}

	public void setWorkDefinition(String workDefinition) {
		this.workDefinition = workDefinition;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getRemoveDate() {
		return removeDate;
	}

	public void setRemoveDate(Date removeDate) {
		this.removeDate = removeDate;
	}

	public List<Education> getEducations() {
		return educations;
	}

	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

}
