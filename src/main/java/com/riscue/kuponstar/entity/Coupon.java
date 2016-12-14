package com.riscue.kuponstar.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COUPONS")
public class Coupon implements Serializable {
	private static final long serialVersionUID = 2728695690484245178L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Match[] matches;
	private Integer userid;
	private Integer Multiplier;
	private Double MaxRatio;
	private Double MaxOdds;
	private Double TotalOdds;
	private Boolean durum;
	private Timestamp tarih;

	public Match[] getMatches() {
		return matches;
	}

	public void setMatches(Match[] matches) {
		this.matches = matches;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getMultiplier() {
		return Multiplier;
	}

	public void setMultiplier(Integer multiplier) {
		Multiplier = multiplier;
	}

	public Double getMaxRatio() {
		return MaxRatio;
	}

	public void setMaxRatio(Double maxRatio) {
		MaxRatio = maxRatio;
	}

	public Double getMaxOdds() {
		return MaxOdds;
	}

	public void setMaxOdds(Double maxOdds) {
		MaxOdds = maxOdds;
	}

	public Double getTotalOdds() {
		return TotalOdds;
	}

	public void setTotalOdds(Double totalOdds) {
		TotalOdds = totalOdds;
	}

	public Boolean getDurum() {
		return durum;
	}

	public void setDurum(Boolean durum) {
		this.durum = durum;
	}

	public Timestamp getTarih() {
		return tarih;
	}

	public void setTarih(Timestamp tarih) {
		this.tarih = tarih;
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
		if (!(object instanceof Coupon)) {
			return false;
		}
		Coupon other = (Coupon) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.riscue.kuponstar.entity.Coupon[ id=" + id + " ]";
	}
}