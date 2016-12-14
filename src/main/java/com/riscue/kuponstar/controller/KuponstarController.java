package com.riscue.kuponstar.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riscue.kuponstar.entity.Coupon;
import com.riscue.kuponstar.entity.Match;
import com.riscue.kuponstar.entity.User;
import com.riscue.kuponstar.model.Response;
import com.riscue.kuponstar.service.KuponstarService;

@Controller
@RequestMapping(path = "/api")
public class KuponstarController {

	public static final Double DEFAULT_BALANCE = 1000.0;
	@Autowired
	KuponstarService service;
	private User user;

	private boolean isLogined() {
		if (user == null)
			return false;
		User dbuser = service.getUserByUsername(user.getUsername());
		if (dbuser == null)
			return false;
		return this.user != null && this.user.getHash().equals(dbuser.getHash());
	}

	@RequestMapping(path = "/signup", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Response signup(@RequestBody User user) {
		Response response = new Response();
		if (user.getUsername() == null || user.getUsername() == "") {
			response.addError("Username can't be empty!");
		}
		if (user.getMail() == null || user.getMail() == "") {
			response.addError("Mail can't be empty!");
		}
		if (user.getName() == null || user.getName() == "") {
			response.addError("Name can't be empty!");
		}
		if (user.getSurname() == null || user.getSurname() == "") {
			response.addError("Surname can't be empty!");
		}
		if (user.getPassword() == null || user.getPassword() == "") {
			response.addError("Password can't be empty!");
		}

		if (response.getStatus() == "SUCCESS") {
			if (service.getUserByUsername(user.getUsername()) != null) {
				response.addError("Username already exist!");
			} else if (service.getUserByMail(user.getMail()) != null) {
				response.addError("Mail already exist!");
			} else {
				user.setTotalcoupon(0);
				user.setSuccesscoupon(0);
				user.setFailcoupon(0);
				user.setHash("");
				user.setBalance(KuponstarController.DEFAULT_BALANCE);
				user.setLastlogin(null);
				user.setSignup(new Timestamp(new Date().getTime()));
				
				service.saveUser(user);
			}
		}
		return response;
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody User login(@RequestBody User user) {
		Response response = new Response();
		if (user.getUsername() == null || user.getUsername() == "") {
			response.addError("Username can't be empty!");
		}
		if (user.getPassword() == null || user.getPassword() == "") {
			response.addError("Password can't be empty!");
		}

		if (response.getStatus() == "FAIL")
			return null;

		User dbuser = service.getUserByUsername(user.getUsername());
		if (dbuser == null)
			return null;

		if (!dbuser.getPassword().equals(user.getPassword()))
			return null;

		this.user = dbuser;
		this.user.setLastlogin(new Timestamp(new Date().getTime()));
		this.user.setHash(Utils.MD5(this.user.getUsername() + new Date().getTime()));
		service.saveUser(this.user);
		return this.user;
	}

	@RequestMapping(path = "/getfixture", produces = "application/json")
	public @ResponseBody List<Match> getFixture() {
		if (isLogined())
			return service.getFixture();
		return null;
	}

	@RequestMapping(path = "/playcoupon", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Response playCoupon(@RequestBody Coupon coupon) {
		Match[] matches = coupon.getMatches();
		coupon.setMaxRatio(1.0);

		for (int i = 0; i < matches.length; i++) {
			Match match = service.getMatch(matches[i].getCode());
			Double ratio = (matches[i].getMS0() == 1) ? match.getMS0()
					: (matches[i].getMS1() == 1) ? match.getMS0() : (matches[i].getMS2() == 1) ? match.getMS0() : 0.0;
			coupon.setMaxRatio(coupon.getMaxRatio() * ratio);
		}
		coupon.setTotalOdds(coupon.getMaxRatio() * coupon.getMaxOdds());
		service.saveCoupon(coupon);
		return new Response();
	}
}
