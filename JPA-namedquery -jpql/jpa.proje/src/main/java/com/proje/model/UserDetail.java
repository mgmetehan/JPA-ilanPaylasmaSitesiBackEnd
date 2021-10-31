package com.proje.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.proje.model.util.PhoneType;

@Entity
@NamedQueries({
		@NamedQuery(name = "UserDetail.findByUserName", query = "SELECT ud FROM UserDetail ud WHERE ud.user.username= :username"),
		@NamedQuery(name = "UserDetail.findWithAddressByUserName", query = "SELECT ud FROM UserDetail ud LEFT JOIN FETCH ud.addresses WHERE ud.user.username= :username"),
		@NamedQuery(name = "UserDetail.findWithAdvertisementByUserName", query = "SELECT ud FROM UserDetail ud LEFT JOIN FETCH ud.advertisements WHERE ud.user.username= :username") })
public class UserDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userDetailId;

	private String firstName;

	private String lastName;

	private Date birthDate;

	@ElementCollection
	@JoinTable(name = "userdetail_phonenumber", joinColumns = @JoinColumn(name = "UserDetailId"))
	@MapKeyColumn(name = "phoneType")
	@Column(name = "phoneNumber")
	private Map<PhoneType, String> phoneNumbers = new HashMap<PhoneType, String>();

	@ElementCollection(fetch = FetchType.LAZY)
	@JoinTable(name = "userdetail_address", joinColumns = @JoinColumn(name = "userDetailId"))
	private List<Address> addresses = new ArrayList<Address>();

	@OneToMany(mappedBy = "userDetail", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.MERGE })
	private List<Advertisement> advertisements = new ArrayList<Advertisement>();

	@OneToOne(mappedBy = "userDetail", fetch = FetchType.LAZY)
	private User user;

	public UserDetail() {
	}

	public UserDetail(String firstName, String lastName, Date birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	public Integer getUserDetailId() {
		return userDetailId;
	}

	public void setUserDetailId(Integer userDetailId) {
		this.userDetailId = userDetailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Map<PhoneType, String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Map<PhoneType, String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Advertisement> getAdvertisements() {
		return advertisements;
	}

	public void setAdvertisements(List<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
