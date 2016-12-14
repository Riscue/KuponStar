package com.riscue.kuponstar.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User implements Serializable {
	private static final long serialVersionUID = 6159543450145054789L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String username;
	private String password;
	private String name;
	private String surname;
	private String mail;
	private String hash;
	private Integer totalcoupon;
	private Integer successcoupon;
	private Integer failcoupon;
	private Double balance;
	private Timestamp lastlogin;
	private Timestamp signup;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getTotalcoupon() {
		return totalcoupon;
	}

	public void setTotalcoupon(Integer totalcoupon) {
		this.totalcoupon = totalcoupon;
	}

	public Integer getSuccesscoupon() {
		return successcoupon;
	}

	public void setSuccesscoupon(Integer successcoupon) {
		this.successcoupon = successcoupon;
	}

	public Integer getFailcoupon() {
		return failcoupon;
	}

	public void setFailcoupon(Integer failcoupon) {
		this.failcoupon = failcoupon;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Timestamp getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Timestamp lastlogin) {
		this.lastlogin = lastlogin;
	}

	public Timestamp getSignup() {
		return signup;
	}

	public void setSignup(Timestamp signup) {
		this.signup = signup;
	}

	public Long getId() {
		return id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof User)) {
			return false;
		}
		User other = (User) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.riscue.kuponstar.entity.User[ id=" + id + " ]";
	}
}