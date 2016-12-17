package com.riscue.kuponstar.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MATCHES")
public class Match implements Serializable {
	private static final long serialVersionUID = 3464053548537401400L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int code;
	private String home;
	private String guest;
	private String MS1;
	private String MS0;
	private String MS2;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	public String getMS1() {
		return MS1;
	}

	public void setMS1(String mS1) {
		MS1 = mS1;
	}

	public String getMS0() {
		return MS0;
	}

	public void setMS0(String mS0) {
		MS0 = mS0;
	}

	public String getMS2() {
		return MS2;
	}

	public void setMS2(String mS2) {
		MS2 = mS2;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Match)) {
			return false;
		}
		Match other = (Match) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.riscue.kuponstar.entity.Match[ id=" + id + " ]";
	}
}