package com.riscue.kuponstar.model;

import com.riscue.kuponstar.entity.Coupon;
import com.riscue.kuponstar.entity.User;

public class PlayCouponParams {
	private Coupon coupon;
	private User user;

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (coupon != null ? coupon.hashCode() : 0);
		hash += (user != null ? user.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PlayCouponParams)) {
			return false;
		}
		PlayCouponParams other = (PlayCouponParams) object;
		if ((this.coupon == null && other.coupon != null) || (this.coupon != null && !this.coupon.equals(other.coupon)))
			return false;

		if ((this.user == null && other.user != null) || (this.user != null && !this.user.equals(other.user)))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return coupon.toString() + ", " + user.toString();
	}
}
